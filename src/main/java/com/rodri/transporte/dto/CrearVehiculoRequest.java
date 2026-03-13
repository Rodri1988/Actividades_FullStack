package com.rodri.transporte.dto;

import com.rodri.transporte.model.EstadoVehiculo;
import com.rodri.transporte.model.TipoTransporte;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearVehiculoRequest {

    @NotNull(message = "El tipo de transporte es obligatorio")
    @Schema(description = "Tipo de transporte", example = "AUTO")
    private TipoTransporte tipoTransporte;

    @NotNull(message = "El estado es obligatorio")
    @Schema(description = "Estado inicial del vehiculo", example = "DISPONIBLE")
    private EstadoVehiculo estado;

    @Schema(example = "AB123CD")
    private String patente;
    @Schema(example = "Rojo")
    private String color;
    @Schema(example = "Toyota")
    private String marca;
    @Schema(example = "Corolla")
    private String modelo;

    @PositiveOrZero(message = "El año debe ser mayor o igual a 0")
    @Schema(example = "2022")
    private Integer anio;

    @PositiveOrZero(message = "El kilometraje debe ser mayor o igual a 0")
    @Schema(example = "15000")
    private Double kilometraje;
}
