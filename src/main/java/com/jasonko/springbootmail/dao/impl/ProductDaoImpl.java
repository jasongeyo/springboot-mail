package com.jasonko.springbootmail.dao.impl;

import com.jasonko.springbootmail.dao.ProductDao;
import com.jasonko.springbootmail.model.Product;
import com.jasonko.springbootmail.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProdutById(Integer productId) {
        String sql = "select product_id , product_name, category, image_url, price"
                   + " , stock, description, created_date, last_modified_date "
                   + " from  product "
                   + " where product_id = :product_id";

        Map<String , Object> map = new HashMap<>();
        map.put("product_id", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        }
        else {
            return null;
        }
    }
}
