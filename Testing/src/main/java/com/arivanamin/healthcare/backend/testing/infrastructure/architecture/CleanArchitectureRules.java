package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.ProxyRules.no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with;

@AnalyzeClasses (packages = BASE_PACKAGE)
public interface CleanArchitectureRules {
    
    String APPLICATION_LAYER = "Controllers";
    
    String CORE_LAYER = "Services";
    
    String PERSISTENCE_LAYER = "Persistence";
    
    @ArchTest
    ArchRule DAO_MUST_RESIDE_IN_A_DAO_PACKAGE = classes().that()
        .haveNameMatching(".*persistence")
        .should()
        .resideInAPackage("..persistence..")
        .as("DAOs should reside in a package '..persistence..'");
    
    @ArchTest
    ArchRule ENTITIES_MUST_RESIDE_IN_A_DOMAIN_PACKAGE = classes().that()
        .areAnnotatedWith(Entity.class)
        .should()
        .resideInAPackage("..persistence..")
        .as("Entities should reside in a package '..persistence..'");
    
    // todo 11/17/24 - maybe change this to only persistence package should use jpa repository
    @ArchTest
    ArchRule ONLY_DAO_MAY_USE_THE_ENTITY_MANAGER = noClasses().that()
        .resideOutsideOfPackage("..persistence..")
        .should()
        .accessClassesThat()
        .areAssignableTo(Repository.class)
        .as("Only Persistence may use the " + Repository.class.getSimpleName());
    
    // todo 11/17/24 - rename rule
    @ArchTest
    ArchRule SERVICES_SHOULD_NOT_ACCESS_CONTROLLERS = noClasses().that()
        .resideInAPackage("..core..")
        .should()
        .accessClassesThat()
        .resideInAPackage("..application..");
    
    // todo 11/17/24 - need rule for core package as well
    @ArchTest
    ArchRule PERSISTENCE_SHOULD_NOT_ACCESS_SERVICES = noClasses().that()
        .resideInAPackage("..persistence..")
        .should()
        .accessClassesThat()
        .resideInAPackage("..application..");
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_BE_ACCESSED_BY_CONTROLLERS_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage("..persistence..")
        .should()
        .onlyBeAccessed()
        .byAnyPackage("..application..", "..persistence..");
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_ACCESS_PERSISTENCE_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage("..persistence..")
        .should()
        .onlyAccessClassesThat()
        .resideInAnyPackage("..jakarta.persistence..", "..jakarta.validation..",
            "..jakarta.transaction..");
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_DEPEND_ON_PERSISTENCE_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage("..persistence..")
        .should()
        .onlyDependOnClassesThat()
        .resideInAnyPackage("..jakarta.persistence..", "..jakarta.validation..",
            "..jakarta.transaction..");
    
    @ArchTest
    ArchRule PERSISTENCE_SHOULD_NOT_DEPEND_ON_SERVICES = noClasses().that()
        .resideInAPackage("..persistence..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("..application..");
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_BE_DEPENDED_ON_BY_CONTROLLERS_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage("..persistence..")
        .should()
        .onlyHaveDependentClassesThat()
        .resideInAnyPackage("..application..");
    
    @ArchTest
    ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
        
        layeredArchitecture().consideringAllDependencies()
            .layer(APPLICATION_LAYER)
            .definedBy(BASE_PACKAGE + "..application..")
            .layer(CORE_LAYER)
            .definedBy(BASE_PACKAGE + "..core..")
            .layer(PERSISTENCE_LAYER)
            .definedBy(BASE_PACKAGE + "..persistence..")
            
            .whereLayer(CORE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER, PERSISTENCE_LAYER)
            .whereLayer(APPLICATION_LAYER)
            .mayNotBeAccessedByAnyLayer()
            .whereLayer(PERSISTENCE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER);
    
    @ArchTest
    ArchRule CORE_SHOULD_ONLY_ACCESS_CLASSES_IN_CORE_ITSELF = noClasses().that()
        .resideInAPackage("..core..")
        .should()
        .accessClassesThat()
        .resideOutsideOfPackages("..core..", "java..");
    
    @ArchTest
    ArchRule ALL_PUBLIC_METHODS_IN_THE_CONTROLLER_LAYER_SHOULD_RETURN_RESPONSE_WRAPPERS =
        methods().that()
            .areDeclaredInClassesThat()
            .resideInAPackage("..application..")
            .and()
            .arePublic()
            .should()
            .haveNameEndingWith("*Response")
            .because("we do not want to couple the api directly to the return types of the " +
                "core module");
    
    @ArchTest
    ArchRule COMMAND_SHOULD_BE_PREFIXED = classes().that()
        .resideInAPackage("..command..")
        .should()
        .haveSimpleNameEndingWith("Command");
    
    @ArchTest
    ArchRule QUERY_SHOULD_BE_PREFIXED =
        classes().that().resideInAPackage("..query..").should().haveSimpleNameEndingWith("Query");
    
    @ArchTest
    ArchRule CONTROLLERS_SHOULD_BE_SUFFIXED = classes().that()
        .resideInAPackage("..application..")
        .and()
        .areAnnotatedWith(RestController.class)
        .or()
        .areAssignableTo(Controller.class)
        .should()
        .haveSimpleNameEndingWith("Controller");
    
    @ArchTest
    ArchRule CLASSES_NAMED_CONTROLLER_SHOULD_BE_IN_A_CONTROLLER_PACKAGE = classes().that()
        .haveSimpleNameEndingWith("Controller")
        .should()
        .resideInAPackage("..application..");
    
    @ArchTest
    ArchRule NO_BYPASS_OF_PROXY_LOGIC =
        no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with(
            Async.class);
}
