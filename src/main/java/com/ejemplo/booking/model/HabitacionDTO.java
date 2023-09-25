package com.ejemplo.booking.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionDTO {
    @NotNull
    private String tipo;
    @NotNull
    private double precio;
    @NotNull
    private int capacidad;

}
