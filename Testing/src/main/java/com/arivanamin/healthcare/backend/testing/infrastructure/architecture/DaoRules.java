package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.data.repository.Repository;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class DaoRules {
    
    @ArchTest
    static final ArchRule DAOs_must_reside_in_a_dao_package = classes().that()
        .haveNameMatching(".*persistence")
        .should()
        .resideInAPackage("..persistence..")
        .as("DAOs should reside in a package '..persistence..'");
    
    @ArchTest
    static final ArchRule entities_must_reside_in_a_domain_package = classes().that()
        .areAnnotatedWith(Entity.class)
        .should()
        .resideInAPackage("..persistence..")
        .as("Entities should reside in a package '..persistence..'");
    
    // todo 11/17/24 - maybe change this to only persistence package should use jpa repository
    @ArchTest
    static final ArchRule only_DAOs_may_use_the_EntityManager = noClasses().that()
        .resideOutsideOfPackage("..persistence..")
        .should()
        .accessClassesThat()
        .areAssignableTo(Repository.class)
        .as("Only Persistence may use the " + Repository.class.getSimpleName());
}
