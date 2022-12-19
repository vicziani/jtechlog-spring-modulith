package training.mentoringapp.employees.internal.service;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import training.mentoringapp.employees.dto.AddressDto;
import training.mentoringapp.employees.dto.CreateAddressDto;
import training.mentoringapp.employees.dto.CreateEmployeeCommand;
import training.mentoringapp.employees.dto.EmployeeDto;
import training.mentoringapp.employees.internal.entity.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeesDto(List<Employee> employees);

    AddressDto toAddressDto(Address address);

    List<AddressDto> toAddressesDto(List<Address> addresses);

    Employee toEmployee(CreateEmployeeCommand command);

    Address toAddress(CreateAddressDto createAddressDto);

    @AfterMapping
    default void setAddressEmployee(@MappingTarget Employee employee){
        if (employee.getAddresses() != null) {
            for (Address address : employee.getAddresses()) {
                address.setEmployee(employee);
            }
        }
    }
}
