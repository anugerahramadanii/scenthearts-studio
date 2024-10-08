package com.scentheartsstudio.scentheartsstudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value = "select * from m_user where email = :email and password = :password")
    public UserEntity getEmailAndPasswordUser(@Param("email") String email, @Param("password") String password);

    @Query(nativeQuery = true, value = "select exists (select * from m_user where email ilike :email and is_delete = false and (is_locked = false or is_locked is null))")
    public Boolean isEmailExists(@Param("email") String email);

    @Query(nativeQuery = true,
    value = "select * from m_user where id = :userId and is_delete =false")
    public Long getUserId(@Param("userId") Long id);

    @Query(nativeQuery = true, 
    value = "SELECT * FROM m_user where email = :email and is_delete = false")
    public UserEntity getUserByEmail(@Param("email") String email);

    @Query(nativeQuery = true,
    value = "select r.code from m_user u inner join m_role r on r.id = u.role_id where u.id = :userId and u.is_delete = false")
    public String getRoleByUserId(@Param("userId") Long userId);
}
