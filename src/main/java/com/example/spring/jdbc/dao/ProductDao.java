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

    private RowMapper<Product> productRowMapper() {
        return ((rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price")
        ));
    }

    // Добавляет новый товар в БД
    public void save(Product product) {
        String sql = "INSERT INTO company.products (name, price) VALUES (?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    // Находит товар по id
    public Product findById(int id) {
        String sql = "SELECT * FROM company.products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper(), id);
    }

    // Возвращает список всех товаров
    public List<Product> findAll() {
        String sql = "SELECT * FROM company.products";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    // Oбновляет информацию о товаре
    public void update(Product product) {
        String sql = "UPDATE company.products SET name = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    // Удаляет товар по id
    public void deleteById(int id) {
        String sql = "DELETE FROM company.products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Transactional
    public void createProductWithLog(Product product) {
        String sql1 = "INSERT INTO company.products (name, price) VALUES (?, ?)";
        jdbcTemplate.update(sql1, product.getName(), product.getPrice());

        String sql2 = "INSERT INTO logs (message) VALUES ('Product added')";
        jdbcTemplate.update(sql2);
    }
}
