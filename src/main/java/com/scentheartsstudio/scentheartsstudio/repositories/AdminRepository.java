package com.scentheartsstudio.scentheartsstudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.entities.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

}
