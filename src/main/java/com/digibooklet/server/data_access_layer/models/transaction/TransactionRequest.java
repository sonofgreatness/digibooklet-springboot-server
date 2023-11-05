package com.digibooklet.server.data_access_layer.models.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class TransactionRequest {


    private String transactionId;
    private Timestamp timestamp;
    private String customerName;
    private String customerId ;
    private String customerPhone;
    private Boolean transactionType;
    private Float amount;
    private String username ;
    private Byte[] signature;

}
