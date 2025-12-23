package com.sixoneseven.blocktrain.controller;

import com.sixoneseven.blocktrain.entity.DataAsset;
import com.sixoneseven.blocktrain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam MultipartFile file,
            @RequestParam String dataName,
            @RequestParam String dataType) throws Exception {

        DataAsset asset = dataService.upload(file, dataName, dataType);
        return ResponseEntity.ok(asset);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {

        File file = dataService.download(id);
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
}
