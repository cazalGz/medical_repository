package com.project.MedicalAPI.domain.doctor;

import com.project.MedicalAPI.domain.direction.DirectionData;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(@NotNull Long id, String name, String document, DirectionData direction) {
}
