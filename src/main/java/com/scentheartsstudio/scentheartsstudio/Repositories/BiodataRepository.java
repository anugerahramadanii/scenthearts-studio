package com.scentheartsstudio.scentheartsstudio.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.DTO.InterProfileDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.BiodataEntity;

@Repository
public interface BiodataRepository extends JpaRepository<BiodataEntity, Long> {

    @Query(nativeQuery = true, value = "select firstname, lastname, mobile_phone, r.id as role_id, r.name as role, email\r\n"
            + //
            "from m_biodata b \r\n" + //
            "inner join m_user u on\r\n" + //
            "b.id = u.biodata_id\r\n" + //
            "inner join m_role r on\r\n" + //
            "r.id = u.role_id\r\n" + //
            "where u.id = 1 \t")
    public InterProfileDTO getProfileByUserId(@Param("userId") long user_id);
}
