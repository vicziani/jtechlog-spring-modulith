package training.mentoringapp.employees.internal.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.mentoringapp.employees.dto.*;
import training.mentoringapp.employees.internal.entity.Employee;
import training.mentoringapp.employees.internal.repository.EmployeeRepository;
import training.mentoringapp.skills.internal.service.SkillsService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    private ApplicationEventPublisher publisher;

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = employeeMapper.toEmployee(command);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    public List<EmployeeDto> listEmployees() {
        return employeeMapper.toEmployeesDto(employeeRepository.findAllWithAddresses());
    }

    public EmployeeDto findEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id)));
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employeeToModify = employeeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeToModify.setName(command.getName());
        return employeeMapper.toEmployeeDto(employeeToModify);
    }

    @Transactional
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);

        publisher.publishEvent(new EmployeeHasDeletedEvent(id));
    }
}
