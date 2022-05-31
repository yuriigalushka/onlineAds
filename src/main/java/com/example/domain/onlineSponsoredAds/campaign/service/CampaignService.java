package com.example.domain.onlineSponsoredAds.campaign.service;

import com.example.domain.onlineSponsoredAds.campaign.document.CampaignDocument;
import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignRequestDto;
import com.example.domain.onlineSponsoredAds.campaign.dto.CampaignResponseDto;
import com.example.domain.onlineSponsoredAds.product.dto.ProductResponseDto;

import java.util.List;

public interface CampaignService {
    CampaignResponseDto addNewCampaign(CampaignRequestDto campaign);
    ProductResponseDto findHighestPayingProduct(String category);
    List<CampaignResponseDto> findAll();
}
