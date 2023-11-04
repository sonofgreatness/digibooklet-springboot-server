package com.digibooklet.server.data_access_layer.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {
    private String userName;// momo_number
    private String momoName;
    private String password;
    private String email;
    private Role role;
}
