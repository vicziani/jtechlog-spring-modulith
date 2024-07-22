package training.mentoringapp.employees.internal.service;

import org.mapstruct.Mapper;
import training.mentoringapp.employees.dto.CreateEmployeeCommand;
import training.mentoringapp.employees.dto.EmployeeDto;
import training.mentoringapp.employees.internal.entity.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeesDto(List<Employee> employees);

    Employee toEmployee(CreateEmployeeCommand command);

}
