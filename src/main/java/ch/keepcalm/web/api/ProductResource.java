package ch.keepcalm.web.api;

import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

/**
 * Created by marcelwidmer on 10/07/16.
 */
public class ProductResource extends ResourceSupport {

    private String productId;
    private String description;
    private String imageUrl;
    private BigDecimal price;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}