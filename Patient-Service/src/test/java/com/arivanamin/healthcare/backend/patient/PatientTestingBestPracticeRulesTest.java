package com.arivanamin.healthcare.backend.patient;

import com.arivanamin.healthcare.backend.testing.architecture.TestingBestPracticeRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;

@AnalyzeClasses (packages = BASE_PACKAGE, importOptions = ImportOption.OnlyIncludeTests.class)
class PatientTestingBestPracticeRulesTest implements TestingBestPracticeRules {
    
}
