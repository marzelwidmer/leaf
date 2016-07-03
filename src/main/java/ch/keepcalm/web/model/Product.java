package ch.keepcalm.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by marcelwidmer on 21/03/16.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @Size(min=1, max=36)
    private String productId;
    @Size(min=2, max=30)
    private String description;
    private String imageUrl;
    @NotNull
    private BigDecimal price;

    /**
     * Used for Entity
     */
    public Product(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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



    private Product(Builder builder) {
        setId(builder.id);
        setVersion(builder.version);
        setProductId(builder.productId);
        setDescription(builder.description);
        setImageUrl(builder.imageUrl);
        setPrice(builder.price);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * {@code Product} builder static inner class.
     */
    public static final class Builder {
        private Integer id;
        private Integer version;
        private String productId;
        private String description;
        private String imageUrl;
        private BigDecimal price;

        private Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Integer val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code version} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code version} to set
         * @return a reference to this Builder
         */
        public Builder version(Integer val) {
            version = val;
            return this;
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