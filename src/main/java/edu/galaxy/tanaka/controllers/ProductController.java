package edu.galaxy.tanaka.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.galaxy.tanaka.entities.Product;
import edu.galaxy.tanaka.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> findAllOrFilterByName(@RequestParam(required = false) String name) {
		return productService.findAllOrFilterByName(name);
	}
	
	@GetMapping("/{id}")
	public Optional<Product> findById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("/{id}")
	public Product udpate(@PathVariable Long id, @RequestBody Product product) {
		return productService.udpate(id, product);
	}
	
	@PatchMapping("/{id}")
	public Product udpateStock(@PathVariable Long id, @RequestBody Product product) {
		return productService.udpateStock(id, product);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}

}
