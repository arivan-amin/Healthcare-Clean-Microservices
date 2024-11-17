package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.freeze.FreezingArchRule.freeze;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class FrozenRules {
    
    // todo 11/17/24 - check if needed
    @ArchTest
    static final ArchRule no_classes_should_depend_on_service =
        freeze(noClasses().should().dependOnClassesThat().resideInAPackage("..service.."));
}

