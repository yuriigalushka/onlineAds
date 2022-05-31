package com.example.domain.onlineSponsoredAds.product.service;

import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignResponseDto;
import com.example.domain.onlineSponsoredAds.product.dao.ProductRepository;
import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;
import com.example.domain.onlineSponsoredAds.product.dto.ProductRequestDto;
import com.example.domain.onlineSponsoredAds.product.dto.ProductResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repo;

    private final ObjectMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<ProductDocument> getByCategory(String category) {
        return repo.findByCategory(category).stream()
                .map(r -> mapper.convertValue(r, ProductDocument.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDocument getBySerialNumber(String serialNumber) {
        return repo.findBySerialNumber(serialNumber);
    }

    @Transactional
    @Override
    public void addNewProduct(ProductRequestDto product) {
        ProductDocument document = mapper.convertValue(product, ProductDocument.class);
        repo.save(document);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponseDto> findAll() {
        return repo.findAll().stream().map(r -> mapper.convertValue(r, ProductResponseDto.class)).collect(Collectors.toList());
    }
}
