package com.arivanamin.healthcare.backend.testing.architecture;

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

public interface CleanArchitectureRules {
    
    String APPLICATION_LAYER = "Controllers";
    
    String CORE_LAYER = "Core";
    
    String PERSISTENCE_LAYER = "Persistence";
    
    String PERSISTENCE_PACKAGE = "..persistence..";
    
    String CORE_PACKAGE = "..core..";
    
    String APPLICATION_PACKAGE = "..application..";
    
    String COMMAND = "..command..";
    
    String QUERY_PACKAGE = "..query..";
    
    String QUERY_SUFFIX = "Query";
    
    String COMMAND_SUFFIX = "Command";
    
    String ENDPOINTS_PACKAGE = "..endpoints..";
    
    String CONTROLLER_SUFFIX = "Controller";
    
    String STANDARD_JAVA_CLASSES = "java..";
    
    String API_RESPONSE_SUFFIX = "*Response";
    
    String[] PERSISTENCE_PACKAGES =
        { "..jakarta.persistence..", "..jakarta.validation..", "..jakarta.transaction.." };
    
    @ArchTest
    ArchRule DAO_MUST_RESIDE_IN_A_DAO_PACKAGE = classes().that()
        .haveNameMatching(".*persistence")
        .should().resideInAPackage(PERSISTENCE_PACKAGE)
        .as("DAOs should reside in a package '..persistence..'");
    
    @ArchTest
    ArchRule ENTITIES_MUST_RESIDE_IN_A_DOMAIN_PACKAGE = classes().that()
        .areAnnotatedWith(Entity.class)
        .should().resideInAPackage(PERSISTENCE_PACKAGE)
        .as("Entities should reside in a package '..persistence..'");
    
    // todo 11/17/24 - maybe change this to only persistence package should use jpa repository
    @ArchTest
    ArchRule ONLY_DAO_MAY_USE_THE_ENTITY_MANAGER = noClasses().that()
        .resideOutsideOfPackage(PERSISTENCE_PACKAGE)
        .should()
        .accessClassesThat()
        .areAssignableTo(Repository.class)
        .as("Only Persistence may use the " + Repository.class.getSimpleName());
    
    // todo 11/17/24 - rename rule
    @ArchTest
    ArchRule SERVICES_SHOULD_NOT_ACCESS_CONTROLLERS = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    // todo 11/17/24 - need rule for core package as well
    @ArchTest
    ArchRule PERSISTENCE_SHOULD_NOT_ACCESS_SERVICES = noClasses().that()
        .resideInAPackage(PERSISTENCE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_BE_ACCESSED_BY_CONTROLLERS_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage(PERSISTENCE_PACKAGE)
        .should()
        .onlyBeAccessed()
        .byAnyPackage(APPLICATION_PACKAGE, PERSISTENCE_PACKAGE);
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_ACCESS_PERSISTENCE_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage(PERSISTENCE_PACKAGE)
        .should()
        .onlyAccessClassesThat()
        .resideInAnyPackage(PERSISTENCE_PACKAGES);
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_DEPEND_ON_PERSISTENCE_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage(PERSISTENCE_PACKAGE)
        .should()
        .onlyDependOnClassesThat()
        .resideInAnyPackage(PERSISTENCE_PACKAGES);
    
    @ArchTest
    ArchRule PERSISTENCE_SHOULD_NOT_DEPEND_ON_SERVICES = noClasses().that()
        .resideInAPackage(PERSISTENCE_PACKAGE)
        .should()
        .dependOnClassesThat()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule SERVICES_SHOULD_ONLY_BE_DEPENDED_ON_BY_CONTROLLERS_OR_OTHER_SERVICES = classes().that()
        .resideInAPackage(PERSISTENCE_PACKAGE)
        .should()
        .onlyHaveDependentClassesThat()
        .resideInAnyPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
        
        layeredArchitecture().consideringAllDependencies()
            .layer(APPLICATION_LAYER).definedBy(BASE_PACKAGE + APPLICATION_PACKAGE)
            .layer(CORE_LAYER).definedBy(BASE_PACKAGE + CORE_PACKAGE)
            .layer(PERSISTENCE_LAYER).definedBy(BASE_PACKAGE + PERSISTENCE_PACKAGE)
            
            .whereLayer(CORE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER, PERSISTENCE_LAYER)
            .whereLayer(APPLICATION_LAYER)
            .mayNotBeAccessedByAnyLayer()
            .whereLayer(PERSISTENCE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER);
    
    @ArchTest
    ArchRule CORE_SHOULD_ONLY_ACCESS_CLASSES_IN_CORE_ITSELF = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideOutsideOfPackages(CORE_PACKAGE, STANDARD_JAVA_CLASSES);
    
    @ArchTest
    ArchRule ALL_PUBLIC_METHODS_IN_THE_CONTROLLER_LAYER_SHOULD_RETURN_RESPONSE_WRAPPERS =
        methods().that()
            .areDeclaredInClassesThat().resideInAPackage(APPLICATION_PACKAGE)
            .and()
            .arePublic()
            .should().haveNameEndingWith(API_RESPONSE_SUFFIX)
            .because("we do not want to couple the api directly to the return types of the " +
                "core module");
    
    @ArchTest
    ArchRule COMMAND_SHOULD_BE_PREFIXED = classes().that().resideInAPackage(COMMAND)
        .should().haveSimpleNameEndingWith(COMMAND_SUFFIX);
    
    @ArchTest
    ArchRule QUERY_SHOULD_BE_PREFIXED = classes().that()
        .resideInAPackage(QUERY_PACKAGE)
        .should()
        .haveSimpleNameEndingWith(QUERY_SUFFIX);
    
    @ArchTest
    ArchRule CONTROLLERS_SHOULD_BE_SUFFIXED = classes().that().resideInAPackage(APPLICATION_PACKAGE)
        .and()
        .areAnnotatedWith(RestController.class)
        .or()
        .areAssignableTo(Controller.class)
        .should().haveSimpleNameEndingWith(CONTROLLER_SUFFIX);
    
    @ArchTest
    ArchRule CONTROLLERS_SHOULD_BE_IN_ENDPOINTS_PACKAGE = classes().that()
        .areAnnotatedWith(RestController.class)
        .or()
        .areAssignableTo(Controller.class)
        .should().resideInAPackage(ENDPOINTS_PACKAGE);
    
    @ArchTest
    ArchRule CLASSES_NAMED_CONTROLLER_SHOULD_BE_IN_A_CONTROLLER_PACKAGE = classes().that()
        .haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
        .should()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule NO_BYPASS_OF_PROXY_LOGIC =
        no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with(
            Async.class);
}
