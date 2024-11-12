package com.project.MedicalAPI.domain.doctor;

import com.project.MedicalAPI.domain.direction.DirectionData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegistrationData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telephone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String document,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        DirectionData direction) {
}
