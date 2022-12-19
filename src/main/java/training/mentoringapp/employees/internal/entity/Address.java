package training.mentoringapp.employees.internal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@NoArgsConstructor(access = PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @ManyToOne
    private Employee employee;

    public Address(String city) {
        this.city = city;
    }
}
