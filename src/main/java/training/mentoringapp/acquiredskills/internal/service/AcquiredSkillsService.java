package training.mentoringapp.acquiredskills.internal.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import training.mentoringapp.acquiredskills.internal.repository.EmployeeSkillsRepository;
import training.mentoringapp.employees.EmployeesFacade;
import training.mentoringapp.employees.dto.EmployeeHasBeenDeletedEvent;
import training.mentoringapp.acquiredskills.internal.dto.AcquireSkillsCommand;
import training.mentoringapp.acquiredskills.internal.entity.AcquiredSkill;
import training.mentoringapp.acquiredskills.internal.entity.EmployeeSkills;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AcquiredSkillsService {

    private EmployeeSkillsRepository employeeSkillsRepository;

    private EmployeesFacade employeesFacade;

    @Transactional
    public List<AcquiredSkill> acquireSkills(AcquireSkillsCommand command) {
        // Throws exception when not found
        employeesFacade.findEmployeeById(command.employeeId());
        var employeeSkills = employeeSkillsRepository
                .findByEmployeeId(command.employeeId())
                .orElse(new EmployeeSkills(command.employeeId()));

        var result = employeeSkills.acquireSkills(command.acquiredSkills());

        if (employeeSkills.getId() == null) {
            employeeSkillsRepository.save(employeeSkills);
        }

        return result;
    }

    @Async
    @TransactionalEventListener
    public void handleEmployeeHasBeenDeletedEvent(EmployeeHasBeenDeletedEvent event) {
        log.info("Event has arrived: {}", event);
        var employeeSkills = employeeSkillsRepository.findByEmployeeId(event.employeeId());
        employeeSkills.ifPresent(skills -> employeeSkillsRepository.delete(skills));
    }

}
