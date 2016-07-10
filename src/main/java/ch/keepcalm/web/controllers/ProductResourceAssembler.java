package ch.keepcalm.web.controllers;

import ch.keepcalm.web.domain.Product;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by marcelwidmer on 10/07/16.
 */
@Component
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {
    public ProductResourceAssembler() {
        super(ProductRestController.class, ProductResource.class);
    }

    public ProductResource toResource(Product product) {
        ProductResource productResource = createResourceWithId(product.getId(), product); // adds a "self" link
        // TODO: copy properties from product to productResource
        productResource.setPrice(product.getPrice());
        productResource.setImageUrl(product.getImageUrl());
        productResource.setProductId(product.getProductId());
        productResource.setDescription(product.getDescription());
        return productResource;
    }
}

