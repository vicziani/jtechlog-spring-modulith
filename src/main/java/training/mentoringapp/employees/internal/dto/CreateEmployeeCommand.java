package training.mentoringapp.employees.internal.dto;

import training.mentoringapp.employees.internal.entity.Address;

import java.util.List;

public record CreateEmployeeCommand(String name, List<Address> addresses) {

}
