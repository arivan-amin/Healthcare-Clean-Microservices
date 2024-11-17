package com.arivanamin.healthcare.backend.testing.infrastructure.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class LayeredArchitectureRules {
    
    public static final String APPLICATION_LAYER = "Controllers";
    
    public static final String CORE_LAYER = "Services";
    
    public static final String PERSISTENCE_LAYER = "Persistence";
    
    @ArchTest
    static final ArchRule layer_dependencies_are_respected =
        
        layeredArchitecture().consideringAllDependencies()
            .layer(APPLICATION_LAYER)
            .definedBy(BASE_PACKAGE + "..application..")
            .layer(CORE_LAYER)
            .definedBy(BASE_PACKAGE + "..core..")
            .layer(PERSISTENCE_LAYER)
            .definedBy(BASE_PACKAGE + "..persistence..")
            
            .whereLayer(CORE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER, PERSISTENCE_LAYER)
            .whereLayer(APPLICATION_LAYER)
            .mayNotBeAccessedByAnyLayer()
            .whereLayer(PERSISTENCE_LAYER)
            .mayOnlyBeAccessedByLayers(APPLICATION_LAYER);
}
