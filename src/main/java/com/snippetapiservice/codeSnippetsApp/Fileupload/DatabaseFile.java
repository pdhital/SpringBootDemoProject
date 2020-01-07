package com.snippetapiservice.codeSnippetsApp.Fileupload;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.snippetapiservice.codeSnippetsApp.Post.Post;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "files")
public class DatabaseFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    @NotNull
    private String id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "data")
    private byte[] data;

    public DatabaseFile() { }

    public DatabaseFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
