package com.rodri.transporte.dto;

import com.rodri.transporte.model.EstadoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoVehiculoResponse {
    private UUID id;
    private EstadoVehiculo estado;
}
