package ds2020.assignment1.dtos.builders;

import ds2020.assignment1.dtos.MedicationPlanDTO;
import ds2020.assignment1.entities.MedicationPlan;

public class MedicationPlanBuilder {

    private MedicationPlanBuilder() {

    }

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(), medicationPlan.getTreatmentPeriod(), medicationPlan.getMedications());
    }

    public static MedicationPlan toMedicationPlanEntity(MedicationPlanDTO medicationPlanDTO) {
        return new MedicationPlan(medicationPlanDTO.getId(), medicationPlanDTO.getTreatmentPeriod(), medicationPlanDTO.getMedications());
    }
}
