package com.digibooklet.server.data_access_layer.models.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Role{

    USER,
    ADMIN,
    MANAGER;

}
