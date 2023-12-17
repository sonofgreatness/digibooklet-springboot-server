package com.digibooklet.server.data_access_layer.repositories;

import com.digibooklet.server.data_access_layer.models.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, String> {


    @Transactional
    @Query(value = """
      select * from transaction_data AS t inner join user_data u\s
      on t.user_id = u.user_name\s
      where u.user_name = :id\s
      """,  nativeQuery=true)
    List<Transaction> findAllValidTransactionByUser(String id);

}

