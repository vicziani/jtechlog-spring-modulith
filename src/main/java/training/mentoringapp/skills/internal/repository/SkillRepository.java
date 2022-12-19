package training.mentoringapp.skills.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.mentoringapp.skills.internal.entity.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByIdIn(List<Long> skills);
}
