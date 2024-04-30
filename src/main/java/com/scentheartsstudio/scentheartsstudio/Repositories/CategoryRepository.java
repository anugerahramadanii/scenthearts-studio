package com.scentheartsstudio.scentheartsstudio.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scentheartsstudio.scentheartsstudio.DTO.InterCategoryDTO;
import com.scentheartsstudio.scentheartsstudio.Entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // get all categories
    @Query(nativeQuery = true, value = "select * from t_category where is_delete = false")
    public List<InterCategoryDTO> getAllCategories();

    // cek initial sudah ada atau belum
    @Query(nativeQuery = true, value = "select exists (select * from t_category where initial ilike :initial and is_delete = false)")
    public Boolean isInitialExists(@Param("initial") String initial);

    // cek name sudah ada atau belum
    @Query(nativeQuery = true, value = "select exists (select * from t_category where name ilike :name and is_delete = false)")
    public Boolean isNameExists(@Param("name") String name);
}
