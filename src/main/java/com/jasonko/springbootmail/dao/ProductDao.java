package com.jasonko.springbootmail.dao;

import com.jasonko.springbootmail.constant.ProductCategory;
import com.jasonko.springbootmail.dto.ProductRequest;
import com.jasonko.springbootmail.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category , String search);

    Product getProdutById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId , ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
