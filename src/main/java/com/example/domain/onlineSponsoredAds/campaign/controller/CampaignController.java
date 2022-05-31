package com.example.domain.onlineSponsoredAds.campaign.controller;

import com.example.domain.onlineSponsoredAds.campaign.document.CampaignDocument;
import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignRequestDto;
import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignResponseDto;
import com.example.domain.onlineSponsoredAds.campaign.service.CampaignService;
import com.example.domain.onlineSponsoredAds.product.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("campaigns")
public class CampaignController {
    @Autowired
    CampaignService service;

    @PostMapping()
    public CampaignResponseDto add(@RequestBody CampaignRequestDto campaign) {
        return service.addNewCampaign(campaign);
    }

    @GetMapping("servAd/{category}")
    public ProductResponseDto servAd(@PathVariable String category) {
        return service.findHighestPayingProduct(category);
    }

    @GetMapping()
    public List<CampaignResponseDto> getAll() {
        return service.findAll();
    }
}
