package com.digibooklet.server.controller;

import com.digibooklet.server.data_access_layer.models.transaction.TransactionRequest;
import com.digibooklet.server.service_layer.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TransactionController {


    private  final TransactionService service;

    @PostMapping("/api/v1/transact/register")
    public ResponseEntity<String> register(
            @RequestBody
            TransactionRequest request,
            @RequestParam
            String username
            ) throws Exception {

        return service.addTransaction(username, request);
    }


    @GetMapping("/api/v1/transact/all")
    public ResponseEntity getAllTransactions(
            @RequestParam
            String username
    ) throws Exception {

        return service.getAllTransactionsByUser(username);
    }

}
