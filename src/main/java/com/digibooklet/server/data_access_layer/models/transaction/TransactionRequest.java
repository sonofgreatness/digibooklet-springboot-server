package com.digibooklet.server.data_access_layer.models.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private String time;
    private Float amount;
    private String username ;
    private List<Integer> signature;
    public  Byte[] convertIntegerListToByteArray(List<Integer> integerList) {
        Byte[] byteArray = new Byte[integerList.size()];

        for (int i = 0; i < integerList.size(); i++) {
            // Convert Integer to byte
            byte byteValue = integerList.get(i).byteValue();

            // Convert byte to Byte
            byteArray[i] = Byte.valueOf(byteValue);
        }

        return byteArray;
    }
}
