package com.scentheartsstudio.scentheartsstudio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.entities.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

        @Query(nativeQuery = true, value = "with myTable as\r\n" + //
                        "(select email, token from t_token where email ilike :email and is_delete = false order by id desc limit 1)\r\n"
                        + //
                        "select exists(select * from myTable where token ilike :token)")
        public Boolean isTokenCorrect(@Param("email") String email, @Param("token") String token);

        @Query(nativeQuery = true, value = "with myTable as\r\n" + //
                        "(select email, token, expired_on from t_token where email ilike :email and is_delete = false order by id desc limit 1)\r\n"
                        + //
                        "select exists(select * from myTable where expired_on < now())")
        public Boolean isTokenExpired(@Param("email") String email);

        @Query(nativeQuery = true,
        value = "select * from t_token where token ilike :token and is_delete = false")
        Optional<TokenEntity> getToken(@Param("token") String token);

//        @Query(nativeQuery = true,
//        value = "select id from t_token where email = :email and is_delete = false and (is_expired = false or is_expired is null) order by id desc limit 1")
//        public Long isEmailExist(@Param("email") String email );
}
