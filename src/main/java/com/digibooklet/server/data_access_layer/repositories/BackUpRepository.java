package com.digibooklet.server.data_access_layer.repositories;

import com.digibooklet.server.data_access_layer.models.backup.Backup;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BackUpRepository extends JpaRepository<Backup, String> {

    @Transactional
    @Query(value = """
      select * from backup_data AS t inner join user_data u\s
      on t.user_id = u.user_name\s
      where u.user_name = :id\s
      """,  nativeQuery=true)
    List<Backup> findAllValidBackupsByUser(String id);


    /**
     * Deletes the oldest backupdata  record , for user
     *     with  username = id
     * @param  id  The username   as stored in the user_data table
     * */
    @Query(value = """
           DELETE FROM backup_data\s
            WHERE (user_id, timestamp)IN (\s
              SELECT user_id, MIN(timestamp)\s
                  FROM backup_data\s
                  WHERE user_id = :id\s
                  GROUP BY user_id);\s
            """,  nativeQuery=true)
    int deleteOldestBackup(String id);


    /**
     * retrieves  backup  records data for particular user
     * @param  id  username in db
     * @return   List<BackupSubDetail>
     *        each POJO has two attributes -> backupId : String
     *                                     -> timestamp : Timestamp
     ***/
    @Query(value = """
           SELECT backup_id, timestamp
            FROM\s
            backup_data   WHERE user_id =:id\s
            """,  nativeQuery=true)
    List<Map<String, Object>> getBackupdetails(String id);

    @Query(value = """
           SELECT * FROM backup_data \s 
           WHERE user_id =:id  AND backup_id =:itemId\s
            """,  nativeQuery=true)
    List<Backup> getSpecificBackup(String id, String itemId);




}
