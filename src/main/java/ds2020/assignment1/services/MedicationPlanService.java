package ds2020.assignment1.services;

import ds2020.assignment1.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ds2020.assignment1.dtos.MedicationPlanDTO;
import ds2020.assignment1.dtos.builders.MedicationPlanBuilder;
import ds2020.assignment1.entities.MedicationPlan;
import ds2020.assignment1.entities.Patient;
import ds2020.assignment1.repositories.MedicationPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MedicationPlanService {

    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public MedicationPlanDTO findMedicationPlanById(UUID medicationPlanId) {
        Optional<MedicationPlan> foundMedicationPlan = medicationPlanRepository.findById(medicationPlanId);
        if (!foundMedicationPlan.isPresent()) {
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + medicationPlanId);
        }
        return MedicationPlanBuilder.toMedicationPlanDTO(foundMedicationPlan.get());
    }


    public UUID updateMedicationPlan(Patient patient, MedicationPlanDTO medicationPlanDTO) {
        MedicationPlan plan = MedicationPlanBuilder.toMedicationPlanEntity(medicationPlanDTO);
        MedicationPlan currentPlan = patient.getMedicationPlan();

        currentPlan.setTreatmentPeriod(plan.getTreatmentPeriod());
        currentPlan.setMedications(plan.getMedications());
        currentPlan = medicationPlanRepository.save(currentPlan);
        return currentPlan.getId();
    }
}
