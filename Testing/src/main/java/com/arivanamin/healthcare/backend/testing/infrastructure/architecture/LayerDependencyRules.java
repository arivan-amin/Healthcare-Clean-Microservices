package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.*;
import com.tngtech.archunit.lang.ArchRule;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@ArchTag ("example")
@AnalyzeClasses (packages = BASE_PACKAGE)
public class LayerDependencyRules {
    
    // todo 11/17/24 - rename rule
    @ArchTest
    static final ArchRule services_should_not_access_controllers = noClasses().that()
        .resideInAPackage("..core..")
        .should()
        .accessClassesThat()
        .resideInAPackage("..application..");
    
    // todo 11/17/24 - need rule for core package as well
    @ArchTest
    static final ArchRule persistence_should_not_access_services = noClasses().that()
        .resideInAPackage("..persistence..")
        .should()
        .accessClassesThat()
        .resideInAPackage("..application..");
    
    @ArchTest
    static final ArchRule services_should_only_be_accessed_by_controllers_or_other_services =
        classes().that()
            .resideInAPackage("..persistence..")
            .should()
            .onlyBeAccessed()
            .byAnyPackage("..application..", "..persistence..");
    
    // todo 11/17/24 - check jakarta package name
    @ArchTest
    static final ArchRule services_should_only_access_persistence_or_other_services =
        classes().that()
            .resideInAPackage("..persistence..")
            .should()
            .onlyAccessClassesThat()
            .resideInAnyPackage("..jakarta.persistence..", "..jakarta.validation..",
                "..jakarta.transaction..");
    
    @ArchTest
    static final ArchRule services_should_only_depend_on_persistence_or_other_services =
        classes().that()
            .resideInAPackage("..persistence..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..jakarta.persistence..", "..jakarta.validation..",
                "..jakarta.transaction..");
    
    @ArchTest
    static final ArchRule persistence_should_not_depend_on_services = noClasses().that()
        .resideInAPackage("..persistence..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("..application..");
    
    @ArchTest
    static final ArchRule services_should_only_be_depended_on_by_controllers_or_other_services =
        classes().that()
            .resideInAPackage("..persistence..")
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage("..application..");
}
