package com.rodri.transporte.service;

import com.rodri.transporte.dto.CrearVehiculoRequest;
import com.rodri.transporte.exception.VehiculoNoEncontradoException;
import com.rodri.transporte.model.EstadoVehiculo;
import com.rodri.transporte.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VehiculoService {

    private final Map<UUID, Vehiculo> almacenamiento = new ConcurrentHashMap<>();

    public Vehiculo crearVehiculo(CrearVehiculoRequest request) {
        Vehiculo vehiculo = Vehiculo.builder()
                .id(UUID.randomUUID())
                .tipoTransporte(request.getTipoTransporte())
                .estado(request.getEstado())
                .patente(request.getPatente())
                .color(request.getColor())
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .anio(request.getAnio())
                .kilometraje(request.getKilometraje())
                .creadoEn(LocalDateTime.now())
                .build();

        almacenamiento.put(vehiculo.getId(), vehiculo);
        return vehiculo;
    }

    public Vehiculo obtenerVehiculoPorId(UUID id) {
        Vehiculo vehiculo = almacenamiento.get(id);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("No existe un vehiculo con id: " + id);
        }
        return vehiculo;
    }

    public Vehiculo actualizarEstado(UUID id, EstadoVehiculo nuevoEstado) {
        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        vehiculo.setEstado(nuevoEstado);
        almacenamiento.put(id, vehiculo);
        return vehiculo;
    }
}
