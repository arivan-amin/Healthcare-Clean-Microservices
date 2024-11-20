package com.arivanamin.healthcare.backend.testing.architecture;

import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
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
    
    Set<String> PROHIBITED_JPA_METHODS = Set.of("equals", "hashCode", "toString");
    
    Collection<String> REQUIRED_PREFIXES = List.of("public/", "protected/");
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_DEPEND_ON_ANY_PERSISTENCE_MECHANISM = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAnyPackage(PERSISTENCE_PACKAGES);
    
    @ArchTest
    ArchRule CORE_SHOULD_NOT_DEPEND_ON_DETAILS_OR_APPLICATION = noClasses().that()
        .resideInAPackage(CORE_PACKAGE)
        .should()
        .accessClassesThat()
        .resideInAnyPackage(DETAILS_PACKAGE, APPLICATION_PACKAGE);
    
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
    ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED = layeredArchitecture().consideringAllDependencies()
        .layer(APPLICATION_LAYER)
        .definedBy(BASE_PACKAGE + APPLICATION_PACKAGE)
        .layer(CORE_LAYER)
        .definedBy(BASE_PACKAGE + CORE_PACKAGE)
        .layer(DETAILS_LAYER)
        .definedBy(BASE_PACKAGE + DETAILS_PACKAGE)
        
        .whereLayer(APPLICATION_LAYER)
        .mayNotBeAccessedByAnyLayer()
        .whereLayer(DETAILS_LAYER)
        .mayOnlyBeAccessedByLayers(APPLICATION_LAYER);
    
    @ArchTest
    ArchRule ALL_PUBLIC_METHODS_IN_THE_CONTROLLER_LAYER_SHOULD_RETURN_RESPONSE_WRAPPERS =
        methods().that()
            .areDeclaredInClassesThat()
            .resideInAPackage(CONTROLLER_PACKAGE)
            .and()
            .arePublic()
            .should(haveReturnTypeWithResponseSuffix())
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
        .or()
        .areAssignableTo(Controller.class)
        .should()
        .resideInAPackage(CONTROLLER_PACKAGE);
    
    @ArchTest
    ArchRule CLASSES_NAMED_CONTROLLER_SHOULD_BE_IN_A_CONTROLLER_PACKAGE = classes().that()
        .haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
        .should()
        .resideInAPackage(APPLICATION_PACKAGE);
    
    @ArchTest
    ArchRule DO_NOT_ANNOTATE_REST_CONTROLLER_WITH_REQUEST_MAPPING = noClasses().that()
        .areAnnotatedWith(RestController.class)
        .or()
        .areMetaAnnotatedWith(RestController.class)
        .should()
        .beAnnotatedWith(RequestMapping.class)
        .orShould()
        .beMetaAnnotatedWith(RequestMapping.class)
        .because("Controller method mapping is preferred to class mapping, use base path property");
    
    @ArchTest
    ArchRule VALIDATE_REST_CONTROLLER_METHODS_REQUEST_BODY_PARAMETER = methods().that()
        .areDeclaredInClassesThat()
        .areAnnotatedWith(RestController.class)
        .or()
        .areMetaAnnotatedWith(RestController.class)
        .should(new ArchCondition<>("") {
            @Override
            public void check (JavaMethod method, ConditionEvents events) {
                var requestBodyParameter = Arrays.stream(method.reflect().getParameters())
                    .filter(parameter -> parameter.isAnnotationPresent(RequestBody.class))
                    .findFirst()
                    .orElse(null);
                
                if (requestBodyParameter != null &&
                    !requestBodyParameter.isAnnotationPresent(Valid.class)) {
                    String message =
                        ("Method %s uses a @RequestBody parameter but is missing the @Valid " +
                            "annotation").formatted(method.getFullName());
                    events.add(SimpleConditionEvent.violated(method, message));
                }
            }
        })
        .because("Request body parameters must always be validated using @Valid annotation.");
    
    @ArchTest
    ArchRule VALIDATE_REST_CONTROLLER_METHODS_URL_PREFIXES = methods().that()
        .arePublic()
        .and()
        .areDeclaredInClassesThat()
        .areAnnotatedWith(RestController.class)
        .or()
        .areMetaAnnotatedWith(RestController.class)
        .should(new ArchCondition<>("") {
            
            @Override
            public void check (JavaMethod method, ConditionEvents events) {
                method.getAnnotations()
                    .stream()
                    .filter(annotation -> annotation.getRawType().getName().endsWith("Mapping"))
                    .forEach(annotation -> validateMappingAnnotation(annotation, method, events));
            }
            
            private void validateMappingAnnotation (JavaAnnotation<JavaMethod> annotation,
                                                    JavaMethod method, ConditionEvents events) {
                String[] mappings = (String[]) annotation.get("value").orElse(null);
                if (mappings == null || mappings.length == 0)
                    return;
                
                long validMappingsCount = Arrays.stream(mappings)
                    .filter(mapping -> REQUIRED_PREFIXES.stream().anyMatch(mapping::startsWith))
                    .count();
                
                if (validMappingsCount == 0) {
                    events.add(SimpleConditionEvent.violated(method,
                        String.format("Method %s does not use any of the required prefixes: %s",
                            method.getFullName(), REQUIRED_PREFIXES)));
                }
                else if (validMappingsCount != mappings.length) {
                    events.add(SimpleConditionEvent.violated(method, String.format(
                        "Method %s contains mappings with prefixes not recognized: %s",
                        method.getFullName(), REQUIRED_PREFIXES)));
                }
            }
        })
        .because(
            "URLs must be recognized based on their path, whether they are public or require " +
                "authentication");
    
    @ArchTest
    ArchRule AVOID_BEAN_ANNOTATION_WITH_QUALIFIER =
        methods().that().areAnnotatedWith(Bean.class).should().notBeAnnotatedWith(Qualifier.class);
    
    @ArchTest
    ArchRule CONFIGURATIONS_SHOULD_NOT_BE_PUBLIC =
        classes().that().areAnnotatedWith(Configuration.class).should().notBePublic();
    
    @ArchTest
    ArchRule ONLY_DECLARE_BEANS_IN_CONFIGURATION_CLASSES = classes().that()
        .resideOutsideOfPackages(APPLICATION_PACKAGE)
        .should()
        .notBeAnnotatedWith(Component.class)
        .andShould()
        .notBeAnnotatedWith(Service.class);
    
    @ArchTest
    ArchRule REST_CONTROLLERS_SHOULD_NOT_BE_PUBLIC =
        classes().that().areAnnotatedWith(RestController.class).should().notBePublic();
    
    @ArchTest
    ArchRule REST_CONTROLLERS_METHODS_SHOULD_BE_PUBLIC = methods().that()
        .areDeclaredInClassesThat()
        .areAnnotatedWith(RestController.class)
        .and(annotatedWith(GetMapping.class).or(annotatedWith(PostMapping.class))
            .or(annotatedWith(DeleteMapping.class))
            .or(annotatedWith(PutMapping.class))
            .or(annotatedWith(PatchMapping.class)))
        .should()
        .bePublic();
    
    @ArchTest
    ArchRule DOCUMENTED_REST_CONTROLLERS =
        classes().that().areAnnotatedWith(RestController.class).should().beAnnotatedWith(Tag.class);
    
    @ArchTest
    ArchRule DOCUMENT_REST_CONTROLLER_METHODS_WITH_OPERATION = methods().that()
        .areDeclaredInClassesThat()
        .areAnnotatedWith(RestController.class)
        .and(annotatedWith(GetMapping.class).or(annotatedWith(PostMapping.class))
            .or(annotatedWith(DeleteMapping.class))
            .or(annotatedWith(PutMapping.class))
            .or(annotatedWith(PatchMapping.class)))
        .should()
        .beAnnotatedWith(Operation.class);
    
    @ArchTest
    ArchRule AVOID_REQUEST_MAPPING_IN_REST_CONTROLLER_METHODS = methods().that()
        .areDeclaredInClassesThat()
        .areAnnotatedWith(RestController.class)
        .should()
        .notBeAnnotatedWith(RequestMapping.class)
        .because("Use Get, Post, Delete or Put Mapping instead.");
    
    @ArchTest
    ArchRule NO_BYPASS_OF_PROXY_LOGIC =
        no_classes_should_directly_call_other_methods_declared_in_the_same_class_that_are_annotated_with(
            Async.class);
    
    @ArchTest
    ArchRule ENTITY_CLASSES_SHOULD_NOT_IMPLEMENT_EQUALS_HASHCODE_AND_TO_STRING = classes().that()
        .areAnnotatedWith(Entity.class)
        .or()
        .areMetaAnnotatedWith(Entity.class)
        .and()
        .resideInAPackage(DETAILS_PACKAGE)
        .should(notImplementEqualsHashCodeOrToString());
    
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
    
    private static ArchCondition<JavaClass> notImplementEqualsHashCodeOrToString () {
        
        return new ArchCondition<>("") {
            
            @Override
            public void check (JavaClass javaClass, ConditionEvents conditionEvents) {
                
                for (JavaMethod method : javaClass.getMethods()) {
                    if (isProhibitedMethodImplemented(javaClass, method)) {
                        String message =
                            "Class %s implements %s method".formatted(javaClass.getName(),
                                method.getName());
                        conditionEvents.add(SimpleConditionEvent.violated(javaClass, message));
                    }
                }
            }
            
            private boolean isProhibitedMethodImplemented (JavaClass javaClass, JavaMethod method) {
                return PROHIBITED_JPA_METHODS.contains(method.getName()) &&
                    method.getOwner().equals(javaClass);
            }
        };
    }
}
