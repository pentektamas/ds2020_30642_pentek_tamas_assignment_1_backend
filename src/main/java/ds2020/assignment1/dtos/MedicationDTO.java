package ds2020.assignment1.dtos;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class MedicationDTO extends RepresentationModel<MedicationDTO> {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String sideEffects;
    @NotNull
    private String dosage;

    public MedicationDTO() {

    }

    public MedicationDTO(UUID id, @NotNull String name, @NotNull String sideEffects, @NotNull String dosage) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public MedicationDTO(@NotNull String name, @NotNull String sideEffects, @NotNull String dosage) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
