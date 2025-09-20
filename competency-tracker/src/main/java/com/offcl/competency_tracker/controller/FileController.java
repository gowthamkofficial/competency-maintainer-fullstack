package com.offcl.competency_tracker.controller;

import com.offcl.competency_tracker.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/view/{id}")
    public ResponseEntity<FileSystemResource> viewFile(@PathVariable Long id) {
        return fileService.viewFile(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long id) {
        return fileService.downloadFile(id);
    }
}
