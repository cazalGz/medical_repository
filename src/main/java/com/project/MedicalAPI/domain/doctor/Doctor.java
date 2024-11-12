package com.project.MedicalAPI.domain.doctor;

import com.project.MedicalAPI.domain.direction.Direction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String document;
    private Boolean active;
    private Specialty speciality;
    private Direction direction;

    public Doctor(DoctorRegistrationData doctorRegistrationData) {
        this.active = true;
        this.name = doctorRegistrationData.name();
        this.email = doctorRegistrationData.email();
        this.telephone = doctorRegistrationData.telephone();
        this.document = doctorRegistrationData.document();
        this.speciality = doctorRegistrationData.specialty();
        this.direction = new Direction(doctorRegistrationData.direction());
    }

    public void UpdateData(DoctorUpdateData doctorUpdateData) {
        if (doctorUpdateData.name() != null) {
            this.name = doctorUpdateData.name();
        }
        if (doctorUpdateData.document() != null) {
            this.document = doctorUpdateData.document();
        }
        if (doctorUpdateData.direction() != null) {
            this.direction = direction.actualizarDatos(doctorUpdateData.direction());
        }
    }

    public void deactivateDoctor() {
        this.active = false;
    }
}
