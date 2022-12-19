package training.mentoringapp.skills.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.mentoringapp.skills.internal.entity.EmployeeSkills;

import java.util.Optional;

public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Long> {


    @Query("select distinct s from EmployeeSkills s left join fetch s.acquiredSkills where s.employeeId = :employeeId")
    Optional<EmployeeSkills> findByEmployeeId(@Param("employeeId") Long employeeId);
}
