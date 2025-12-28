package com.sixoneseven.blocktrain.controller;

import com.sixoneseven.blocktrain.repo.entity.DownloadResult;
import com.sixoneseven.blocktrain.repo.entity.File;
import com.sixoneseven.blocktrain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
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
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  shootTime
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
        String chainHash = result.getChainHash();
        String localHash = result.getLocalHash();

        if (result.isTrusted()) {

            java.io.File zip = result.getZipFile();

            return ResponseEntity.ok()
                    // 可信凭证 hash
                    .header("Chain-Hash", chainHash)
                    .header("Local-Hash", localHash)
                    // 文件下载
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=trusted_data_" + dataId + ".zip")
                    .body(new FileSystemResource(zip));

        } else {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("Chain-Hash", chainHash)
                    .header("Local-Hash", localHash)
                    .body("可信校验失败");
        }
    }


}
