package training.mentoringapp.employees.dto;

import training.mentoringapp.employees.internal.entity.Address;

import java.util.List;

public record EmployeeDto(Long id, String name, List<Address> addresses) {

}
