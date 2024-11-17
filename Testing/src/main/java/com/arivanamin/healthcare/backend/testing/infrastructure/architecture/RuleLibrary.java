package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.*;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE)
class RuleLibrary {
    
    @ArchTest
    static final ArchTests LIBRARY = ArchTests.in(RuleSets.class);
    
    @ArchTest
    static final ArchTests SLICES_ISOLATION_RULES = ArchTests.in(SlicesIsolationRules.class);
}
