package com.sixoneseven.blocktrain.controller;

import com.sixoneseven.blocktrain.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    /**
     * 查询可信凭证
     */
    @GetMapping("/query/{dataId}")
    public ResponseEntity<Map<String, Object>> query(
            @PathVariable Long dataId) throws Exception {

        Map<String, Object> credential = credentialService.getCredential(dataId);
        return ResponseEntity.ok(credential);
    }
}
