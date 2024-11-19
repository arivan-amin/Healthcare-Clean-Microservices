package com.arivanamin.healthcare.backend.testing.architecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import java.util.logging.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.DependencyRules.NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

public interface CommonBestPracticeRules {
    
    // todo 11/17/24 - check if needed
    @ArchTest
    ArchRule NO_ACCESSES_TO_UPPER_PACKAGE = NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;
    
    @ArchTest
    ArchRule INTERFACES_SHOULD_NOT_HAVE_NAMES_ENDING_WITH_THE_WORD_INTERFACE =
        noClasses().that().areInterfaces().should().haveNameMatching(".*Interface");
    
    @ArchTest
    ArchRule INTERFACES_SHOULD_NOT_HAVE_SIMPLE_CLASS_NAMES_CONTAINING_THE_WORD_INTERFACE =
        noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface");
    
    @ArchTest
    ArchRule INTERFACES_MUST_NOT_BE_PLACED_IN_IMPLEMENTATION_PACKAGES =
        noClasses().that().resideInAPackage("..application..").should().beInterfaces();
    
    // common
    @ArchTest
    ArchRule NO_ACCESS_TO_STANDARD_STREAMS = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
    
    @ArchTest
    ArchRule NO_GENERIC_EXCEPTIONS = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
    
    @ArchTest
    ArchRule NO_JAVA_UTIL_LOGGING = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
    
    @ArchTest
    ArchRule LOGGERS_SHOULD_BE_PRIVATE_STATIC_FINAL = fields().that()
        .haveRawType(Logger.class)
        .should()
        .bePrivate()
        .andShould()
        .beStatic()
        .andShould()
        .beFinal()
        .because("we agreed on this convention");
    
    // todo 11/18/24 - maybe forbid old java date too, only local date and time should be used
    @ArchTest
    ArchRule NO_JODA_TIME = NO_CLASSES_SHOULD_USE_JODATIME;
    
    @ArchTest
    ArchRule NO_FIELD_INJECTION = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
}
