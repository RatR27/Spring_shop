package com.rr27.lesson4springdata;

import com.rr27.lesson4springdata.controllers.ProductRestController;
import com.rr27.lesson4springdata.entities.Product;
import com.rr27.lesson4springdata.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private ProductService productService;
//
//    @Test
//    public void getAllProductsTest() throws Exception {
//        List<Product> allProducts = Arrays.asList(
//                new Product( "Milk", new BigDecimal(90)),
//                new Product("Bread", new BigDecimal(25)),
//                new Product("Cheese", new BigDecimal(320))
//        );
//
//        given(productService.findAll()).willReturn(allProducts);
//
//        mvc.perform(get("/api/v1/products")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[0].title", is(allProducts.get(0).getTitle())));
//    }
}
