package com.jasonko.springbootmail.service.impl;

import com.jasonko.springbootmail.constant.ProductCategory;
import com.jasonko.springbootmail.dao.ProductDao;
import com.jasonko.springbootmail.dto.ProductRequest;
import com.jasonko.springbootmail.model.Product;
import com.jasonko.springbootmail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductCategory category , String search) {
        return productDao.getProducts(category , search);
    }

    @Override
    public Product getProdutById(Integer productId) {
        return productDao.getProdutById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId , productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
