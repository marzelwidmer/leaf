package ch.keepcalm.web.controllers;

import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

/**
 * Created by marcelwidmer on 11/04/16.
 */
@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product updatedProduct, @PathVariable Integer id) {

        Product product = Product.newBuilder()
                .productId(updatedProduct.getProductId())
                .description(updatedProduct.getDescription())
                .imageUrl(updatedProduct.getImageUrl())
                .price(updatedProduct.getPrice())
                .build();

        return productService.saveProduct(product);
    }

    /**
     * List All
     * @return
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String list(){
        return productService.listAllProducts().toString();
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public void handleNotFound() {
    }
}
