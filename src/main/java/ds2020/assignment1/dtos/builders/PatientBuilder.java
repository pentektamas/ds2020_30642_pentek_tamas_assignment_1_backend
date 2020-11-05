package ds2020.assignment1.dtos.builders;

import ds2020.assignment1.dtos.PatientDTO;
import ds2020.assignment1.entities.Patient;

public class PatientBuilder {

    private PatientBuilder() {

    }

    public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getName(), patient.getBirthdate(), patient.getGender(), patient.getAddress(), patient.getMedicalRecord(), patient.getMedicationPlan(), patient.getAccount());
    }

    public static Patient toPatientEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO.getId(), patientDTO.getName(), patientDTO.getBirthdate(), patientDTO.getGender(), patientDTO.getAddress(), patientDTO.getMedicalRecord(), patientDTO.getMedicationPlan(), patientDTO.getAccount());
    }
}
