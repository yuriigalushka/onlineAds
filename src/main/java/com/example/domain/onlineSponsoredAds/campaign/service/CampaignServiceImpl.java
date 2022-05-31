package com.example.domain.onlineSponsoredAds.campaign.service;

import com.example.domain.onlineSponsoredAds.campaign.dao.CampaignRepository;
import com.example.domain.onlineSponsoredAds.campaign.document.CampaignDocument;
import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignRequestDto;
import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignResponseDto;
import com.example.domain.onlineSponsoredAds.product.dao.ProductRepository;
import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;
import com.example.domain.onlineSponsoredAds.product.dto.ProductResponseDto;
import com.example.domain.onlineSponsoredAds.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    CampaignRepository repo;
    @Autowired
    ProductRepository prodRepo;
    @Autowired
    ProductService productService;
    private final ObjectMapper mapper;

    @Transactional
    @Override
    public CampaignResponseDto addNewCampaign(CampaignRequestDto campaign) {
        CampaignDocument document = mapper.convertValue(campaign, CampaignDocument.class);
        CampaignDocument save = repo.save(document);
        return mapper.convertValue(save, CampaignResponseDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDto findHighestPayingProduct(String category) {
        CampaignDocument campaign;
        ProductDocument res;
        List<ProductDocument> products = productService.getByCategory(category);
        if (products.isEmpty()) {
            campaign = repo.findCampaignWithHighestBid();
            res = productService.getBySerialNumber(campaign.getIdentifiers().get(0));
            System.out.println(res);
        } else {
            List<String> collectIdentifiers = products.stream().map(ProductDocument::getSerialNumber).collect(Collectors.toList());
            campaign = repo.findCampaignByIdentifier(collectIdentifiers);
            Set<String> campaignIdentifiers = new HashSet<>(campaign.getIdentifiers());
            res = products.stream().filter(p -> campaignIdentifiers.contains(p.getSerialNumber())).collect(Collectors.toList()).get(0);
        }
        return mapper.convertValue(res, ProductResponseDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CampaignResponseDto> findAll() {
        return repo.findAll().stream().map(r -> mapper.convertValue(r, CampaignResponseDto.class)).collect(Collectors.toList());
    }
}
