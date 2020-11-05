package ds2020.assignment1.controllers;

import ds2020.assignment1.dtos.CaregiverDTO;
import ds2020.assignment1.dtos.PatientDTO;
import ds2020.assignment1.services.CaregiverService;
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
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        for (CaregiverDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(CaregiverController.class)
                    .getCaregiver(dto.getId())).withRel("caregiverDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverId = caregiverService.insertCaregiver(caregiverDTO);
        return new ResponseEntity<>(caregiverId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updateCaregiver(@PathVariable("id") UUID caregiverId, @Valid @RequestBody CaregiverDTO caregiverDTO) {
        caregiverService.updateCaregiver(caregiverId, caregiverDTO);
        return new ResponseEntity<>(caregiverId, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteCaregiver(@PathVariable("id") UUID caregiverId) {
        caregiverService.deleteCaregiver(caregiverId);
        return new ResponseEntity<>(caregiverId, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/patients/{id}")
    public ResponseEntity<List<PatientDTO>> getCaregiverPatients(@PathVariable("id") UUID patientId) {
        List<PatientDTO> patients = caregiverService.findAllPatients(patientId);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "/account/{accountid}")
    public ResponseEntity<CaregiverDTO> getCaregiverByAccountID(@PathVariable("accountid") UUID accountId) {
        CaregiverDTO dto = caregiverService.findCaregiverByAccountId(accountId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
