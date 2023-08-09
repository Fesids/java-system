package com.app.products.controller;


import com.app.products.DTO.SiteTypeDTO;
import com.app.products.UTILS.GeneralUtilities;
import com.app.products.models.SiteTypeDescription;
import com.app.products.services.SiteTypeDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ST")
@CrossOrigin("*")
public class STDController {


    @Autowired
    private SiteTypeDescService siteTypeDescService;

    /*@PostMapping("/new/{type}")
    public ResponseEntity<SiteTypeDescription> create(@RequestBody SiteTypeDTO siteTypeDTO, @PathVariable("type") String type){
        var newSTD = siteTypeDescService.createSTD(siteTypeDTO, type);

        return ResponseEntity.ok().body(newSTD);
    }*/

    @PostMapping("/new/{type}")
    public ResponseEntity<String> create(@RequestBody SiteTypeDTO siteTypeDTO, @PathVariable("type") String type){
        var newSTD = siteTypeDescService.createSTD(siteTypeDTO, type);

        return ResponseEntity.created(GeneralUtilities.toUri("/new/{type}")).body(newSTD);
    }

    @GetMapping("/list")
    public ResponseEntity<List<SiteTypeDescription>> list(){
        return ResponseEntity.ok().body(siteTypeDescService.siteList());
    }

    @GetMapping("/detail/{site_id}")
    public ResponseEntity<SiteTypeDescription> detail(@PathVariable("site_id") String id){
        return ResponseEntity.ok().body(siteTypeDescService.detailSite(id));
    }
}
