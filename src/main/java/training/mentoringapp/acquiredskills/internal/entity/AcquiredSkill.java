package training.mentoringapp.acquiredskills.internal.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record AcquiredSkill(Long skillId, int level) {

}
