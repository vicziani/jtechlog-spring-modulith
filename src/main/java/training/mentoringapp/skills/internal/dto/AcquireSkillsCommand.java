package training.mentoringapp.skills.internal.dto;

import lombok.Data;

import java.util.List;

@Data
public class AcquireSkillsCommand {

    private List<Long> skills;

    private List<Integer> levels;
}
