package ds2020.assignment1.dtos.builders;

import ds2020.assignment1.dtos.CaregiverDTO;
import ds2020.assignment1.entities.Caregiver;

public class CaregiverBuilder {

    private CaregiverBuilder() {

    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(), caregiver.getName(), caregiver.getBirthdate(), caregiver.getGender(), caregiver.getAddress(), caregiver.getPatients(), caregiver.getAccount());
    }

    public static Caregiver toCaregiverEntity(CaregiverDTO caregiverDTO) {
        return new Caregiver(caregiverDTO.getId(), caregiverDTO.getName(), caregiverDTO.getBirthdate(), caregiverDTO.getGender(), caregiverDTO.getAddress(), caregiverDTO.getAccount(), caregiverDTO.getPatients());
    }
}
