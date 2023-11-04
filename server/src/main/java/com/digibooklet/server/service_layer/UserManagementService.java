package com.digibooklet.server.service_layer;

import com.digibooklet.server.data_access_layer.models.backup.Backup;
import com.digibooklet.server.data_access_layer.models.user.*;
import com.digibooklet.server.data_access_layer.repositories.BackUpRepository;
import com.digibooklet.server.data_access_layer.repositories.TransactionRepository;
import com.digibooklet.server.data_access_layer.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserManagementService {

    @Autowired
    private final UserRepository userRepository;
    private  final TransactionRepository transactionRepository;
    private final BackUpRepository backUpRepository;
    /*
    public ResponseEntity<AuthenticationResponse>
          registerUser (UserRegistrationRequest request) throws  Exception{

        try {
            User user = User.builder()
                    .userName(request.getUserName())
                    .email(request.getEmail())
                    .momoName(request.getEmail())
                    .password(request.getPassword())
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            return ResponseEntity.ok(new AuthenticationResponse("registered"));
        }
        catch (Exception e)
        {
            throw  new Exception(e.getMessage()) ;
        }
    }

*/


/*
    public ResponseEntity<AuthenticationResponse>
    registerAdmin (UserRegistrationRequest request) throws  Exception{

        try {
            User user = User.builder()
                    .userName(request.getUserName())
                    .email(request.getEmail())
                    .momoName(request.getEmail())
                    .password(request.getPassword())
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);
            return ResponseEntity.ok(new AuthenticationResponse("registered"));
        }
        catch (Exception e)
        {
            throw  new Exception(e.getMessage()) ;
        }
    }

*/
/*
    public ResponseEntity<AuthenticationResponse>
    authenticate (AuthenticationRequest request) throws  Exception{

        try {
            return ResponseEntity.ok(new AuthenticationResponse("authenticated"));
        }
        catch (Exception e)
        {
            throw  new Exception(e.getMessage()) ;
        }
    }

*/

    public  List<User> getAllUsers() throws Exception
    {
         try{
              return
                      userRepository.findAll();
         }
         catch (Exception e)
         {
             throw new Exception(e.getMessage());
         }

    }


    public List<UserStats> getUserStats(){
        List<User> users = userRepository.findAll();
        ArrayList<UserStats> userStats = new ArrayList<>();

        for (User eachUser : users)
        {

            int sum_of_transactions = getSumOfTransactions(eachUser);
            int sum_of_backups = getSumOfBackUps(eachUser);

           UserStats  stat = UserStats.
                    builder()
                    .userName(eachUser.getUserName())
                    .momoName(eachUser.getMomoName())
                    .email(eachUser.getEmail())
                    .transactions(sum_of_transactions)
                    .backups(sum_of_backups)
                    .build();

           userStats.add(stat);
        }

        return  userStats;
    }



    /**
     *gets number of transactions associated user account
     *  has stored in the db
     */
    private int getSumOfTransactions(User eachUser) {
        return transactionRepository.
                findAllValidTransactionByUser(eachUser.getUserName()).size();

    }


    /**
     *gets number of backups associated user account
     *  has stored in the db
     */
    private int getSumOfBackUps(User eachUser) {
        return  backUpRepository.
                findAllValidBackupsByUser(eachUser.getUserName()).size();
    }

}
