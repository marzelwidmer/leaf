package ch.keepcalm.web.services;

import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    // TODO: 22/03/16 why like this and not just with @Autowired ProductRepository
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }
}