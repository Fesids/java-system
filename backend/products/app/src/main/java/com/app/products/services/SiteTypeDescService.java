package com.app.products.services;

import com.app.products.DTO.SiteTypeDTO;
import com.app.products.models.SiteTypeDescription;

import java.util.List;

public interface SiteTypeDescService {

    //SiteTypeDescription createSTD(SiteTypeDTO siteTypeDTO, String siteType);
    String createSTD(SiteTypeDTO siteTypeDTO, String siteType);

    List<SiteTypeDescription> siteList();

    SiteTypeDescription detailSite(String id);

}
