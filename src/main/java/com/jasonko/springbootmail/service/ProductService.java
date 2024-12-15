package com.jasonko.springbootmail.service;

import com.jasonko.springbootmail.dto.ProductRequest;
import com.jasonko.springbootmail.model.Product;

public interface ProductService {

    Product getProdutById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
