package com.scentheartsstudio.scentheartsstudio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.dto.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // get all categories
    @Query(nativeQuery = true, value = "select * from t_category where is_delete = false")
    public List<InterCategoryDTO> getAllCategories();

    // cek name sudah ada atau belum
    @Query(nativeQuery = true, value = "select exists (select * from t_category where name ilike :name and is_delete = false)")
    public Boolean isNameExists(@Param("name") String name);

    @Query(nativeQuery = true,
    value = "SELECT EXISTS (SELECT * FROM t_category where name ilike :name and name not ilike :oldName and is_delete = false)")
    public Boolean isNameExistsUpdate(@Param("name") String name, @Param("oldName") String oldName);

    @Query(nativeQuery = true,
    value = "select id from m_user where id = :userId and is_delete = false")
    public Long getUserId(@Param("userId") Long user_id);

    @Query(nativeQuery = true,
    value = "select id from t_category where id = :categoryId and is_delete = false")
    public Long getCategoryId(@Param("categoryId") Long category_id);
}
