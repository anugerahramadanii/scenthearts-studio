package com.scentheartsstudio.scentheartsstudio.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(nativeQuery = true, value = "select * from m_user where email = :email and password = :password")
    public UserEntity getEmailAndPasswordUser(@Param("email") String email, @Param("password") String password);

    @Query(nativeQuery = true, value = "select exists (select * from m_user where email ilike :email and is_delete = false and (is_locked = false or is_locked is null))")
    public Boolean isEmailExists(@Param("email") String email);

}
