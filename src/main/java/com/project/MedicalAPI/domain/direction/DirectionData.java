package com.project.MedicalAPI.domain.direction;

import jakarta.validation.constraints.NotBlank;

public record DirectionData(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String number,
        @NotBlank
        String complement
) {
}
