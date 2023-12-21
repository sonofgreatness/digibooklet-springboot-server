package com.digibooklet.server.controller;

import com.digibooklet.server.service_layer.BackupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
public class BackupController {


   private final BackupService service;


    @Transactional
    @GetMapping("/api/v1/backup/download")
    public ResponseEntity<byte[]> download(@RequestParam
                                            String backupId
                                           ) throws Exception {
            String username = getCurrentUsername();
            return  service.downloadBackUpFile(username,backupId);
    }


    @GetMapping("/api/v1/backup/details")
    public ResponseEntity<String>//<List<BackupSubDetails>>
    getDetails() throws Exception {


        String username = getCurrentUsername();
        return  ResponseEntity.ok (service.getAllBackUpDetailsForUser(username));
    }
    @PostMapping("/api/v1/backup/upload")
    public ResponseEntity<String> upload(@RequestParam("file")
                                             MultipartFile file
                                         ) throws Exception {


        try {

            String username = getCurrentUsername();
            service.uploadBackUpFile(username, file.getBytes());
            return ResponseEntity.ok("SUCCESSFUL UPLOAD");
        }
        catch (Exception e)
        {
            throw new Exception("FAILED UPLOAD\n"+e.getMessage());
        }
    }


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // The principal is typically a UserDetails instance, and its getUsername() method returns the username
            return authentication.getName();
        } else {
            // Handle the case where the user is not authenticated, return an appropriate value or handle as needed
            return null;
        }
    }
}
