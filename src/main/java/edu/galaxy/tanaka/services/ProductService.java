package edu.galaxy.tanaka.services;

import java.util.List;
import java.util.Optional;
import edu.galaxy.tanaka.entities.Product;

public interface ProductService {

	List<Product> findAllOrFilterByName(String name);
	Optional<Product> findById(Long id);
	Product save(Product product);
	Product udpate(Long id, Product product);
	Product udpateStock(Long id, Product product);
	void delete(Long id);
	
}
