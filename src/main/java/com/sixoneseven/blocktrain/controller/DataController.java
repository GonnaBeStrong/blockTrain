package com.sixoneseven.blocktrain.controller;

import com.sixoneseven.blocktrain.repo.entity.DownloadResult;
import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam MultipartFile file,
            @RequestParam String dataName,
            @RequestParam String dataType,
            @RequestParam String detail,
            @RequestParam String provider,
            @RequestParam String longitude,
            @RequestParam String latitude,
            @RequestParam String satellite,
            @RequestParam LocalDateTime shootTime
    ) throws Exception {
        File asset = dataService.upload(
                 file,
                 dataName,
                 dataType,
                 detail,
                 provider,
                 longitude,
                 latitude,
                 satellite,
                 shootTime
        );
        return ResponseEntity.ok(asset);
    }

    @GetMapping("/download/by-dataid/{dataId}")
    public ResponseEntity<?> downloadByDataId(@PathVariable String dataId) throws Exception {
        DownloadResult result = dataService.downloadZipByDataId(dataId);

        if (result.isTrusted()) {
            java.io.File zip = result.getZipFile();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=trusted_data_" + dataId + ".zip")
                    .body(new FileSystemResource(zip));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("校验失败，文件不可下载");
        }
    }

}
