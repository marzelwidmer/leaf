package ch.keepcalm.web.controllers;

import ch.keepcalm.web.LeafApplication;
import ch.keepcalm.web.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LeafApplication.class)
public class ProductRestControllerDocumentation {

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation(
            "target/generated-snippets"
    );

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    /**
     * Set up MockMVC
     */
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    /**
     * All documentation production happens here.
     *
     * @throws Exception
     */
    @Test
    public void documentProductResource() throws Exception {
        Product product = Product.newBuilder()
                .productId(UUID.randomUUID().toString())
                .description("foobar")
                .imageUrl("http://fobar-image.com/image/1")
                .price(new BigDecimal(6.99))
                .build();

        createProduct(product);

    }

    private void createProduct(Product product) throws Exception {

        RestDocumentationResultHandler document = documentPrettyPrintReqResp("create-product");

        document.snippets(
                requestFields(productFields(false)),
                responseFields(productFields(false))
        );

        this.mockMvc.perform(post("/api/products/")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document);
    }

    /**
     * Pretty print request and response
     *
     * @param useCase the name of the snippet
     * @return RestDocumentationResultHandler
     */
    private RestDocumentationResultHandler documentPrettyPrintReqResp(String useCase) {
        return document(useCase,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));
    }


    /**
     * User fields used in requests and responses.
     * An array field equivalent can be proveded
     *
     * @param isJsonArray if the fields are used in a JsonArray
     * @return
     */
    private static FieldDescriptor[] productFields(boolean isJsonArray) {
        return isJsonArray ?
                new FieldDescriptor[]{
                        fieldWithPath("[]").description("Product list"),
                        fieldWithPath("[].productId").description(PRODUCT_ID_DESCRIPTION),
                        fieldWithPath("[].description").description(PRODUCT_DESCRIPTION),
                        fieldWithPath("[].imageUrl").description(PRODUCT_IMAGE_URL_DESCRIPTION),
                        fieldWithPath("[].price").description(PRODUCT_PRICE_DESCRIPTION)
                } :
                new FieldDescriptor[]{
                        fieldWithPath("productId").description(PRODUCT_ID_DESCRIPTION),
                        fieldWithPath("description").description(PRODUCT_DESCRIPTION),
                        fieldWithPath("imageUrl").description(PRODUCT_IMAGE_URL_DESCRIPTION),
                        fieldWithPath("price").description(PRODUCT_PRICE_DESCRIPTION)
                };
    }

    /**
     * productId in path variables
     *
     * @return ParameterDescriptor
     */
    private static ParameterDescriptor[] userPathParams() {
        return new ParameterDescriptor[]{
                parameterWithName("productId").description(PRODUCT_ID_DESCRIPTION)
        };
    }

    private static final String PRODUCT_PRICE_DESCRIPTION = "Product's price";
    private static final String PRODUCT_IMAGE_URL_DESCRIPTION = "Product's image URL";
    private static final String PRODUCT_DESCRIPTION = "Product's description";
    private static final String PRODUCT_ID_DESCRIPTION = "Product's identifier";

}
