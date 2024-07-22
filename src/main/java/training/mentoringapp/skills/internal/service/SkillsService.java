package training.mentoringapp.skills.internal.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import training.mentoringapp.skills.internal.repository.SkillRepository;
import training.mentoringapp.skills.internal.dto.CreateSkillCommand;
import training.mentoringapp.skills.internal.dto.SkillDto;

@Service
@AllArgsConstructor
@Slf4j
public class SkillsService {

    private SkillRepository skillRepository;

    private SkillsMapper mapper;

    public SkillDto createSkill(CreateSkillCommand command) {
        var skill = mapper.toSkill(command);
        skillRepository.save(skill);
        return mapper.toDto(skill);
    }

}
