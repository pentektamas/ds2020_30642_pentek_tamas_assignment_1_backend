package ds2020.assignment1.controllers;

import ds2020.assignment1.dtos.MedicationDTO;
import ds2020.assignment1.services.MedicationService;
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
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        for (MedicationDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(MedicationController.class)
                    .getMedication(dto.getId())).withRel("medicationDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        UUID medicationId = medicationService.insertMedication(medicationDTO);
        return new ResponseEntity<>(medicationId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updateMedication(@PathVariable("id") UUID medicationId, @Valid @RequestBody MedicationDTO medicationDTO) {
        medicationService.updateMedication(medicationId, medicationDTO);
        return new ResponseEntity<>(medicationId, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteMedication(@PathVariable("id") UUID medicationId) {
        medicationService.deletePatient(medicationId);
        return new ResponseEntity<>(medicationId, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationId) {
        MedicationDTO dto = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
