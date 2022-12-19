package training.mentoringapp.skills.internal.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import training.mentoringapp.employees.EmployeesFacade;
import training.mentoringapp.employees.dto.EmployeeHasDeletedEvent;
import training.mentoringapp.skills.internal.dto.AcquireSkillsCommand;
import training.mentoringapp.skills.internal.dto.AcquiredSkillDto;
import training.mentoringapp.skills.internal.dto.CreateSkillCommand;
import training.mentoringapp.skills.internal.dto.SkillDto;
import training.mentoringapp.skills.internal.entity.EmployeeSkills;
import training.mentoringapp.skills.internal.entity.Skill;
import training.mentoringapp.skills.internal.repository.EmployeeSkillsRepository;
import training.mentoringapp.skills.internal.repository.SkillRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SkillsService {

    private SkillRepository skillRepository;

    private EmployeeSkillsRepository employeeSkillsRepository;

    private EmployeesFacade employeesFacade;
    private SkillsMapper mapper;

    public SkillDto createSkill(CreateSkillCommand command) {
        var skill = mapper.toSkill(command);
        skillRepository.save(skill);
        return mapper.toDto(skill);
    }

    @Transactional
    public List<AcquiredSkillDto> acquireSkills(long employeeId, AcquireSkillsCommand command) {
        var employee = employeesFacade.findEmployeeById(employeeId);
        var employeeSkills = employeeSkillsRepository
                .findByEmployeeId(employeeId)
                .orElse(new EmployeeSkills(employeeId));

        var skills = skillRepository.findByIdIn(command.getSkills())
                .stream().collect(Collectors.toMap(Skill::getId, skill -> skill));

        var filteredSkills = filterSkills(command.getSkills(), command.getLevels(), skills);
        employeeSkills.acquireSkills(filteredSkills);

        employeeSkillsRepository.save(employeeSkills);

        return filteredSkills.entrySet().stream()
                .map(v -> new AcquiredSkillDto(skills.get(v.getKey()).getName(), v.getValue()))
                .toList();
    }

    private Map<Long, Integer> filterSkills(List<Long> skills, List<Integer> levels, Map<Long, Skill> validSkills) {
        var filtered = new HashMap<Long, Integer>();
        if (skills.size() != levels.size()) {
            throw new IllegalArgumentException("Size not same: " + skills.size() + " " + levels.size());
        }
        for (var i = 0; i < skills.size(); i++) {
            var skill = skills.get(i);
            if (validSkills.containsKey(skill)) {
                filtered.put(skill, levels.get(i));
            }
        }
        return filtered;
    }

    @Async
    @TransactionalEventListener
    public void handleEmployeeHasDeletedEvent(EmployeeHasDeletedEvent event) {
        log.info("Event has arrived: {}", event);
        var employeeSkills = employeeSkillsRepository.findByEmployeeId(event.getEmployeeId());
        if (employeeSkills.isPresent()) {
            employeeSkillsRepository.delete(employeeSkills.get());
        }
    }

}
