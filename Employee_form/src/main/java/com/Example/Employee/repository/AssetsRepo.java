package com.Example.Employee.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Example.Employee.entity.Assets;

@Repository
public interface AssetsRepo extends JpaRepository<Assets, Integer> {
	@Query("SELECT a.slNo FROM Assets a WHERE a.subcategory.id = :subcategoryId")
    List<String> findSerialNumbersBySubcategoryId(@Param("subcategoryId") Long subcategoryId);

    @Query("SELECT DISTINCT a.companyname FROM Assets a WHERE a.subcategory.id = :subcategoryId")
    List<String> findCompaniesBySubcategoryId(@Param("subcategoryId") Long subcategoryId);

    @Query("SELECT a FROM Assets a WHERE a.categoryName = :category AND a.subcatgoryName = :subcategory AND a.companyname = :companyName AND a.slNo = :serialNumber")
    Optional<Assets> findByCategoryAndSubcategoryAndCompanyNameAndSerialNumber(
            @Param("category") String category,
            @Param("subcategory") String subcategory,
            @Param("companyName") String companyName,
            @Param("serialNumber") String serialNumber);

}
