package com.app.camel.controller;

import org.springframework.web.bind.annotation.RestController;
import com.app.camel.routes.FileRoutes;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class FileController {
    
    private final FileRoutes fileRoutes;

    public FileController(FileRoutes fileRoutes) {
        this.fileRoutes = fileRoutes;
    }

    @PostMapping("/file")
    public String postMethodName(@RequestBody String entity) throws Exception {

        if(entity == null || entity.isEmpty()) {
            return "Request body is empty";
        }

        if("move".equalsIgnoreCase(entity)) {

            fileRoutes.configure(); // Call the configure method to trigger the file transfer

        }

        return entity;
    }
    
    
}
