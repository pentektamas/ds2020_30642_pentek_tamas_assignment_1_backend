package ds2020.assignment1.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Caregiver implements Serializable {

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

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    //Eager Loading is a design pattern in which data initialization occurs on the spot
    //In eager loading strategy, if we load the Caregiver, it will also load up all Patients associated with it
    @OneToMany(fetch = FetchType.EAGER)
    private List<Patient> patients = new ArrayList<>();

    public Caregiver() {
    }

    public Caregiver(UUID id, String name, LocalDate birthdate, String gender, String address, Account account, List<Patient> patients) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.account = account;
        this.patients = patients;
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
