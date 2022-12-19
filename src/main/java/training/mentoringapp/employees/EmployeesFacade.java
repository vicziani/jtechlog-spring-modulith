package training.mentoringapp.employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import training.mentoringapp.employees.dto.EmployeeDto;
import training.mentoringapp.employees.internal.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeesFacade {

    private EmployeeService employeeService;

    public EmployeeDto findEmployeeById(long id) {
        return employeeService.findEmployeeById(id);
    }
}
