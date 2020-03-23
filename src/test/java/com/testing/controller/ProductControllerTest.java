package com.testing.controller;

import com.testing.api.resource.ProductApi;
import com.testing.repository.ProductRepository;
import com.testing.repository.entity.Product;
import com.testing.sampleDataForTests.SampleProductData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductControllerTest {

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    private SampleProductData sampleProductData = new SampleProductData();

    @Test
    public void getProduct_NoResults() throws Exception {
        mockMvc
                .perform(get("/product", 1)
                        .param("id", "1"))
                .andExpect(status().is4xxClientError());

        assertThat(this.testRestTemplate.getForObject(HTTP_LOCALHOST + port + "/product/?id=1",
                String.class)).isNotNull();
    }

    @Test
    public void getProduct() {
        // act

        Product product = sampleProductData.getTestProduct();

        product = productRepository.save(product);

        ProductApi productApi = testRestTemplate.getForObject(HTTP_LOCALHOST + port + "/product/".concat(product.getId().toString()), ProductApi.class);

        assertEquals(productApi.getName(), "sampleName");
        assertEquals(productApi.getCategory(), "sampleCategory");
        assertEquals(productApi.getDescription(), "sampleDescription");
        assertEquals(productApi.getUnitPrice().intValue(), 10);
        assertEquals(Optional.of(productApi.getUnitsInOrder()).get(), Optional.of(20L).get());
        assertEquals(Optional.of(productApi.getUnitsInStock()).get(), Optional.of(50L).get());
    }

    @Test
    public void getProducts() {
        // act

        productRepository.save(sampleProductData.getTestProduct());
        productRepository.save(sampleProductData.getTestProduct());
        ResponseEntity<List<ProductApi>> clientsFromDb = testRestTemplate.exchange(HTTP_LOCALHOST + port + "/products",  HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductApi>>() {
        });

        // assert
        assertThat(clientsFromDb.getBody().size() > 0);
        ProductApi productApi = clientsFromDb.getBody().get(0);
        assertEquals(productApi.getName(), "sampleName");
        assertEquals(productApi.getCategory(), "sampleCategory");
        assertEquals(productApi.getDescription(), "sampleDescription");
        assertEquals(productApi.getUnitPrice().intValue(), 10);
        assertEquals(Optional.of(productApi.getUnitsInOrder()).get(), Optional.of(20L).get());
        assertEquals(Optional.of(productApi.getUnitsInStock()).get(), Optional.of(50L).get());


        ProductApi secondProduct = clientsFromDb.getBody().get(1);
        assertEquals(secondProduct.getName(), "sampleName");
        assertEquals(secondProduct.getCategory(), "sampleCategory");
        assertEquals(secondProduct.getDescription(), "sampleDescription");
        assertEquals(secondProduct.getUnitPrice().intValue(), 10);
        assertEquals(Optional.of(secondProduct.getUnitsInOrder()).get(), Optional.of(20L).get());
        assertEquals(Optional.of(secondProduct.getUnitsInStock()).get(), Optional.of(50L).get());
    }

    @Test
    public void addProduct() {
        // act
        ProductApi client = sampleProductData.getTestProductApi();
        ProductApi addedClient = testRestTemplate.postForObject(HTTP_LOCALHOST + port + "/products", client, ProductApi.class);

        Optional<Product> clientFromDb = productRepository.findById(addedClient.getId());

        Product productApi = clientFromDb.get();
        // assert
        assertEquals(productApi.getName(), "sampleName");
        assertEquals(productApi.getCategory(), "sampleCategory");
        assertEquals(productApi.getDescription(), "sampleDescription");
        assertEquals(productApi.getUnitPrice().intValue(), 10);
        assertEquals(Optional.of(productApi.getUnitsInOrder()).get(), Optional.of(20L).get());
        assertEquals(Optional.of(productApi.getUnitsInStock()).get(), Optional.of(50L).get());
    }
}
