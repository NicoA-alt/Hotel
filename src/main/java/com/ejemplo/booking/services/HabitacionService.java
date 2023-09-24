package com.ejemplo.booking.services;

import com.ejemplo.booking.model.Habitacion;
import com.ejemplo.booking.model.Reserva;
import com.ejemplo.booking.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;
    public HabitacionService(HabitacionRepository habitacionRepository){
        this.habitacionRepository = habitacionRepository;
    }
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findAll();
    }

    public ResponseEntity agregarHabitacion(Habitacion habitacion) {
        try {
            habitacionRepository.save(habitacion);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity obtenerHabitacionPorId(Integer id) {
        try {
            Habitacion habitacion = habitacionRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Habitacion no encontrada"));
            return ResponseEntity.ok(habitacion);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    public ResponseEntity actualizarHabitacion(Integer id, Habitacion habitacion) {
        try{
            Habitacion habitacionActual = habitacionRepository.findById(id).get();
            habitacionActual.setTipo(habitacion.getTipo());
            habitacionActual.setDisponible(habitacion.isDisponible());
            habitacionActual.setPrecio(habitacion.getPrecio());
            habitacionActual.setCapacidad(habitacion.getCapacidad());
            habitacionRepository.save(habitacionActual);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity borrarHabitacion(Integer id) {
        try {
            habitacionRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity obtenerReservasDeHabitacion(Integer id) {
        try {
            Habitacion habitacion = habitacionRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Habitacion no encontrada"));
            return ResponseEntity.ok(habitacion.getReservas());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
