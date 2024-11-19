package com.arivanamin.healthcare.backend.patient;

import com.arivanamin.healthcare.backend.testing.architecture.CleanArchitectureRules;
import com.arivanamin.healthcare.backend.testing.architecture.CommonBestPracticeRules;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE)
public class PatientArchitectureTest implements CommonBestPracticeRules, CleanArchitectureRules {
    
}
