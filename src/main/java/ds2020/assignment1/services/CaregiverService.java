package ds2020.assignment1.services;

import ds2020.assignment1.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ds2020.assignment1.dtos.CaregiverDTO;
import ds2020.assignment1.dtos.PatientDTO;
import ds2020.assignment1.dtos.builders.CaregiverBuilder;
import ds2020.assignment1.dtos.builders.PatientBuilder;
import ds2020.assignment1.entities.Account;
import ds2020.assignment1.entities.Caregiver;
import ds2020.assignment1.entities.Patient;
import ds2020.assignment1.repositories.AccountRepository;
import ds2020.assignment1.repositories.CaregiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.caregiverRepository = caregiverRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(UUID caregiverId) {
        Optional<Caregiver> foundCaregiver = caregiverRepository.findById(caregiverId);
        if (!foundCaregiver.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + caregiverId);
        }
        return CaregiverBuilder.toCaregiverDTO(foundCaregiver.get());
    }

    public UUID insertCaregiver(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toCaregiverEntity(caregiverDTO);
        Account account = caregiver.getAccount();
        accountRepository.save(account);
        caregiver = caregiverRepository.save(caregiver);
        return caregiver.getId();
    }

    public UUID updateCaregiver(UUID caregiverId, CaregiverDTO caregiverDTO) {
        Optional<Caregiver> foundCaregiver = caregiverRepository.findById(caregiverId);
        if (!foundCaregiver.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + caregiverId);
        }
        Caregiver caregiver = foundCaregiver.get();

        Optional<Account> foundAccount = accountRepository.findById(caregiver.getAccount().getId());
        Account account = foundAccount.get();
        account.setAccountType(caregiverDTO.getAccount().getAccountType());
        account.setPassword(caregiverDTO.getAccount().getPassword());
        account.setUserName(caregiverDTO.getAccount().getUserName());

        caregiver.setName(caregiverDTO.getName());
        caregiver.setBirthdate(caregiverDTO.getBirthdate());
        caregiver.setGender(caregiverDTO.getGender());
        caregiver.setAddress(caregiverDTO.getAddress());
        caregiver.setAccount(account);
        caregiver.setPatients(caregiverDTO.getPatients());
        accountRepository.save(caregiver.getAccount());
        caregiver = caregiverRepository.save(caregiver);
        return caregiver.getId();
    }

    public void deleteCaregiver(UUID caregiverId) {
        Caregiver caregiver = CaregiverBuilder.toCaregiverEntity(findCaregiverById(caregiverId));
        caregiverRepository.deleteById(caregiverId);
        accountRepository.delete(caregiver.getAccount());
    }

    public List<PatientDTO> findAllPatients(UUID caregiverId) {
        CaregiverDTO caregiverDTO = findCaregiverById(caregiverId);
        return caregiverDTO.getPatients().stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverByAccountId(UUID accountId) {
        Optional<Caregiver> foundCaregiver = caregiverRepository.findCaregiverByAccount_Id(accountId);
        if (!foundCaregiver.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + accountId);
        }
        return CaregiverBuilder.toCaregiverDTO(foundCaregiver.get());
    }
}
