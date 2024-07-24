package training.mentoringapp.acquiredskills.internal.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training.mentoringapp.acquiredskills.internal.dto.AcquireSkillsCommand;
import training.mentoringapp.acquiredskills.internal.entity.AcquiredSkill;
import training.mentoringapp.acquiredskills.internal.service.AcquiredSkillsService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AcquiredSkillsController {

    private AcquiredSkillsService acquiredSkillsService;

    @PostMapping("/api/employees/{employeeId}/skills")
    public List<AcquiredSkill> acquireSkills(@PathVariable("employeeId") long employeeId, @RequestBody AcquireSkillsCommand command) {
        if (employeeId != command.employeeId()) {
            throw new IllegalArgumentException("Employee id mismatch: %d != %d".formatted(employeeId, command.employeeId()));
        }
        return acquiredSkillsService.acquireSkills(command);
    }

    @GetMapping("/api/employees/{employeeId}/skills")
    public List<AcquiredSkill> getAcquiredSkills(@PathVariable("employeeId") long employeeId) {
        return acquiredSkillsService.getAcquiredSkills(employeeId);
    }

}
