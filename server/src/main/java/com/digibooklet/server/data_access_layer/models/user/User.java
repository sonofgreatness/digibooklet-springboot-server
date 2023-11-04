package com.digibooklet.server.data_access_layer.models.user;

import com.digibooklet.server.data_access_layer.models.backup.Backup;
import com.digibooklet.server.data_access_layer.models.transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data//from  Lombok , reduces boilerplate code, equivalent to  setting getter, setter and to string methods
@Builder// refer to design pattern builder
@NoArgsConstructor//..
@AllArgsConstructor//..
@Entity
@Table(name = "user_data")
public class User implements UserDetails {

    @Id
    private String userName;// momo_number
    private String momoName;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Backup> backups;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Enumerated(EnumType.STRING)
    private Role role;




    @Override
    public  String toString (){

        return "user name ="+ userName +"momo name =" +momoName +"/n"
                +"email = "+ email + " role ="
                +role.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



    @Override
    public String getUsername() {
        return userName;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserName() {
      return  userName;
    }
}
