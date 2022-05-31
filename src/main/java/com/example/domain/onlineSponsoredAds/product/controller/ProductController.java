package com.example.domain.onlineSponsoredAds.product.controller;

import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignResponseDto;
import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;
import com.example.domain.onlineSponsoredAds.product.dto.ProductRequestDto;
import com.example.domain.onlineSponsoredAds.product.dto.ProductResponseDto;
import com.example.domain.onlineSponsoredAds.product.dto.ResponseMessageDto;
import com.example.domain.onlineSponsoredAds.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping()
    public ResponseMessageDto add(@RequestBody ProductRequestDto product) {
        service.addNewProduct(product);
        return ResponseMessageDto.builder().message("Product has been added").build();
    }
    
    @GetMapping("{category}")
    public List<ProductDocument> getAllByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    @GetMapping()
    public List<ProductResponseDto> getAll() {
        return service.findAll();
    }
}
