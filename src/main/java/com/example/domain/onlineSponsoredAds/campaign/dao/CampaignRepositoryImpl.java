package com.example.domain.onlineSponsoredAds.campaign.dao;

import com.example.domain.onlineSponsoredAds.campaign.document.CampaignDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CampaignRepositoryImpl implements CampaignRepository {
    private final MongoTemplate template;

    @Override
    public CampaignDocument save(CampaignDocument campaign) {
        return template.save(campaign);
    }

    @Override
    public CampaignDocument findCampaignByIdentifier(List<String> list) {
        LocalDate minDate = LocalDate.now().minusDays(10);
        Query query = Query.query(
                Criteria.where("identifiers").in(list)).with(Sort.by("bid").descending()).addCriteria(
                        Criteria.where("startDate").gte(minDate)
        ).limit(1);
        return template.find(query, CampaignDocument.class).get(0);
    }

    @Override
    public CampaignDocument findCampaignWithHighestBid() {
        LocalDate minDate = LocalDate.now().minusDays(10);
        Query newQuery = Query.query(
                Criteria.where("startDate").gte(minDate)
        ).with(Sort.by("bid").descending()).limit(1);
        return template.find(newQuery, CampaignDocument.class).get(0);
    }

    @Override
    public List<CampaignDocument> findAll() {
        return template.findAll(CampaignDocument.class);
    }
}
