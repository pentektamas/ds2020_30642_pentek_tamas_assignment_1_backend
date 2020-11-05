package ds2020.assignment1.dtos;

import ds2020.assignment1.entities.Account;
import ds2020.assignment1.entities.AccountType;
import ds2020.assignment1.entities.MedicationPlan;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class PatientDTO extends RepresentationModel<PatientDTO> {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    @NotNull
    private String medicalRecord;
    @NotNull
    private MedicationPlan medicationPlan;
    @NotNull
    private Account account;

    public PatientDTO() {

    }

    public PatientDTO(UUID id, @NotNull String name, @NotNull LocalDate birthdate, @NotNull String gender, @NotNull String address, @NotNull String medicalRecord, @NotNull MedicationPlan medicationPlan, @NotNull Account account) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.medicationPlan = medicationPlan;
        this.account = account;
        this.account.setAccountType(AccountType.PATIENT);
    }

    public PatientDTO(@NotNull String name, @NotNull LocalDate birthdate, @NotNull String gender, @NotNull String address, @NotNull String medicalRecord, @NotNull Account account) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.medicationPlan = new MedicationPlan();
        this.account = account;
        this.account.setAccountType(AccountType.PATIENT);
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
