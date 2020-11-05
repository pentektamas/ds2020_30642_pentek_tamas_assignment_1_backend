package ds2020.assignment1.repositories;

import ds2020.assignment1.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

}
