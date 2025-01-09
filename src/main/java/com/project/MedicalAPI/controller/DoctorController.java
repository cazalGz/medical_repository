package com.project.MedicalAPI.controller;

import com.project.MedicalAPI.domain.direction.DirectionData;
import com.project.MedicalAPI.domain.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorListData>> ActiveDoctorList() {
        List<DoctorListData> medicos = doctorRepository.findByActiveTrue()
                .stream()
                .map(DoctorListData::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/all_doctors")
    public ResponseEntity<List<CompleteDoctorListData>> DoctorList() {
        List<CompleteDoctorListData> doctors = doctorRepository.findAll()
                .stream()
                .map(CompleteDoctorListData::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(doctors);
    }



    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorResponseData> getDoctorData(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        var doctorData = new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getTelephone(), doctor.getDocument(), doctor.getSpeciality().toString(),
                new DirectionData(doctor.getDirection().getStreet(), doctor.getDirection().getDistrict(),
                        doctor.getDirection().getCity(), doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement()));
        return ResponseEntity.ok(doctorData);
    }

    @PostMapping("/post_doctor")
    public ResponseEntity<DoctorResponseData> registerDoctor(@RequestBody @Valid DoctorRegistrationData doctorRegistrationData,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = doctorRepository.save(new Doctor(doctorRegistrationData));
        DoctorResponseData doctorResponseData = new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(),
                doctor.getTelephone(), doctor.getDocument(),doctor.getSpeciality().toString(),
                new DirectionData(doctor.getDirection().getStreet(), doctor.getDirection().getDistrict(),
                        doctor.getDirection().getCity(), doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement()));

        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(doctorResponseData);

    }

    @PutMapping("/doctor")
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateData doctorUpdateData) {
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.UpdateData(doctorUpdateData);
        return ResponseEntity.ok(new DoctorResponseData(doctor.getId(), doctor.getName(), doctor.getEmail(),
                doctor.getTelephone(), doctor.getDocument(),doctor.getSpeciality().toString(),
                new DirectionData(doctor.getDirection().getStreet(), doctor.getDirection().getDistrict(),
                        doctor.getDirection().getCity(), doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement())));
    }

    // DELETE LOGIC
    @DeleteMapping("/doctor/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deactivateDoctor();
        return ResponseEntity.noContent().build();
    }

}
