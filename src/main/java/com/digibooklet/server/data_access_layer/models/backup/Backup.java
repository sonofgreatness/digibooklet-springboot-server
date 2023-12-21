package com.digibooklet.server.data_access_layer.models.backup;

import com.digibooklet.server.data_access_layer.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "backup_data")
public class Backup {

    @Id
    @UuidGenerator
    private String backupId;
    private Timestamp timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] file;

    public String toString()
    {
        return
                "id = "+ backupId+ " \n "+
                "time = "+ timestamp + " \n "
                +"\n"+ file.length+"\n"
                +"user = " + user;

    }

}
