package ds2020.assignment1.dtos;

import ds2020.assignment1.entities.Medication;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MedicationPlanDTO {

    private UUID id;
    @NotNull
    private Integer treatmentPeriod;
    private List<Medication> medications;

    public MedicationPlanDTO() {

    }

    public MedicationPlanDTO(UUID id, @NotNull Integer treatmentPeriod, List<Medication> medications) {
        this.id = id;
        this.treatmentPeriod = treatmentPeriod;
        this.medications = medications;
    }

    public MedicationPlanDTO(@NotNull Integer treatmentPeriod, List<Medication> medications) {
        this.treatmentPeriod = treatmentPeriod;
        this.medications = medications;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTreatmentPeriod() {
        return treatmentPeriod;
    }

    public void setTreatmentPeriod(Integer treatmentPeriod) {
        this.treatmentPeriod = treatmentPeriod;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
