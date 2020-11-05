package ds2020.assignment1.controllers;

import ds2020.assignment1.dtos.PatientDTO;
import ds2020.assignment1.services.PatientService;
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
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        for (PatientDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("patientDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertPatient(@Valid @RequestBody PatientDTO patientDTO) {
        UUID patientId = patientService.insertPatient(patientDTO);
        return new ResponseEntity<>(patientId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updatePatient(@PathVariable("id") UUID patientId, @Valid @RequestBody PatientDTO patientDTO) {
        patientService.updatePatient(patientId, patientDTO);
        return new ResponseEntity<>(patientId, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deletePatient(@PathVariable("id") UUID patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(patientId, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/account/{accountid}")
    public ResponseEntity<PatientDTO> getPatientByAccountID(@PathVariable("accountid") UUID accountId) {
        PatientDTO dto = patientService.findPatientByAccountId(accountId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
