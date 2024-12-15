package com.jasonko.springbootmail.dao;

import com.jasonko.springbootmail.dto.ProductRequest;
import com.jasonko.springbootmail.model.Product;

public interface ProductDao {

    Product getProdutById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
