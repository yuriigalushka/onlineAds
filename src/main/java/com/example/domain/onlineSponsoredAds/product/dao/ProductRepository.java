package com.example.domain.onlineSponsoredAds.product.dao;

import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository {
    List<ProductDocument> findByCategory(String category);
    ProductDocument findBySerialNumber(String serialNumber);
    List<ProductDocument> findAll();
    ProductDocument save(ProductDocument product);
}
