package ds2020.assignment1.dtos.builders;

import ds2020.assignment1.dtos.DoctorDTO;
import ds2020.assignment1.entities.Doctor;


public class DoctorBuilder {

    private DoctorBuilder() {

    }

    public static DoctorDTO toDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId(), doctor.getAccount());
    }

    public static Doctor toDoctorEntity(Doctor doctorDTO) {
        return new Doctor(doctorDTO.getId(), doctorDTO.getAccount());
    }
}
