package com.digibooklet.server.data_access_layer.models.transaction;

import com.digibooklet.server.data_access_layer.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "transaction_data")
public class Transaction {

    @Id
    private String transactionId;
    private Timestamp timestamp;
    private String customerName;
    private String customerId ;
    private String customerPhone;
    private Boolean transactionType;
    private Float amount;


    @Lob
    private Byte[] signature;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;





/*
    @Override
    public String toString(){
        return  "transactionId = " +transactionId  +"timestamp  =" + timestamp
                +"customername = "+ customerName + "\n"
                +"customerId = "+customerId  + customerPhone + "\n"
                +"transaction type  = "+transactionType+
                "amount = " +amount +"Signature = "+ signature;
    }
*/
}
