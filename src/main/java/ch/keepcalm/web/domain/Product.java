package ch.keepcalm.web.domain;

import java.math.BigDecimal;

/**
 * Simple Product domain object
 * Created by marcelwidmer on 03/07/16.
 */
public class Product {

    private String productId;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    public Product() {
    }

    public Product(String productId, String description, String imageUrl, BigDecimal price) {
        this.productId = productId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    private Product(Builder builder) {
        setProductId(builder.productId);
        setDescription(builder.description);
        setImageUrl(builder.imageUrl);
        setPrice(builder.price);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

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



    /**
     * {@code Product} builder static inner class.
     */
    public static final class Builder {
        private String productId;
        private String description;
        private String imageUrl;
        private BigDecimal price;

        private Builder() {
        }

        /**
         * Sets the {@code productId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code productId} to set
         * @return a reference to this Builder
         */
        public Builder productId(String val) {
            productId = val;
            return this;
        }

        /**
         * Sets the {@code description} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code description} to set
         * @return a reference to this Builder
         */
        public Builder description(String val) {
            description = val;
            return this;
        }

        /**
         * Sets the {@code imageUrl} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code imageUrl} to set
         * @return a reference to this Builder
         */
        public Builder imageUrl(String val) {
            imageUrl = val;
            return this;
        }

        /**
         * Sets the {@code price} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code price} to set
         * @return a reference to this Builder
         */
        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        /**
         * Returns a {@code Product} built from the parameters previously set.
         *
         * @return a {@code Product} built with parameters of this {@code Product.Builder}
         */
        public Product build() {
            return new Product(this);
        }
    }
}
