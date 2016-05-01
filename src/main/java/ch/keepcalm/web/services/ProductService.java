package ch.keepcalm.web.services;

import ch.keepcalm.web.domain.Product;

/**
 * Created by marcelwidmer on 22/03/16.
 */
public interface ProductService {

    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product getProductByProductId(String id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
