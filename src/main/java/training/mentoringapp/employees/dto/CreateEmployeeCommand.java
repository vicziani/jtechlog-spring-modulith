package training.mentoringapp.employees.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateEmployeeCommand {

    private String name;

    private List<CreateAddressDto> addresses;
}
