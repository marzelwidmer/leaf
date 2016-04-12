package ch.keepcalm.web.controllers;

import ch.keepcalm.web.config.RepositoryConfiguration;
import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.repositories.ProductRepository;
import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by marcelwidmer on 11/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("junit, junit-integration")
public class ProductRestControllerIT {

    private static final String DESCRIPTION_FIELD = "description";
    private static final String PRODUCTS_RESOURCE = "/api/products";
    private static final String PRODUCT_RESOURCE = "/api/products/{id}";
    private static final int NON_EXISTING_ID = 999;
    private static final String FIRST_PRODUCT_DESCRIPTION = "First product";
    private static final String SECOND_PRODUCT_DESCRIPTION = "Second product";
    private static final String THIRD_PRODUCT_DESCRIPTION = "Third product";

    private static final Product FIRST_ITEM = Product.newBuilder()
            .productId("1234")
            .description(FIRST_PRODUCT_DESCRIPTION)
            .price(new BigDecimal("18.95"))
            .build();


    private static final Product SECOND_ITEM = Product.newBuilder()
            .productId("4321")
            .description(FIRST_PRODUCT_DESCRIPTION)
            .price(new BigDecimal("11.50"))
            .description(SECOND_PRODUCT_DESCRIPTION)
            .build();

    private static final Product THIRD_ITEM = Product.newBuilder()
            .productId("567")
            .description(FIRST_PRODUCT_DESCRIPTION)
            .price(new BigDecimal("22.55"))
            .description(THIRD_PRODUCT_DESCRIPTION)
            .build();

    private ProductRepository repository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Value("${local.server.port}")
    private int serverPort;
    private Product firstItem;
    private Product secondItem;

    @Before
    public void setUp() {
        repository.deleteAll();
        firstItem = repository.save(FIRST_ITEM);
        secondItem = repository.save(SECOND_ITEM);
        RestAssured.port = serverPort;
    }

    @Test
    public void getItemsShouldReturnBothItems() {
        when()
                .get(PRODUCTS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(DESCRIPTION_FIELD, hasItems(FIRST_PRODUCT_DESCRIPTION, SECOND_PRODUCT_DESCRIPTION));
    }

}