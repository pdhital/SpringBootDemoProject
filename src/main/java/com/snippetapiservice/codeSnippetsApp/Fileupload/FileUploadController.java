package com.snippetapiservice.codeSnippetsApp.Fileupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private static String UPLOAD_FOLDER = "C:\\Users\\Dave Dhital\\Documents\\AA_JavaProjects\\codeSnippetsApp\\uploadedFiles\\";

    @PostMapping("/file/upload")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        DatabaseFile fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getId(), fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/file/uploads")
    public List < Response > uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    //download file from server
    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        DatabaseFile dbFile =  fileStorageService.getFile((id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    //upload file to local machine
    @PostMapping("/upload-files")
    public ResponseEntity<?> upload2(@RequestParam("name") long postId, @RequestParam("files") MultipartFile[] uploadFiles) {

        logger.debug("Multiple file upload!");

        // Get file name
        String uploadedFileName = Arrays.stream(uploadFiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            saveUploadedFiles(Arrays.asList(uploadFiles));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Uploaded files: "+ uploadedFileName, HttpStatus.OK);

    }

    //download file from local folder
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        Path path = Paths.get(UPLOAD_FOLDER + fileName);
        UrlResource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType(fileName)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //save file locally
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next file
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }
    }

    public String fileType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileType = "";
        if (fileExtension.equals("pdf")) fileType =  "application/pdf";
        else if (fileExtension.equals("doc")) fileType =  "application/msword";
        else if (fileExtension.equals("jpeg")) fileType =  "image/jpeg";
        else if (fileExtension.equals("png")) fileType =  "image/png";
        else if (fileExtension.equals("JPG")) fileType =  "image/jpeg";
        else if (fileExtension.equals("jpg")) fileType =  "image/jpeg";
        else if (fileExtension.equals("PNG")) fileType =  "image/PNG";

        return fileType;
    }
}
