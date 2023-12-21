package com.digibooklet.server.service_layer;

import com.digibooklet.server.data_access_layer.models.backup.Backup;
import com.digibooklet.server.data_access_layer.models.user.User;
import com.digibooklet.server.data_access_layer.repositories.BackUpRepository;
import com.digibooklet.server.data_access_layer.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Service
public class BackupService {

    @Autowired
    private final UserRepository userRepository;
    private  final BackUpRepository backUpRepository;


     public void  uploadBackUpFile(String username, byte[] file) throws Exception {

         User user = userRepository.findById(username).orElseThrow(
                 ()-> new Exception("Error User Not Found"));

         Timestamp time = new Timestamp(System.currentTimeMillis());

         Backup
                 backups = Backup.builder()
                 .file(file)
                 .timestamp(time)
                 .user(user)
                 .build();
         backUpRepository.save(backups);
         deleteExtraBackups(user);

     }



     /**
      * deleteExtraBackups  -- maintains  5 backup files per user,
      *                      if backups in db are more than 4 delete
      *                       the oldest  backup file
      * @param  user
     * */
   private void  deleteExtraBackups(User user){
       try {

           List<Backup> backups =  backUpRepository.findAllValidBackupsByUser(user.getUserName());
           if (backups.size() > 5)
                 backUpRepository.deleteOldestBackup(user.getUserName());
       }
       catch (Exception e)
       {
         System.out.println(e.getMessage());
       }

   }



   /**
    * gets the  backup file with  particular  id in
    * list of all backup files  from db ,  as filename = "backup_db.zip"
    *
    * @param backupId
    * @param username
    * @return: zip file  downloadable
    **/
   @Transactional
    public ResponseEntity<byte[]>downloadBackUpFile(String username, String backupId) throws  Exception {

       try {
           Backup document = backUpRepository.getSpecificBackup(username, backupId).get(0);

           return ResponseEntity.ok()
                   .contentType(MediaType.parseMediaType("application/zip"))
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "backup_db.zip" + "\"")
                   .body(document.getFile());
       }catch (Exception e) {

           throw  new Exception(e.getMessage());
       }

    }




    public  List<Backup> getAllBackUps() throws Exception
    {
        try{
            return  backUpRepository.findAll();
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }

    }

    public String getAllBackUpDetailsForUser(String username) throws Exception
    {
        try{

          String  result  = new ObjectMapper().writeValueAsString(backUpRepository.getBackupdetails(username));
          return  result;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }



}
