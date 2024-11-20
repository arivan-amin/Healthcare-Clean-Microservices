package com.arivanamin.healthcare.backend.testing.architecture;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.*;
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
    
    String CORE_PACKAGE = "..core..";
    
    String CORE_LAYER = "Core";
    
    String DETAILS_PACKAGE = "..details..";
    
    String DETAILS_LAYER = "Details";
    
    String APPLICATION_PACKAGE = "..application..";
    
    String APPLICATION_LAYER = "Controllers";
    
    String COMMAND_PACKAGE = "..command..";
    
    String COMMAND_SUFFIX = "Command";
    
    String QUERY_PACKAGE = "..query..";
    
    String QUERY_SUFFIX = "Query";
    
    String CONTROLLER_PACKAGE = "..endpoints..";
    
    String CONTROLLER_SUFFIX = "Controller";
    
    String PERSISTENCE_SUFFIX = "Persistence";
    
    String REPOSITORY_SUFFIX = "Repository";
    
    String API_RESPONSE_SUFFIX = "Response";
    
    String SPRING_FRAMEWORK_PACKAGES = "org.springframework..";
    
    String[] PERSISTENCE_PACKAGES =
        { "..jakarta.persistence..", "..jakarta.validation..", "..jakarta.transaction.." };
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_DEPEND_ON_ANY_PERSISTENCE_MECHANISM =
        noClasses().that().resideInAPackage(CORE_PACKAGE)
        .should().accessClassesThat().resideInAnyPackage(PERSISTENCE_PACKAGES);
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_DEPEND_ON_DETAILS_OR_APPLICATION = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should().accessClassesThat().resideInAnyPackage(DETAILS_PACKAGE, APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_ACCESS_APPLICATION_LAYER =
        noClasses().that().resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_DEPEND_ON_SPRING = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAPackage(SPRING_FRAMEWORK_PACKAGES);
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_ACCESS_DETAILS_LAYER = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAPackage(DETAILS_PACKAGE);
    
    @ArchTest
    ArchRule DETAILS_SHOULD_NOT_ACCESS_APPLICATION = noClasses().that()
        .resideInAPackage(DETAILS_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule DETAILS_SHOULD_NOT_DEPEND_ON_APPLICATION =
        noClasses().that().resideInAPackage(DETAILS_PACKAGE)
        .should()
        .dependOnClassesThat()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule DETAILS_SHOULD_ONLY_BE_ACCESSED_BY_APPLICATION = classes().that()
        .resideInAPackage(DETAILS_PACKAGE)
        .should()
        .onlyBeAccessed()
        .byAnyPackage(APPLICATION_PACKAGE, DETAILS_PACKAGE);
    
    @ArchTest
    ArchRule INTERFACES_MUST_NOT_BE_PLACED_IN_IMPLEMENTATION_PACKAGES =
        noClasses().that().resideInAPackage(APPLICATION_PACKAGE).should().beInterfaces();
    
    @ArchTest
    ArchRule PERSISTENCE_CLASSES_SHOULD_BE_IN_DETAILS_PACKAGE = classes().that()
        .haveSimpleNameContaining("Jpa")
        .and()
        .haveSimpleNameEndingWith(PERSISTENCE_SUFFIX)
        .should()
        .resideInAPackage(DETAILS_PACKAGE)
        .as("Classes that handle Persistence should only be in details package");
    
    @ArchTest
    ArchRule JPA_ENTITIES_SHOULD_BE_IN_DETAILS_PACKAGE = classes().that()
        .areAnnotatedWith(Entity.class)
        .should()
        .resideInAPackage(DETAILS_PACKAGE)
        .as("Entities should be in details package");
    
    @ArchTest
    ArchRule JPA_REPOSITORY_SHOULD_BE_IN_DETAILS_PACKAGE = classes().that()
        .areAssignableTo(Repository.class)
        .should()
        .resideInAPackage(DETAILS_PACKAGE)
        .andShould()
        .haveSimpleNameEndingWith(REPOSITORY_SUFFIX)
        .as("Entities should be in details package");
    
    @ArchTest
    ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED =
        
        layeredArchitecture().consideringAllDependencies()
            .layer(APPLICATION_LAYER)
            .definedBy(BASE_PACKAGE + APPLICATION_PACKAGE)
            .layer(CORE_LAYER)
            .definedBy(BASE_PACKAGE + CORE_PACKAGE)
            .layer(DETAILS_LAYER)
            .definedBy(BASE_PACKAGE + DETAILS_PACKAGE)
            
            .whereLayer(CORE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER, DETAILS_LAYER)
            .whereLayer(APPLICATION_LAYER)
            .mayNotBeAccessedByAnyLayer()
            .whereLayer(DETAILS_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER);
    
    @ArchTest
    ArchRule ALL_PUBLIC_METHODS_IN_THE_CONTROLLER_LAYER_SHOULD_RETURN_RESPONSE_WRAPPERS =
        methods().that().areDeclaredInClassesThat().resideInAPackage(CONTROLLER_PACKAGE)
            .and().arePublic().should(haveReturnTypeWithResponseSuffix())
            .because("we do not want to couple the api directly to the return types of the " +
                "core module");
    
    @ArchTest
    ArchRule COMMAND_SHOULD_BE_PREFIXED = classes().that()
        .resideInAPackage(COMMAND_PACKAGE)
        .should()
        .haveSimpleNameEndingWith(COMMAND_SUFFIX);
    
    @ArchTest
    ArchRule QUERY_SHOULD_BE_PREFIXED = classes().that()
        .resideInAPackage(QUERY_PACKAGE)
        .should()
        .haveSimpleNameEndingWith(QUERY_SUFFIX);
    
    @ArchTest
    ArchRule CONTROLLERS_SHOULD_BE_SUFFIXED = classes().that()
        .resideInAPackage(APPLICATION_PACKAGE)
        .and()
        .areAnnotatedWith(RestController.class)
        .or()
        .areAssignableTo(Controller.class)
        .should()
        .haveSimpleNameEndingWith(CONTROLLER_SUFFIX);
    
    @ArchTest
    ArchRule CONTROLLERS_SHOULD_BE_IN_ENDPOINTS_PACKAGE = classes().that()
        .areAnnotatedWith(RestController.class)
        .or().areAssignableTo(Controller.class).should().resideInAPackage(CONTROLLER_PACKAGE);
    
    @ArchTest
    ArchRule CLASSES_NAMED_CONTROLLER_SHOULD_BE_IN_A_CONTROLLER_PACKAGE = classes().that()
        .haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
        .should()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule NO_BYPASS_OF_PROXY_LOGIC =
        no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with(
            Async.class);
    
    private static ArchCondition<JavaMethod> haveReturnTypeWithResponseSuffix () {
        return new ArchCondition<>("") {
            @Override
            public void check (JavaMethod method, ConditionEvents events) {
                String returnType = method.getRawReturnType().getName();
                boolean matches =
                    "void".equals(returnType) || returnType.endsWith(API_RESPONSE_SUFFIX);
                events.add(new SimpleConditionEvent(method, matches,
                    "Method %s in %s does not return a type ending with '%s'".formatted(
                        method.getName(), method.getOwner().getName(), API_RESPONSE_SUFFIX)));
            }
        };
    }
}
