package com.back.controllers;

import com.back.models.VentaCabecera;
import com.back.services.VentaCabeceraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Cabecera de las facturas realizadas", description = "Endpoints para Gestionar ABMC las facturas")
@RestController
@RequestMapping("/api/ventacabecera")
public class VentaCabeceraController {
    @Autowired
    private VentaCabeceraService ventaCabeceraService;
    @Operation(summary = "Obtener la Lista de todas las facturas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaCabecera.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno al tratar de obtener la lista de facturas", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<VentaCabecera>> getAllVentaCabecera(){
        try {
            List<VentaCabecera> ventaCabeceras=ventaCabeceraService.getAllVentaCabecera();
            return ResponseEntity.ok(ventaCabeceras);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaCabecera.class)) }),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content) })
    @Operation(summary = "Obtener una factura por su ID")
    @GetMapping("/{id}")
    public ResponseEntity <VentaCabecera> getVentaCabecera(@PathVariable Long id){
        try {
            VentaCabecera ventaCabecera=ventaCabeceraService.findById(id);
            return ResponseEntity.ok(ventaCabecera);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Agregar una nueva Factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura agregada correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaCabecera.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping
    public ResponseEntity <VentaCabecera> createVentaCabecera(@RequestBody VentaCabecera ventaCabecera){
        try {
            VentaCabecera createdVentaCabecera = ventaCabeceraService.createVentaCabecera(ventaCabecera);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVentaCabecera);
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Editar una Factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura editada correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaCabecera.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PutMapping("/{id}")
    public  ResponseEntity <VentaCabecera> updateVentaCabecera(@PathVariable Long id, @RequestBody VentaCabecera ventaCabeceraDetails){
        try {
            VentaCabecera updatedVentaCabecera = ventaCabeceraService.updateVentaCabecera(id,ventaCabeceraDetails);
            return ResponseEntity.ok(updatedVentaCabecera);
        }catch (IllegalArgumentException e){
            return  ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Eliminar una Factura ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Factura eliminada correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentaCabecera(@PathVariable Long id){
        try {
            if (ventaCabeceraService.existById(id)){
                ventaCabeceraService.deleteVentaCabecera(id);
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
