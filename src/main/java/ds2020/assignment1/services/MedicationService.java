package ds2020.assignment1.services;

import ds2020.assignment1.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ds2020.assignment1.dtos.MedicationDTO;
import ds2020.assignment1.dtos.builders.MedicationBuilder;
import ds2020.assignment1.entities.Medication;
import ds2020.assignment1.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationDTO> findMedications() {
        List<Medication> medicationList = medicationRepository.findAll();
        return medicationList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(UUID medicationId) {
        Optional<Medication> foundMedication = medicationRepository.findById(medicationId);
        if (!foundMedication.isPresent()) {
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + medicationId);
        }
        return MedicationBuilder.toMedicationDTO(foundMedication.get());
    }

    public UUID insertMedication(MedicationDTO medicationDTO) {
        Medication medication = MedicationBuilder.toMedicationEntity(medicationDTO);
        medication = medicationRepository.save(medication);
        return medication.getId();
    }

    public UUID updateMedication(UUID medicationId, MedicationDTO medicationDTO) {
        Optional<Medication> foundMedication = medicationRepository.findById(medicationId);
        if (!foundMedication.isPresent()) {
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + medicationId);
        }
        Medication medication = foundMedication.get();
        medication.setName(medicationDTO.getName());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDosage(medicationDTO.getDosage());
        medication = medicationRepository.save(medication);
        return medication.getId();
    }

    public void deletePatient(UUID medicationId) {
        medicationRepository.deleteById(medicationId);
    }
}
