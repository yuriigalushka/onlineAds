package com.example.domain.onlineSponsoredAds.product.dao;

import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoTemplate template;

    @Override
    public List<ProductDocument> findByCategory(String category) {
        Query query = Query.query(Criteria.where("category").is(category));
        return template.find(query, ProductDocument.class);
    }

    @Override
    public ProductDocument findBySerialNumber(String serialNumber) {
        Query query = Query.query(Criteria.where("serialNumber").is(serialNumber));
        return template.find(query, ProductDocument.class).get(0);
    }

    @Override
    public List<ProductDocument> findAll() {
        return template.findAll(ProductDocument.class);
    }

    @Override
    public ProductDocument save(ProductDocument product) {
        return template.save(product);
    }
}
