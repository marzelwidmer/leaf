package ch.keepcalm.web.api;

import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.services.ProductResourceService;
import ch.keepcalm.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by marcelwidmer on 11/04/16.
 */
@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductResourceService service;

    @Autowired
    public void setProductService(ProductResourceService service) {
        this.service = service;
    }

    /**
     * TODO: 10/07/16 HATEOAS
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public HttpEntity<ProductResource> findOne(@PathVariable("productId") Integer productId) {
        ProductResource productResource = service.findOne(productId);
        return new ResponseEntity<>(productResource, HttpStatus.OK);
    }

    /**
     * TODO: 10/07/16 HATEOAS
     *
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProductResource>> showAll() {
        List<ProductResource> productResources = service.listAll();
        return new ResponseEntity<List<ProductResource>>(productResources, HttpStatus.OK);
    }


    /**
     * Update One Product
     * @param updatedProduct
     * @param id
     * @return
     */
   /* @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product updatedProduct, @PathVariable Integer id) {

        Product product = Product.newBuilder()
                .productId(updatedProduct.getProductId())
                .description(updatedProduct.getDescription())
                .imageUrl(updatedProduct.getImageUrl())
                .price(updatedProduct.getPrice())
                .build();

        return productResourceService.saveProduct(product);
    }*/

    /**
     * Delete One Product
     *
     * @param product
     * @return
     */
   /* @RequestMapping(value = "/product/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productResourceService.saveProduct(product);
    }*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public void handleNotFound() {
    }
}
