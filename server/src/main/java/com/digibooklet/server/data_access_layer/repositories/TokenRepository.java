package com.digibooklet.server.data_access_layer.repositories;

import com.digibooklet.server.data_access_layer.models.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select * from token_data t inner join user_data u\s
      on t.user_id = u.user_name\s
      where u.user_name = :id and (t.expired = false or t.revoked = false)\s
      """, nativeQuery = true)
    List<Token> findAllValidTokenByUser(String id);
    Optional<Token> findByToken(String token);




}
