package com.scentheartsstudio.scentheartsstudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.dto.InterProfileDTO;
import com.scentheartsstudio.scentheartsstudio.entities.BiodataEntity;

@Repository
public interface BiodataRepository extends JpaRepository<BiodataEntity, Long> {

    // profile
    @Query(nativeQuery = true, value = "select b.firstname, b,lastname, b.mobile_phone, b.image_path, u.email\n"
            + "from m_biodata b \n"
            + "inner join m_user u on\n"
            + "b.id = u.biodata_id\n"
            + "where u.id = :userId \t")
    public InterProfileDTO getProfileByUserId(@Param("userId") long user_id);
}
