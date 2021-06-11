package com.compassouol.productms.service;



import javax.transaction.Transactional;


import org.springframework.stereotype.Service;




import com.compassouol.productms.model.Product;
import com.compassouol.productms.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	
	@Transactional
	public Product save(Product product) {
		
			return productRepository.save(product);
			
		
	}
	
	@Transactional
	public void delete(Long productId) {
		productRepository.deleteById(productId);
	}
}
