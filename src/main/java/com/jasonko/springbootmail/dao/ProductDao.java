package com.jasonko.springbootmail.dao;

import com.jasonko.springbootmail.dto.ProductRequest;
import com.jasonko.springbootmail.model.Product;

public interface ProductDao {

    Product getProdutById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId , ProductRequest productRequest);
}
