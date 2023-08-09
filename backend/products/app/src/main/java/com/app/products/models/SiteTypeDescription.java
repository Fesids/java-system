package com.app.products.models;


import com.app.products.ENUM.EsiteType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document("STD")
public class SiteTypeDescription {

    @Id
    private String STD_id;

    private String body;

    private String description;

    private EsiteType siteType;


    public SiteTypeDescription(){

        this.STD_id = new Object().toString();
    }









}
