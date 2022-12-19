package training.mentoringapp;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.model.ApplicationModules;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ApplicationModulesTest {

    @Test
    void testModules() {
        var modules = ApplicationModules.of(MentoringAppApplication.class);

        modules.verify();

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}
