package ds2020.assignment1.services;

import ds2020.assignment1.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ds2020.assignment1.dtos.DoctorDTO;
import ds2020.assignment1.dtos.builders.DoctorBuilder;
import ds2020.assignment1.entities.Account;
import ds2020.assignment1.entities.Doctor;
import ds2020.assignment1.repositories.AccountRepository;
import ds2020.assignment1.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.doctorRepository = doctorRepository;
    }


    public UUID insertDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = DoctorBuilder.toDoctorEntity(doctorDTO);
        Account account = doctor.getAccount();
        accountRepository.save(account);
        doctor = doctorRepository.save(doctor);
        return doctor.getId();
    }

    public DoctorDTO findDoctorByAccountId(UUID accountId) {
        Optional<Doctor> foundDoctor = doctorRepository.findDoctorByAccount_Id(accountId);
        if (!foundDoctor.isPresent()) {
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + accountId);
        }
        return DoctorBuilder.toDoctorDTO(foundDoctor.get());
    }
}
