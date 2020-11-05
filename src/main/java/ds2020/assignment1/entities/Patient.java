package ds2020.assignment1.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "medicalRecord", nullable = false)
    private String medicalRecord;

    @OneToOne(fetch = FetchType.EAGER)
    private MedicationPlan medicationPlan;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    public Patient() {

    }

    public Patient(UUID id, String name, LocalDate birthdate, String gender, String address, String medicalRecord, MedicationPlan medicationPlan, Account account) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.medicationPlan = medicationPlan;
        this.account = account;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public MedicationPlan getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(MedicationPlan medicationPlan) {
        this.medicationPlan = medicationPlan;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
