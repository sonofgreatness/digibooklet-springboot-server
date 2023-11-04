package com.digibooklet.server.data_access_layer.repositories;

import com.digibooklet.server.data_access_layer.models.backup.Backup;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
      SELECT  \s
      (SELECT  backup_id  AS my_id FROM  backup_data \s 
      WHERE timestamp =(SELECT MIN(timestamp) FROM backup_data )\s
      AND user_id = :id
      LIMIT 1
      )\s
      """,  nativeQuery=true)
    Backup deleteOldestBackup(String id);

}
