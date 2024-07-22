package training.mentoringapp.skills;


import org.junit.jupiter.api.Test;
import training.mentoringapp.acquiredskills.internal.entity.AcquiredSkill;
import training.mentoringapp.acquiredskills.internal.entity.EmployeeSkills;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeSkillsTest {

    @Test
    void acquireSkills() {
        var employeeSkills = new EmployeeSkills(1L);
        employeeSkills.acquireSkills(List.of(new AcquiredSkill(1L, 3), new AcquiredSkill(2L, 3)));
        employeeSkills.acquireSkills(List.of(new AcquiredSkill(1L, 2), new AcquiredSkill(2L, 4)));

        assertThat(employeeSkills.getAcquiredSkills()).containsExactlyInAnyOrder(
                new AcquiredSkill(1L, 3),
                new AcquiredSkill(2L, 4)
        );
    }
}
