package com.example.domain.onlineSponsoredAds.product.service;

import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;
import com.example.domain.onlineSponsoredAds.product.dto.ProductRequestDto;
import com.example.domain.onlineSponsoredAds.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductDocument> getByCategory(String category);
    List<ProductResponseDto> findAll();
    void addNewProduct(ProductRequestDto product);
    ProductDocument getBySerialNumber(String serialNumber);
}
