package training.mentoringapp.skills.internal.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training.mentoringapp.skills.internal.dto.AcquireSkillsCommand;
import training.mentoringapp.skills.internal.dto.AcquiredSkillDto;
import training.mentoringapp.skills.internal.dto.CreateSkillCommand;
import training.mentoringapp.skills.internal.dto.SkillDto;
import training.mentoringapp.skills.internal.repository.SkillRepository;
import training.mentoringapp.skills.internal.service.SkillsService;

import java.util.List;

@RestController
@AllArgsConstructor
public class SkillsController {

    private SkillsService skillsService;

    @PostMapping("/api/skills")
    public SkillDto createSkill(@RequestBody CreateSkillCommand command) {
        return skillsService.createSkill(command);
    }

    @PostMapping("/api/employees/{employeeId}/skills")
    public List<AcquiredSkillDto> acquireSkills(@PathVariable("employeeId") long employeeId, @RequestBody AcquireSkillsCommand command) {
        return skillsService.acquireSkills(employeeId, command);
    }
}
