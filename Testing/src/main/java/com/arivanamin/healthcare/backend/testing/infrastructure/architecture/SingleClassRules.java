package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class SingleClassRules {
    
    @ArchTest
    static final ArchRule core_should_only_access_classes_in_core_itself = noClasses().that()
        .resideInAPackage("..core..")
        .should()
        .accessClassesThat()
        .resideOutsideOfPackages("..core..", "java..");
}
