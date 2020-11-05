package ds2020.assignment1.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class MedicationPlan implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "treatmentPeriod", nullable = false)
    private Integer treatmentPeriod;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Medication> medications = new ArrayList<>();

    public MedicationPlan() {

    }

    public MedicationPlan(UUID id, Integer treatmentPeriod, List<Medication> medications) {
        this.id = id;
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
