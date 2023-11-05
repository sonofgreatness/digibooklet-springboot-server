package com.digibooklet.server.data_access_layer.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@AllArgsConstructor
public class UserStats {


    private String userName;// momo_number
    private String momoName;
    private String email;
    private int transactions;
    private  int backups;
}
