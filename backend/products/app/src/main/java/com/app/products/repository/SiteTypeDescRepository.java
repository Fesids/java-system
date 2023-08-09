package com.app.products.repository;

import com.app.products.models.SiteTypeDescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteTypeDescRepository extends MongoRepository<SiteTypeDescription, String> {
}
