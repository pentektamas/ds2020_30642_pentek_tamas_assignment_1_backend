package ds2020.assignment1.services;

import ds2020.assignment1.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ds2020.assignment1.dtos.PatientDTO;
import ds2020.assignment1.dtos.builders.PatientBuilder;
import ds2020.assignment1.entities.Account;
import ds2020.assignment1.entities.MedicationPlan;
import ds2020.assignment1.entities.Patient;
import ds2020.assignment1.repositories.AccountRepository;
import ds2020.assignment1.repositories.MedicationPlanRepository;
import ds2020.assignment1.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AccountRepository accountRepository;
    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public PatientService(PatientRepository patientRepo, AccountRepository accountRepository, MedicationPlanRepository medicationPlanRepository) {
        this.patientRepository = patientRepo;
        this.accountRepository = accountRepository;
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> foundPatient = patientRepository.findById(id);
        if (!foundPatient.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(foundPatient.get());
    }

    public UUID insertPatient(PatientDTO patientDTO) {
        Patient patient = PatientBuilder.toPatientEntity(patientDTO);
        Account account = patient.getAccount();
        MedicationPlan medicationPlan = patientDTO.getMedicationPlan();
        patient.setMedicationPlan(medicationPlan);
        medicationPlanRepository.save(medicationPlan);
        accountRepository.save(account);
        patient = patientRepository.save(patient);
        return patient.getId();
    }

    public UUID updatePatient(UUID patientId, PatientDTO patientDTO) {
        Optional<Patient> foundPatient = patientRepository.findById(patientId);
        if (!foundPatient.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patientId);
        }
        Patient patient = foundPatient.get();
        Optional<Account> foundAccount = accountRepository.findById(patient.getAccount().getId());
        Account account = foundAccount.get();
        account.setAccountType(patientDTO.getAccount().getAccountType());
        account.setPassword(patientDTO.getAccount().getPassword());
        account.setUserName(patientDTO.getAccount().getUserName());

        patient.setName(patientDTO.getName());
        patient.setBirthdate(patientDTO.getBirthdate());
        patient.setGender(patientDTO.getGender());
        patient.setAddress(patientDTO.getAddress());
        patient.setMedicalRecord(patientDTO.getMedicalRecord());
        patient.setMedicationPlan(patientDTO.getMedicationPlan());
        patient.setAccount(account);

        accountRepository.save(patient.getAccount());
        if (patient.getMedicationPlan() != null)
            medicationPlanRepository.save(patient.getMedicationPlan());
        patient = patientRepository.save(patient);
        return patient.getId();
    }

    public void deletePatient(UUID patientId) {
        Patient patient = PatientBuilder.toPatientEntity(findPatientById(patientId));
        patientRepository.deleteById(patientId);
        accountRepository.delete(patient.getAccount());
        if (patient.getMedicationPlan() != null)
            medicationPlanRepository.delete(patient.getMedicationPlan());
    }

    public PatientDTO findPatientByAccountId(UUID accountId) {
        Optional<Patient> foundPatient = patientRepository.findPatientByAccount_Id(accountId);
        if (!foundPatient.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + accountId);
        }
        return PatientBuilder.toPatientDTO(foundPatient.get());
    }

}
