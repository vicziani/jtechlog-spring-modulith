package training.mentoringapp.skills.internal.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import training.mentoringapp.skills.internal.dto.CreateSkillCommand;
import training.mentoringapp.skills.internal.dto.SkillDto;
import training.mentoringapp.skills.internal.service.SkillsService;

@RestController
@AllArgsConstructor
public class SkillsController {

    private SkillsService skillsService;

    @PostMapping("/api/skills")
    public SkillDto createSkill(@RequestBody CreateSkillCommand command) {
        return skillsService.createSkill(command);
    }

}
