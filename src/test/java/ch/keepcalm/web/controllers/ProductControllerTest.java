package ch.keepcalm.web.controllers;

import ch.keepcalm.web.LeafApplication;
import ch.keepcalm.web.domain.Product;
import ch.keepcalm.web.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by marcelwidmer on 14/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LeafApplication.class})
@WebIntegrationTest
@ActiveProfiles("junit, junit-integration")
public class ProductControllerTest {


    @Value("${local.server.port}")
    int port;

    RestTemplate restTemplate = new TestRestTemplate();

    private static final String DESCRIPTION_FIELD = "description";
    private static final String PRODUCTS_RESOURCE = "/api/products/";
    private static final String PRODUCT_RESOURCE = "/api/products/{id}";
    private static final String FIRST_PRODUCT_ID = "1234";
    private static final String FIRST_PRODUCT_PRICE = "18.95";
    private static final String FIRST_PRODUCT_DESCRIPTION = "First product";
    private static final String SECOND_PRODUCT_DESCRIPTION = "Second product";
    private static final String THIRD_PRODUCT_DESCRIPTION = "Third product";


    private static final Product FIRST_ITEM = Product.newBuilder()
            .productId(FIRST_PRODUCT_ID)
            .description(FIRST_PRODUCT_DESCRIPTION)
            .price(new BigDecimal(FIRST_PRODUCT_PRICE))
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


    private Product firstItem;
    private Product secondItem;

    @Before
    public void setUp() {
        repository.deleteAll();
        firstItem = repository.save(FIRST_ITEM);
        secondItem = repository.save(SECOND_ITEM);
    }


    @Test
    public void testRequest() throws Exception {
        URI uri = URI.create("http://localhost:" + port + PRODUCTS_RESOURCE);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));

        ResponseEntity<String> responseOne = restTemplate.getForEntity(uri + String.valueOf(firstItem.getId()), String.class);
        assertThat(responseOne.getStatusCode(), is(equalTo(HttpStatus.OK)));
        System.out.println(responseOne);

        get(uri + String.valueOf(firstItem.getId())).
                then().
                assertThat().body("productId", equalTo(FIRST_PRODUCT_ID)).
                and().
                assertThat().body("description", equalTo(FIRST_PRODUCT_DESCRIPTION)).
                and().
                assertThat().body("imageUrl", equalTo(null));
        // TODO: 01/05/16   price test.
                /*
                and().
               assertThat().body("price", is(FIRST_PRODUCT_PRICE));
                */


/*
        when()
                .get(PRODUCTS_RESOURCE)
                .then()
                .statusCode(HttpStatus.OK)
                .body(DESCRIPTION_FIELD, hasItems(FIRST_PRODUCT_DESCRIPTION, SECOND_PRODUCT_DESCRIPTION));
*/
    }
}
