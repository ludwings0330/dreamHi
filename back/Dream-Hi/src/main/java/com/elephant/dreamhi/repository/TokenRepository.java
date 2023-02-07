package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUserId(Long userId);

    @Query("select t from Token t where t.accessToken=:accessToken")
    Optional<Token> findByUserIdAndAcessToken(@Param("accessToken") String accessToken);

    @Query("delete from Token t where t.userId=:userId")
    @Modifying
    Integer deleteByUserId(@Param("userId") Long userId);

}

