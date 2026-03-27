package com.rodri.transporte.dto;

import com.rodri.transporte.model.EstadoVehiculo;
import com.rodri.transporte.model.TipoTransporte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponse {
    private UUID id;
    private TipoTransporte tipoTransporte;
    private EstadoVehiculo estado;
    private String patente;
    private String color;
    private String marca;
    private String modelo;
    private Integer anio;
    private Double kilometraje;
    private LocalDateTime creadoEn;
}
