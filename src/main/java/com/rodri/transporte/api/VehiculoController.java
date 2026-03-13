package com.rodri.transporte.api;

import com.rodri.transporte.dto.ActualizarEstadoVehiculoRequest;
import com.rodri.transporte.dto.CrearVehiculoRequest;
import com.rodri.transporte.dto.EstadoVehiculoResponse;
import com.rodri.transporte.dto.VehiculoResponse;
import com.rodri.transporte.model.Vehiculo;
import com.rodri.transporte.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@Tag(name = "Vehiculos", description = "API para registrar transportes y consultar estado")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @PostMapping
    @Operation(
            summary = "Registrar vehiculo",
            description = "Crea un registro de auto, bicicleta, scooter u otro transporte",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    examples = {
                        @ExampleObject(
                            name = "Auto",
                            summary = "Alta de un auto disponible",
                            value = "{\n  \"tipoTransporte\": \"AUTO\",\n  \"estado\": \"DISPONIBLE\",\n  \"patente\": \"AB123CD\",\n  \"color\": \"Rojo\",\n  \"marca\": \"Toyota\",\n  \"modelo\": \"Corolla\",\n  \"anio\": 2022,\n  \"kilometraje\": 15000\n}"
                        ),
                        @ExampleObject(
                            name = "Bicicleta",
                            summary = "Alta de bicicleta en uso",
                            value = "{\n  \"tipoTransporte\": \"BICICLETA\",\n  \"estado\": \"EN_USO\",\n  \"color\": \"Negro\",\n  \"marca\": \"Venzo\",\n  \"modelo\": \"X1\",\n  \"anio\": 2024,\n  \"kilometraje\": 350\n}"
                        )
                    }
                )
            )
    )
    public ResponseEntity<VehiculoResponse> crearVehiculo(@Valid @RequestBody CrearVehiculoRequest request) {
        Vehiculo creado = vehiculoService.crearVehiculo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toVehiculoResponse(creado));
    }

    @GetMapping("/{id}/estado")
    @Operation(summary = "Consultar estado", description = "Obtiene el estado del vehiculo por id")
    public ResponseEntity<EstadoVehiculoResponse> obtenerEstado(@PathVariable UUID id) {
        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        EstadoVehiculoResponse response = EstadoVehiculoResponse.builder()
                .id(vehiculo.getId())
                .estado(vehiculo.getEstado())
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/estado")
    @Operation(
            summary = "Actualizar estado",
            description = "Actualiza el estado del vehiculo por id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    examples = {
                        @ExampleObject(
                            name = "Ocupar vehiculo",
                            value = "{\n  \"estado\": \"OCUPADO\"\n}"
                        ),
                        @ExampleObject(
                            name = "Marcar disponible",
                            value = "{\n  \"estado\": \"DISPONIBLE\"\n}"
                        )
                    }
                )
            )
    )
    public ResponseEntity<EstadoVehiculoResponse> actualizarEstado(
            @PathVariable UUID id,
            @Valid @RequestBody ActualizarEstadoVehiculoRequest request
        ) {
        Vehiculo vehiculoActualizado = vehiculoService.actualizarEstado(id, request.getEstado());
        EstadoVehiculoResponse response = EstadoVehiculoResponse.builder()
                .id(vehiculoActualizado.getId())
                .estado(vehiculoActualizado.getEstado())
                .build();

        return ResponseEntity.ok(response);
    }

    private VehiculoResponse toVehiculoResponse(Vehiculo vehiculo) {
        return VehiculoResponse.builder()
                .id(vehiculo.getId())
                .tipoTransporte(vehiculo.getTipoTransporte())
                .estado(vehiculo.getEstado())
                .patente(vehiculo.getPatente())
                .color(vehiculo.getColor())
                .marca(vehiculo.getMarca())
                .modelo(vehiculo.getModelo())
                .anio(vehiculo.getAnio())
                .kilometraje(vehiculo.getKilometraje())
                .creadoEn(vehiculo.getCreadoEn())
                .build();
    }
}
