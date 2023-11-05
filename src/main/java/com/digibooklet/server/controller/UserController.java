package com.digibooklet.server.controller;

import com.digibooklet.server.data_access_layer.models.user.AuthenticationRequest;
import com.digibooklet.server.data_access_layer.models.user.AuthenticationResponse;
import com.digibooklet.server.data_access_layer.models.user.User;
import com.digibooklet.server.data_access_layer.models.user.UserRegistrationRequest;
import com.digibooklet.server.service_layer.UserManagementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private  final UserManagementService service;


    @PostMapping("/getAllUsers")
    public  ResponseEntity  getAllUsers() throws Exception {

        return  ResponseEntity.ok
                (service.getAllUsers().toString());
    }



}
