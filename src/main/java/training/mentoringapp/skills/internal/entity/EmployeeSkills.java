package training.mentoringapp.skills.internal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private Long employeeId;

    @OneToMany(mappedBy = "employeeSkills", cascade = CascadeType.ALL)
    private List<AcquiredSkill> acquiredSkills = new ArrayList<>();

    public EmployeeSkills(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<AcquiredSkill> acquireSkills(Map<Long, Integer> filteredSkills) {
        return filteredSkills.entrySet().stream()
                .map(e -> new AcquiredSkill(e.getKey(), e.getValue()))
                .peek(this::acquireSkill)
                .toList();

    }

    private void acquireSkill(AcquiredSkill acquiredSkill) {
        acquiredSkills.add(acquiredSkill);
        acquiredSkill.setEmployeeSkills(this);
    }
}
