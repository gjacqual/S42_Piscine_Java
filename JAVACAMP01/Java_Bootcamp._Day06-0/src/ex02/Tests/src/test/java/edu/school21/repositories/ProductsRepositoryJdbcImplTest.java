package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepository productsRepository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "Apple MacBook Air 2022 8/8 256", 145999),
            new Product(1L, "Apple MacBook Air 2021 8/8 256", 107000),
            new Product(2L, "Apple MacBook Pro 2020 8/8 256", 159800),
            new Product(3L, "Apple MacBook Pro 2019 8/8 512", 185800),
            new Product(4L, "Apple MacBook Pro 2018 8/8 1TB", 340980),
            new Product(5L, "Apple MacBook Pro 2017 8/8 2TB", 540980));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "Apple MacBook Air 2021 8/8 256", 107000);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "Xerox 1", 3000);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "Test Product", 12345);


    @BeforeEach
    void init() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS,
                productsRepository.findAll());
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(productsRepository.findById(1L).get(),
                EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void testUpdate() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(2L).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void testSave() throws SQLException {
        productsRepository.save(EXPECTED_SAVE_PRODUCT);
        Assertions.assertNotNull(EXPECTED_SAVE_PRODUCT.getIdentifier());
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT,
                productsRepository
                        .findById(EXPECTED_SAVE_PRODUCT.getIdentifier()).orElse(null));
    }

    @Test
    void testDelete() throws SQLException {
        productsRepository.delete(1L);
        Assertions.assertFalse(productsRepository.findById(1L).isPresent());
    }
}
