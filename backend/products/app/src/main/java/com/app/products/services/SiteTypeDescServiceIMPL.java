package com.app.products.services;


import com.app.products.DTO.SiteTypeDTO;
import com.app.products.ENUM.EsiteType;
import com.app.products.models.SiteTypeDescription;
import com.app.products.repository.SiteTypeDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SiteTypeDescServiceIMPL implements SiteTypeDescService{

    @Autowired
    private SiteTypeDescRepository siteTypeDescRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createSTD(SiteTypeDTO siteTypeDTO, String siteType) {
        SiteTypeDescription newSTD = null;

        switch (siteType) {
            case "FRONT_BASIC" -> {
            /*newSTD = SiteTypeDescription.builder().siteType(EsiteType.FRONT_BASIC)
                    .body(siteTypeDTO.body()).build();*/
                newSTD = siteTypeDTO.createFrontBasic(siteTypeDTO);
                siteTypeDescRepository.save(newSTD);
            }
            case "BACK_BASIC" -> {
            /*newSTD = SiteTypeDescription.builder().siteType(EsiteType.BACK_BASIC)
                    .description(siteTypeDTO.description())
                    .body(siteTypeDTO.body()).build();*/
                newSTD = siteTypeDTO.createBackBasic(siteTypeDTO);
                siteTypeDescRepository.save(newSTD);
            }
            case "FULL_SITE" -> {
            /*newSTD = SiteTypeDescription.builder().siteType(EsiteType.FULL_SITE)
                    .description(siteTypeDTO.description())
                    .body(siteTypeDTO.body()).build();*/
                newSTD = siteTypeDTO.createFullSite(siteTypeDTO);
                siteTypeDescRepository.save(newSTD);
            }
        }
        /*assert newSTD != null;
        return siteTypeDescRepository.save(newSTD);*/

        return "saved";
    }

    @Override
    public List<SiteTypeDescription> siteList() {
        return mongoTemplate.findAll(SiteTypeDescription.class);
    }

    @Override
    public SiteTypeDescription detailSite(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("STD_id").is(id));

        var ST_detail = mongoTemplate.findOne(query, SiteTypeDescription.class, "STD");

        return ST_detail;

    }


}
