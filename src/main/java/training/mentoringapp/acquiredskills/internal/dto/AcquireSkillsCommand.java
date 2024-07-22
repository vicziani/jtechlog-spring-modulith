package training.mentoringapp.acquiredskills.internal.dto;

import training.mentoringapp.acquiredskills.internal.entity.AcquiredSkill;

import java.util.List;

public record AcquireSkillsCommand(long employeeId, List<AcquiredSkill> acquiredSkills) {

}
