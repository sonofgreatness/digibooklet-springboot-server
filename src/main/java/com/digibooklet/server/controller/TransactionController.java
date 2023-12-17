package com.digibooklet.server.controller;

import com.digibooklet.server.data_access_layer.models.transaction.TransactionRequest;
import com.digibooklet.server.service_layer.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionController {


    private  final TransactionService service;

    @PostMapping("/api/v1/transact/register")
    public ResponseEntity<String> register(
            @RequestBody
            TransactionRequest request
            ) throws Exception {

        String username = getCurrentUsername();
        return ResponseEntity.ok(service.addTransaction(username,request));
    }



    @GetMapping("/api/v1/transact/all")
    public ResponseEntity<List<TransactionRequest>>
     getAllTransactions() throws Exception {
        String username = getCurrentUsername();
        return ResponseEntity.ok(service.getAllTransactionsByUser(username));
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
