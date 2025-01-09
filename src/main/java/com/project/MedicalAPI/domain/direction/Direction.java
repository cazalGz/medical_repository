package com.project.MedicalAPI.domain.direction;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;

    public Direction(DirectionData direction) {
        this.street = direction.street();
        this.number = direction.number();
        this.district = direction.district();
        this.complement = direction.complement();
        this.city = direction.city();
    }

    public Direction updateData(DirectionData direction) {
        this.street = direction.street();
        this.number = direction.number();
        this.district = direction.district();
        this.complement = direction.complement();
        this.city = direction.city();
        return this;
    }
}
