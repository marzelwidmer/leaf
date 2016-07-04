package ch.keepcalm.web.services;

import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelwidmer on 22/03/16.
 */
// tag::ProductService[]
@Service
public class ProductService {


    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Convert model product to domain product
     * @param model
     * @return domain product
     */
    private Product convertToProduct(ch.keepcalm.web.model.Product model) {
        Product product = Product.newBuilder()
                .Id(model.getId())
                .productId(model.getProductId())
                .description(((model.getDescription() == null) ? "N/A" : model.getDescription()))
                .imageUrl(((model.getImageUrl() == null) ? "N/A" : model.getImageUrl()))
                .price(model.getPrice()).build();
        return product;
    }



    /**
     * Convert domain product to model product
     * @param product
     * @return model product
     */
    private ch.keepcalm.web.model.Product convertToModel(Product product) {
        ch.keepcalm.web.model.Product model = ch.keepcalm.web.model.Product.newBuilder()
                .id(product.getId())
                .productId(product.getProductId())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice()).build();
        return model;
    }


    public List<Product> listAllProducts() {
        List<Product> products = new ArrayList<Product>();
        for (ch.keepcalm.web.model.Product model : productRepository.findAll()) {
            products.add(convertToProduct(model));
        }
        return products;
    }

    public Product getProductById(Integer id) {
        return convertToProduct(productRepository.findOne(id));
    }

    public Product getProductByProductId(String id) {
        return convertToProduct(productRepository.findByProductId(id));
    }

    public Product saveProduct(Product product) {
        return convertToProduct(productRepository.save(convertToModel(product))) ;
    }

    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        if (product == null) {
            // todo throw not found exception;
        }
        productRepository.delete(id);

    }

      /*public Product deleteProduct(Integer id) {
        Product product = getProductById(id);
        if (product == null){
            return null;
        }
        productRepository.delete(id);
        return product;
    }*/
}
// end::ProductService[]
