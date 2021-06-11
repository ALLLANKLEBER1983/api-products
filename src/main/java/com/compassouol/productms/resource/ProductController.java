package com.compassouol.productms.resource;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.productms.model.Product;
import com.compassouol.productms.repository.ProductRepository;
import com.compassouol.productms.service.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	private ProductRepository productRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product adicionar(@Valid @RequestBody Product product) {
		return productService.salvar(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> atualizar(@Valid @PathVariable Long id,
			@RequestBody Product product){
		if(!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		product.setId(id);
		product = productService.salvar(product);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> buscar(@PathVariable Long id){
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<Product> listar(){
		return productRepository.findAll();
	}
	
	
	  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<List<Product>> findAllByFilters(
	  
	  @RequestParam(value = "q", required = false) String nameOrDescription,
	  
	  @RequestParam(value = "min_price", required = false) Double minPrice,
	  
	  @RequestParam(value = "max_price", required = false) Double maxPrice) {
	  
	  List<Product> product = productRepository.search(nameOrDescription, minPrice,
	  maxPrice);
	  
	 if(product.isEmpty()) {
		 return ResponseEntity.notFound().build();
	 }
	  
	  return ResponseEntity.ok(product); 
	  }
	  
	  @DeleteMapping("/{productId}")
	  public ResponseEntity<Void> remover(@PathVariable Long productId){
		  if(!productRepository.existsById(productId)) {
			 return ResponseEntity.notFound().build();
		  }
		   productService.excluir(productId);
		   return ResponseEntity.notFound().build();
	  }
	  
	  
	 
}
