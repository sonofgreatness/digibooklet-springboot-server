package com.digibooklet.server.controller;

import com.digibooklet.server.service_layer.BackupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
@AllArgsConstructor
public class BackupController {


   private final BackupService service;


    @GetMapping("/api/v1/backup/download")
    public ResponseEntity<byte[]> download(@RequestParam
                                            int index,
                                           @RequestParam
                                           String username
                                           ) throws Exception {

            return  service.downloadBackUpFile(index, username);
    }
    @PostMapping("/api/v1/backup/upload")
    public ResponseEntity<String> upload(@RequestParam("file")
                                             MultipartFile file,
                                         @RequestParam
                                         String username

                                         ) throws Exception {

        try {
            service.uploadBackUpFile(username, file.getBytes());
            return ResponseEntity.ok("SUCCESSFUL UPLOAD");
        }
        catch (Exception e)
        {
            throw new Exception("FAILED UPLOAD\n"+e.getMessage());
        }
    }



}
