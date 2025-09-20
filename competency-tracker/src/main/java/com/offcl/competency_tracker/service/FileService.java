package com.offcl.competency_tracker.service;

import com.offcl.competency_tracker.exception.ResourceNotFoundException;
import com.offcl.competency_tracker.model.FileEntity;
import com.offcl.competency_tracker.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepo;

   
    public ResponseEntity<FileSystemResource> viewFile(Long id) {
        FileEntity fileEntity = fileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id " + id));

        File file = new File(fileEntity.getFilePath());
        if (!file.exists()) {
            throw new ResourceNotFoundException("File not found on disk: " + fileEntity.getFilePath());
        }

        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .body(resource);
    }


    public ResponseEntity<FileSystemResource> downloadFile(Long id) {
        FileEntity fileEntity = fileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id " + id));

        File file = new File(fileEntity.getFilePath());
        if (!file.exists()) {
            throw new ResourceNotFoundException("File not found on disk: " + fileEntity.getFilePath());
        }

        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileEntity.getFileName())
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .body(resource);
    }
}
