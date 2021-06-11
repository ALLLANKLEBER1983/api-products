package com.compassouol.productms.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compassouol.productms.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Transactional(readOnly=true)
	@Query(" SELECT DISTINCT obj FROM Product obj WHERE obj.name =:q AND obj.price BETWEEN :min_price AND :max_price   ")
	List<Product>search(@Param("q") String q,@Param("min_price") Double min_price,@Param("max_price") Double max_price);

}
