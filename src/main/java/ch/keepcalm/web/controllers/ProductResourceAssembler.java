package ch.keepcalm.web.controllers;

import ch.keepcalm.web.domain.Product;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by marcelwidmer on 10/07/16.
 */
@Component
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {
    /**
     * Constructor
     */
    public ProductResourceAssembler() {
        super(ProductRestController.class, ProductResource.class);
    }

    /**
     * Convert domain product to resource product
     * @param product
     * @return
     */
    @Override
    public ProductResource toResource(Product product) {
        // TODO: 10/07/16 self URL is wrong...
        ProductResource productResource = createResourceWithId("product/" + product.getId(), product); // adds a "self" link

        // TODO: copy properties from product to productResource
        productResource.setPrice(product.getPrice());
        productResource.setImageUrl(product.getImageUrl());
        productResource.setProductId(product.getProductId());
        productResource.setDescription(product.getDescription());
        return productResource;
    }
}

