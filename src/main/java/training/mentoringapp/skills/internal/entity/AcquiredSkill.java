package training.mentoringapp.skills.internal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AcquiredSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long skillId;

    private int level;

    @ManyToOne
    @Setter
    private EmployeeSkills employeeSkills;

    public AcquiredSkill(Long skillId, int level) {
        this.skillId = skillId;
        this.level = level;
    }
}
