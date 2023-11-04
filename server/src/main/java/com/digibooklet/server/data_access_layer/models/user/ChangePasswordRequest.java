package com.digibooklet.server.data_access_layer.models.user;

import lombok.*;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
