package com.rodri.transporte.dto;

import com.rodri.transporte.model.EstadoVehiculo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarEstadoVehiculoRequest {

    @NotNull(message = "El estado es obligatorio")
    @Schema(description = "Nuevo estado del vehiculo", example = "OCUPADO")
    private EstadoVehiculo estado;
}
