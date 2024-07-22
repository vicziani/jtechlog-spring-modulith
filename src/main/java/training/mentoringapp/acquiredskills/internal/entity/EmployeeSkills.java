package training.mentoringapp.acquiredskills.internal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private Long employeeId;

    @ElementCollection
    @Getter
    private List<AcquiredSkill> acquiredSkills = new ArrayList<>();

    public EmployeeSkills(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<AcquiredSkill> acquireSkills(List<AcquiredSkill> newAcquiredSkills) {
        var acquiredSkillsMap = acquiredSkills.stream().collect(Collectors.toMap(AcquiredSkill::skillId, Function.identity()));

        for (var newAcquiredSkill : newAcquiredSkills) {
            var existing = acquiredSkillsMap.get(newAcquiredSkill.skillId());
            if (existing == null || existing.level() < newAcquiredSkill.level()) {
                acquiredSkillsMap.put(newAcquiredSkill.skillId(), newAcquiredSkill);
            }
        }

        acquiredSkills = acquiredSkillsMap.values().stream().toList();
        return acquiredSkills;
    }

}
