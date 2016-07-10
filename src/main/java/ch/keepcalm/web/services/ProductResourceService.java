package ch.keepcalm.web.services;

import ch.keepcalm.web.api.ProductResource;
import ch.keepcalm.web.api.ProductResourceAssembler;
import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelwidmer on 22/03/16.
 */
// tag::ProductResourceService[]
@Service
public class ProductResourceService {


    private ProductRepository productRepository;
    private ProductResourceAssembler productResourceAssembler;

    @Autowired
    public void setProductRepository(ProductRepository productRepository, ProductResourceAssembler productResourceAssembler) {
        this.productRepository = productRepository;
        this.productResourceAssembler = productResourceAssembler;
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
     * TODO: 10/07/16 HATEOAS
     * Convert model to Resource
     *
     * @param productId
     * @return
     */
    @Transactional
    public ProductResource findOne(Integer productId) {
        ch.keepcalm.web.model.Product model = productRepository.findOne(productId);
        ProductResource productResource = productResourceAssembler.toResource(convertToProduct(model));
        return productResource;
    }

    /**
     * TODO: 10/07/16 HATEOAS
     * Convert model to resource
     *
     * @return
     */
    @Transactional
    public List<ProductResource> listAll() {
        List<ProductResource> products = new ArrayList<ProductResource>();
        for (ch.keepcalm.web.model.Product model : productRepository.findAll()) {
            products.add(productResourceAssembler.toResource(convertToProduct(model)));
        }
        return products;
    }


}
// end::ProductResourceService[]
