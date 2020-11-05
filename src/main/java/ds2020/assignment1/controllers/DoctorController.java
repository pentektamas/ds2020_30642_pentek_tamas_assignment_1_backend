package ds2020.assignment1.controllers;

import ds2020.assignment1.dtos.*;
import ds2020.assignment1.dtos.builders.MedicationPlanBuilder;
import ds2020.assignment1.dtos.builders.PatientBuilder;
import ds2020.assignment1.entities.AccountType;
import ds2020.assignment1.entities.MedicationPlan;
import ds2020.assignment1.entities.Patient;
import ds2020.assignment1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final CaregiverService caregiverService;
    private final MedicationService medicationService;
    private final MedicationPlanService medicationPlanService;

    @Autowired
    public DoctorController(PatientService patientService, CaregiverService caregiverService, MedicationService medicationService, MedicationPlanService medicationPlanService, DoctorService doctorService) {
        this.patientService = patientService;
        this.caregiverService = caregiverService;
        this.medicationService = medicationService;
        this.medicationPlanService = medicationPlanService;
        this.doctorService = doctorService;
    }

    @GetMapping(value = "/patients")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        for (PatientDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("patientDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/patient/insert")
    public ResponseEntity<UUID> insertPatient(@Valid @RequestBody PatientDTO patientDTO) {
        patientDTO.getAccount().setAccountType(AccountType.PATIENT);
        UUID patientId = patientService.insertPatient(patientDTO);
        return new ResponseEntity<>(patientId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/patient/update/{id}")
    public ResponseEntity<UUID> updatePatient(@PathVariable("id") UUID patientId, @Valid @RequestBody PatientDTO patientDTO) {
        patientService.updatePatient(patientId, patientDTO);
        return new ResponseEntity<>(patientId, HttpStatus.OK);
    }

    @PostMapping(value = "/patient/delete/{id}")
    public ResponseEntity<UUID> deletePatient(@PathVariable("id") UUID patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(patientId, HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/caregivers")
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        for (CaregiverDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(CaregiverController.class)
                    .getCaregiver(dto.getId())).withRel("caregiverDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/caregiver/insert")
    public ResponseEntity<UUID> insertCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        caregiverDTO.getAccount().setAccountType(AccountType.CAREGIVER);
        UUID caregiverId = caregiverService.insertCaregiver(caregiverDTO);
        return new ResponseEntity<>(caregiverId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/caregiver/update/{id}")
    public ResponseEntity<UUID> updateCaregiver(@PathVariable("id") UUID caregiverId, @Valid @RequestBody CaregiverDTO caregiverDTO) {
        caregiverService.updateCaregiver(caregiverId, caregiverDTO);
        return new ResponseEntity<>(caregiverId, HttpStatus.OK);
    }

    @PostMapping(value = "/caregiver/delete/{id}")
    public ResponseEntity<UUID> deleteCaregiver(@PathVariable("id") UUID caregiverId) {
        caregiverService.deleteCaregiver(caregiverId);
        return new ResponseEntity<>(caregiverId, HttpStatus.OK);
    }

    @GetMapping(value = "/caregiver/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/medications")
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        for (MedicationDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(MedicationController.class)
                    .getMedication(dto.getId())).withRel("medicationDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/medication/insert")
    public ResponseEntity<UUID> insertMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        UUID medicationId = medicationService.insertMedication(medicationDTO);
        return new ResponseEntity<>(medicationId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/medication/update/{id}")
    public ResponseEntity<UUID> updateMedication(@PathVariable("id") UUID medicationId, @Valid @RequestBody MedicationDTO medicationDTO) {
        medicationService.updateMedication(medicationId, medicationDTO);
        return new ResponseEntity<>(medicationId, HttpStatus.OK);
    }

    @PostMapping(value = "/medication/delete/{id}")
    public ResponseEntity<UUID> deleteMedication(@PathVariable("id") UUID medicationId) {
        medicationService.deletePatient(medicationId);
        return new ResponseEntity<>(medicationId, HttpStatus.OK);
    }

    @GetMapping(value = "/medication/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationId) {
        MedicationDTO dto = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/medicationplan/{id}")
    public ResponseEntity<UUID> createMedicationPlan(@PathVariable("id") UUID patientID, @Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        Patient patient = PatientBuilder.toPatientEntity(patientService.findPatientById(patientID));
        MedicationPlan plan = MedicationPlanBuilder.toMedicationPlanEntity(medicationPlanDTO);
        UUID medicationPlanID = patient.getMedicationPlan().getId();
        medicationPlanService.updateMedicationPlan(patient, medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.OK);
    }

    @GetMapping(value = "/account/{accountid}")
    public ResponseEntity<DoctorDTO> getDoctorByAccountID(@PathVariable("accountid") UUID accountId) {
        DoctorDTO dto = doctorService.findDoctorByAccountId(accountId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
