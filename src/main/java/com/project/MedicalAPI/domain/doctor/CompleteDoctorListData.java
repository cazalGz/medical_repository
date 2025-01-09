package com.project.MedicalAPI.domain.doctor;

import com.project.MedicalAPI.domain.direction.DirectionData;

public record CompleteDoctorListData(Long id, String name, String email, String telephone, String document, String speciality, DirectionData direction) {
    public CompleteDoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getTelephone(), doctor.getDocument(),doctor.getSpeciality().toString(),
                new DirectionData(doctor.getDirection().getStreet(), doctor.getDirection().getDistrict(),
                        doctor.getDirection().getCity(), doctor.getDirection().getNumber(),
                        doctor.getDirection().getComplement()));
    }
}
