package edu.galaxy.tanaka.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.galaxy.tanaka.entities.Product;
import edu.galaxy.tanaka.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAllOrFilterByName(String name) {
		Optional<String> optionalName = Optional.ofNullable(name);
		if (optionalName.isPresent()) {
			return productRepository.findByNameLike("%"+name+"%");
		}
		return productRepository.findByState(true);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product product) {
		product.setState(true);
		return productRepository.save(product);
	}

	@Override
	public Product udpate(Long id, Product product) {
		Optional<Product> oProduct = productRepository.findById(id);
		if (oProduct.isPresent()) {
			Product productToUpdate = oProduct.get();
			productToUpdate.setName(product.getName());
			productToUpdate.setDescription(product.getDescription());
			productToUpdate.setStock(product.getStock());
			productToUpdate.setPrice(product.getPrice());
			productToUpdate.setImage(product.getImage());
			return productRepository.save(productToUpdate);
		}
		return productRepository.save(product);
	}

	@Override
	public Product udpateStock(Long id, Product product) {
		Optional<Product> oProduct = productRepository.findById(id);
		if (!oProduct.isPresent()) {
			throw new RuntimeException("Product not found");
		}
		Product productToUpdate = oProduct.get();
		productToUpdate.setStock(product.getStock());
		return productRepository.save(productToUpdate);
	}

	@Override
	public void delete(Long id) {
		Product oProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		oProduct.setState(false);
		productRepository.save(oProduct);
	}

}
