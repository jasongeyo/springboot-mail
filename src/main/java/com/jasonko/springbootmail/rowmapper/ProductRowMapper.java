package com.jasonko.springbootmail.rowmapper;

import com.jasonko.springbootmail.constant.ProductCategory;
import com.jasonko.springbootmail.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProduct_id(resultSet.getInt("product_id"));
        product.setProduct_name(resultSet.getString("product_name"));
        //20241207 JASON begin:修改成ENUM
        //product.setCategory(resultSet.getString("category"));
        String categoryStr = resultSet.getString("category");
        ProductCategory category = ProductCategory.valueOf(categoryStr);
        product.setCategory(category);
        //20241207 JASON end:修改成ENUM
        product.setImage_url(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreated_date(resultSet.getTimestamp("created_date"));
        product.setLast_modified_date(resultSet.getTimestamp("last_modified_date"));

        return product;
    }
}
