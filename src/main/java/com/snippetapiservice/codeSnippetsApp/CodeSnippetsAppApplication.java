package com.snippetapiservice.codeSnippetsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.snippetapiservice.codeSnippetsApp")
public class CodeSnippetsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeSnippetsAppApplication.class, args);
	}

}
