package training.mentoringapp.skills.internal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcquiredSkillDto {

    private String skillName;

    private long level;
}
