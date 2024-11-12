package com.project.MedicalAPI.domain.doctor;

public record DoctorListData(Long id, String nombre, String especialidad, String documento, String email) {
    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getSpeciality().toString(), doctor.getDocument(), doctor.getEmail());
    }
}
