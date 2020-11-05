package ds2020.assignment1.repositories;

import ds2020.assignment1.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findDoctorByAccount_Id(UUID accountID);
}
