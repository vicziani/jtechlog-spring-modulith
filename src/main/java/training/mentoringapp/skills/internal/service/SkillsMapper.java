package training.mentoringapp.skills.internal.service;

import org.mapstruct.Mapper;
import training.mentoringapp.skills.internal.dto.CreateSkillCommand;
import training.mentoringapp.skills.internal.dto.SkillDto;
import training.mentoringapp.skills.internal.entity.Skill;

@Mapper(componentModel = "spring")
public interface SkillsMapper {
    Skill toSkill(CreateSkillCommand command);

    SkillDto toDto(Skill skill);
}
