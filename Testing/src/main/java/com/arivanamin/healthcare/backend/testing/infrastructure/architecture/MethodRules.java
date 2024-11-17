package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class MethodRules {
    
    @ArchTest
    static ArchRule all_public_methods_in_the_controller_layer_should_return_API_response_wrappers =
        methods().that()
            .areDeclaredInClassesThat()
            .resideInAPackage("..application..")
            .and()
            .arePublic()
            .should()
            .haveNameEndingWith("*Response")
            .because("we do not want to couple the api directly to the return types of the " +
                "core module");
}
