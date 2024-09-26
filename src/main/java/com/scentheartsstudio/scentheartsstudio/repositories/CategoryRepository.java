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
    @Query(nativeQuery = true,
            value = "select * from t_category where is_delete = false")
    public List<InterCategoryDTO> getAllCategories();

    // Get total data
    @Query(nativeQuery = true,
            value = "select count(*) from t_category where is_delete = false")
    public Integer getTotalData();

    // get all categories with pagination, and search
    @Query(nativeQuery = true,
            value = "select id, name, image_path, active from t_category \n"
                    + "where(name ilike '%' || :keyword || '%') and is_delete = false limit :limit offset :offset")
    public List<InterCategoryDTO> searchPaginateCategories(@Param("keyword") String keyword,
                                                              @Param("offset") Integer offset,
                                                              @Param("limit") Integer limit);

    // get all categories with pagination, sorting ASC, and search
    @Query(nativeQuery = true,
            value = "select id, name, image_path, active from t_category \n"
                    + "where(name ilike '%' || :keyword || '%') and is_delete = false order by name asc limit :limit offset :offset")
    public List<InterCategoryDTO> searchPaginateCategoriesAsc(@Param("keyword") String keyword,
                                                              @Param("offset") Integer offset,
                                                              @Param("limit") Integer limit);

    // get all categories with pagination, sorting DESC, and search
    @Query(nativeQuery = true,
            value = "select id, name, image_path, active from t_category \n"
                    + "where(name ilike '%' || :keyword || '%') and is_delete = false order by name asc limit :limit offset :offset")
    public List<InterCategoryDTO> searchPaginateCategoriesDesc(@Param("keyword") String keyword,
                                                              @Param("offset") Integer offset,
                                                              @Param("limit") Integer limit);

    // cek name sudah ada atau belum
    @Query(nativeQuery = true,
            value = "select exists (select * from t_category where name ilike :name and is_delete = false)")
    public Boolean isNameExists(@Param("name") String name);

    @Query(nativeQuery = true,
    value = "SELECT EXISTS (SELECT * FROM t_category where name ilike :name and name not ilike :oldName and is_delete = false)")
    public Boolean isNameExistsUpdate(@Param("name") String name, @Param("oldName") String oldName);

}
