package com.example.spring.jdbc.dao;

import com.example.spring.jdbc.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    String INSERT_PRODUCT = "INSERT INTO company.products (name, price) VALUES (?, ?)";
    String SELECT_BY_ID = "SELECT * FROM company.products WHERE id = ?";
    String SELECT_ALL_PRODUCTS = "SELECT * FROM company.products";
    String UPDATE_PRODUCT = "UPDATE company.products SET name = ?, price = ? WHERE id = ?";
    String INSERT_LOG = "INSERT INTO company.logs (message) VALUES ('Product added')";


    private RowMapper<Product> productRowMapper() {
        return ((rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price")
        ));
    }

    // Добавляет новый товар в БД
    public void save(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT, product.getName(), product.getPrice());
    }

    // Находит товар по id
    public Product findById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, productRowMapper(), id);
    }

    // Возвращает список всех товаров
    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS, productRowMapper());
    }

    // Oбновляет информацию о товаре
    public void update(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT, product.getName(), product.getPrice());
    }

    // Удаляет товар по id
    public void deleteById(int id) {
        String sql = "DELETE FROM company.products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Transactional
    public void createProductWithLog(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT, product.getName(), product.getPrice());
        jdbcTemplate.update(INSERT_LOG);
    }
}
