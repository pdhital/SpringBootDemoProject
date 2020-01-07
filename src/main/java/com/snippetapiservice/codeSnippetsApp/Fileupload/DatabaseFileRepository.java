package com.snippetapiservice.codeSnippetsApp.Fileupload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {

}
