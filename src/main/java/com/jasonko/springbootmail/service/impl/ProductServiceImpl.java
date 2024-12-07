package com.jasonko.springbootmail.service.impl;

import com.jasonko.springbootmail.dao.ProductDao;
import com.jasonko.springbootmail.model.Product;
import com.jasonko.springbootmail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProdutById(Integer productId) {
        return productDao.getProdutById(productId);
    }
}
