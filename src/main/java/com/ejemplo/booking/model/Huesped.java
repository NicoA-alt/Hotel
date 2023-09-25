package com.ejemplo.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "huespedes")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @Pattern(regexp="[A-Za-záéíóúÁÉÍÓÚñÑ\s]+")
    @NotNull
    private String nombre;
    @Size(max = 50)
    @Pattern(regexp="[A-Za-záéíóúÁÉÍÓÚñÑ\\s]+")
    @NotNull
    private String apellido;
    @Pattern(regexp="^[FM]\\d{1}\\.\\d{3}\\.\\d{3}|\\d{2}\\.\\d{3}\\.\\d{3}")
    @NotNull
    private String dni;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String telefono;

    // Relación con Reservas
    @ManyToMany(mappedBy = "huespedes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reserva> reservas;

}





