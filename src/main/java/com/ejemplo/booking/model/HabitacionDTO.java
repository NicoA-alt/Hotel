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
    private String tipo;
    private double precio;
    private int capacidad;
}
