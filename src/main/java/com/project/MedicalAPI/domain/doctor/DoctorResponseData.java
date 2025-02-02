package com.project.MedicalAPI.domain.doctor;

import com.project.MedicalAPI.domain.direction.DirectionData;

public record DoctorResponseData(Long id, String name, String email, String telephone, String document, String speciality,
                                 DirectionData direction) {
}
