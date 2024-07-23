package training.mentoringapp.employees.dto;

import org.springframework.modulith.events.Externalized;

@Externalized
public record EmployeeHasBeenDeletedEvent(long employeeId) {

}
