package com.movil.started.kid;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.movil.started.kid");

        noClasses()
            .that()
                .resideInAnyPackage("com.movil.started.kid.service..")
            .or()
                .resideInAnyPackage("com.movil.started.kid.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.movil.started.kid.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
