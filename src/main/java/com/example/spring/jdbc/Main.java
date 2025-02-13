package com.example.spring.jdbc;

import com.example.spring.jdbc.dao.ProductDao;
import com.example.spring.jdbc.entity.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        DataSource dataSource;

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductDao productDao = context.getBean(ProductDao.class);

//        Product product1 = new Product (1, "Apple", 2.38);
//        productDao.save(product1);
//
//        Product product2 = new Product(2, "Orange", 1.89);
//        productDao.save(product2);
//
//        Product product3 = new Product(1, "Apple", 1.38);
//        productDao.update(product3);
//
//        List<Product> users = productDao.findAll();
//        users.forEach(p -> System.out.println(p.getName() + " - " + p.getPrice()));
//
//        Product product4 = new Product(3, "Lemon", 0.67);
//        productDao.createProductWithLog(product4);
//
//        productDao.deleteById(1);

    }
}