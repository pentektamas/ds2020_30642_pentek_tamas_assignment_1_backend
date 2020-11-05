package ds2020.assignment1.repositories;

import ds2020.assignment1.entities.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

    Optional<Caregiver> findCaregiverByAccount_Id(UUID accountID);
}
