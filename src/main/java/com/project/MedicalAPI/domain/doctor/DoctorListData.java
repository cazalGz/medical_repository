package com.project.MedicalAPI.domain.doctor;

public record DoctorListData(Long id, String nombre, String speciality, String document, String email) {
    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getSpeciality().toString(), doctor.getDocument(), doctor.getEmail());
    }
}
