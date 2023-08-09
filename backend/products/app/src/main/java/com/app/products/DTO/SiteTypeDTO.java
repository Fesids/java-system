package com.app.products.DTO;

import com.app.products.ENUM.EsiteType;
import com.app.products.models.SiteTypeDescription;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SiteTypeDTO(
        @NotEmpty @NotNull String body,

        @NotEmpty @NotNull String description


) {
    public SiteTypeDescription createFrontBasic(SiteTypeDTO siteTypeDTO){
        var STD = SiteTypeDescription.builder().body(siteTypeDTO.body)
                .description(siteTypeDTO.description)
                .siteType(EsiteType.FRONT_BASIC).build();
        return STD;
    }


    public SiteTypeDescription createBackBasic(SiteTypeDTO siteTypeDTO){
        var STD = SiteTypeDescription.builder().body(siteTypeDTO.body)
                .description(siteTypeDTO.description)
                .siteType(EsiteType.BACK_BASIC).build();
        return STD;
    }

    public SiteTypeDescription createFullSite(SiteTypeDTO siteTypeDTO){
        var STD = SiteTypeDescription.builder()
                .description(siteTypeDTO.description).body(siteTypeDTO.body)
                .siteType(EsiteType.FULL_SITE).build();
        return STD;
    }






}
