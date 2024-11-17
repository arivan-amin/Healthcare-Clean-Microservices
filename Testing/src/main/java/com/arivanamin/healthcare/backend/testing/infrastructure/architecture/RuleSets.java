package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.*;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE)
class RuleSets {
    
    @ArchTest
    private final ArchTests NAMING_CONVENTION_RULES = ArchTests.in(NamingConventionRules.class);
}
