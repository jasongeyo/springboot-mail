package com.jasonko.springbootmail.dao.impl;

import com.jasonko.springbootmail.constant.ProductCategory;
import com.jasonko.springbootmail.dao.ProductDao;
import com.jasonko.springbootmail.dto.ProductQueryParams;
import com.jasonko.springbootmail.dto.ProductRequest;
import com.jasonko.springbootmail.model.Product;
import com.jasonko.springbootmail.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "select product_id , product_name, category, image_url, price"
                + " , stock, description, created_date, last_modified_date "
                + " from  product where 1 = 1 "
                ;

        Map<String , Object>map = new HashMap<>();

        if (productQueryParams.getCategory() != null) {
            sql += " and category = :category";
            map.put("category", productQueryParams.getCategory().name());
        }
        if (productQueryParams.getSearch() != null) {
            sql += " and product_name like :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        sql += " order by " + productQueryParams.getOrderBy()
            +  " " + productQueryParams.getSort()
            ;

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map , new ProductRowMapper());

        return productList;
    }

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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = " insert into product( "
                + " product_name , category , image_url , price , stock "
                + " , description, created_date, last_modified_date "
                + " ) values ( "
                + " :productName, :category, :imageUrl, :price, :stock "
                + " , :description, :created_date, :last_modified_date "
                + " ) "
                ;
        Map<String , Object> map = new HashMap<>();
        map.put("productName" , productRequest.getProduct_name());
        map.put("category" , productRequest.getCategory().toString());
        map.put("imageUrl" , productRequest.getImage_url());
        map.put("price" , productRequest.getPrice());
        map.put("stock" , productRequest.getStock());
        map.put("description" , productRequest.getDescription());

        Date now = new Date();
        map.put("created_date" , now);
        map.put("last_modified_date" , now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        try {
            namedParameterJdbcTemplate.update(sql , new MapSqlParameterSource(map) , keyHolder);
        } catch (Exception e) {
            //System.err.println(e.getMessage());
            e.printStackTrace();
        }

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId , ProductRequest productRequest) {
        String sql = " update product set "
                + " product_name = :productName "
                + " , category = :category "
                + " , image_url = :imageUrl "
                + " , price = :price "
                + " , stock = :stock "
                + " , description = :description "
                + " , last_modified_date = :last_modified_date "
                + " where product_id = :product_id "
                ;

        Map<String , Object> map = new HashMap<>();
        map.put("product_id" , productId);

        map.put("productName" , productRequest.getProduct_name());
        map.put("category" , productRequest.getCategory().toString());
        map.put("imageUrl" , productRequest.getImage_url());
        map.put("price" , productRequest.getPrice());
        map.put("stock" , productRequest.getStock());
        map.put("description" , productRequest.getDescription());

        map.put("last_modified_date" , new Date());

        try {
            namedParameterJdbcTemplate.update(sql , map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "delete from product where product_id = :product_id";

        Map<String , Object> map = new HashMap<>();
        map.put("product_id" , productId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
