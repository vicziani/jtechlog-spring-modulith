package training.mentoringapp.employees.internal.entity;

import jakarta.persistence.*;

@Embeddable
public record Address(String city) {

}
