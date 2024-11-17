package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class NamingConventionRules {
    
    @ArchTest
    static ArchRule command_should_be_prefixed = classes().that()
        .resideInAPackage("..command..")
        .should()
        .haveSimpleNameEndingWith("Command");
    
    @ArchTest
    static ArchRule query_should_be_prefixed =
        classes().that().resideInAPackage("..query..").should().haveSimpleNameEndingWith("Query");
    
    @ArchTest
    static ArchRule controllers_should_be_suffixed = classes().that()
        .resideInAPackage("..application..")
        .and()
        .areAnnotatedWith(RestController.class)
        .or()
        .areAssignableTo(Controller.class)
        .should()
        .haveSimpleNameEndingWith("Controller");
    
    @ArchTest
    static ArchRule classes_named_controller_should_be_in_a_controller_package = classes().that()
        .haveSimpleNameEndingWith("Controller")
        .should()
        .resideInAPackage("..application..");
}
