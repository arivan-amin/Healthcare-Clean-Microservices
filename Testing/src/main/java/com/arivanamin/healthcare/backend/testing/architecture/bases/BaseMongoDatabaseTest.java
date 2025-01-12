package com.arivanamin.healthcare.backend.testing.architecture.bases;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

public interface BaseMongoDatabaseTest extends BaseIntegrationTest {
    
    @Container
    MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.0");
    
    @DynamicPropertySource
    static void registerProperties (DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
