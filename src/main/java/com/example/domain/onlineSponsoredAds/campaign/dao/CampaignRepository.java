package com.example.domain.onlineSponsoredAds.campaign.dao;

import com.example.domain.onlineSponsoredAds.campaign.document.CampaignDocument;
import com.example.domain.onlineSponsoredAds.product.document.ProductDocument;

import java.util.List;

public interface CampaignRepository {
    CampaignDocument save(CampaignDocument campaign);
    CampaignDocument findCampaignByIdentifier(List<String> list);
    List<CampaignDocument> findAll();
    CampaignDocument findCampaignWithHighestBid();
}
