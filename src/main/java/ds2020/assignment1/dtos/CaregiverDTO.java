package ds2020.assignment1.dtos;

import ds2020.assignment1.entities.Account;
import ds2020.assignment1.entities.Patient;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CaregiverDTO extends RepresentationModel<CaregiverDTO> {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    private List<Patient> patients;
    @NotNull
    private Account account;

    public CaregiverDTO() {

    }

    public CaregiverDTO(UUID id, @NotNull String name, @NotNull LocalDate birthdate, @NotNull String gender, @NotNull String address, @NotNull List<Patient> patients, @NotNull Account account) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.patients = patients;
        this.account = account;
    }

    public CaregiverDTO(@NotNull String name, @NotNull LocalDate birthdate, @NotNull String gender, @NotNull String address, @NotNull List<Patient> patients, @NotNull Account account) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.patients = patients;
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
