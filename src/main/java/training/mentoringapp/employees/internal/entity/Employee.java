package training.mentoringapp.employees.internal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@NoArgsConstructor(access = PRIVATE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<Address> addresses = new ArrayList<>();

    public Employee(String name) {
        this.name = name;
    }

    public void addAddresses(List<Address> addresses) {
        this.addresses.addAll(addresses);
    }
}
