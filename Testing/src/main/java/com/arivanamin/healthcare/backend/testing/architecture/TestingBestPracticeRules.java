package com.arivanamin.healthcare.backend.testing.architecture;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.library.GeneralCodingRules.testClassesShouldResideInTheSamePackageAsImplementation;

public interface TestingBestPracticeRules {
    
    String TEST_SUFFIX = "Test";
    
    @ArchTest
    ArchRule TEST_CLASSES_PLACEMENT = testClassesShouldResideInTheSamePackageAsImplementation();
    
    @ArchTest
    ArchRule TEST_CLASSES_SHOULD_BE_PACKAGE_PRIVATE = classes().that()
        .haveSimpleNameEndingWith(TEST_SUFFIX)
        .and()
        .areAssignableTo(BaseUnitTest.class)
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .should()
        .bePackagePrivate();
    
    @ArchTest
    ArchRule TEST_METHODS_SHOULD_BE_PACKAGE_PRIVATE =
        methods().that().areAnnotatedWith(Test.class).should().bePackagePrivate();
}
