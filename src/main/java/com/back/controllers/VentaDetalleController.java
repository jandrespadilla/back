package com.back.controllers;

import com.back.models.Producto;
import com.back.models.VentaDetalle;
import com.back.services.VentaDetalleService;
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
@Tag(name = "Gestion de items de las facturas", description = "Endpoints para Gestionar ABMC de items de las facturas")
@RestController
@RequestMapping("/api/ventadetalle")
public class VentaDetalleController {
    @Autowired
    private VentaDetalleService ventaDetalleService;
    @Operation(summary = "Obtener la Lista de todos los Items facturados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Items obtenida correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaDetalle.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno al tratar de obtener la lista de Items", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<VentaDetalle>> getAllVentaDetalle(){
        try {
            List<VentaDetalle> ventaDetalles = ventaDetalleService.getAllVentaDetalle();
            return ResponseEntity.ok(ventaDetalles);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaDetalle.class)) }),
            @ApiResponse(responseCode = "404", description = "Item no encontrado", content = @Content) })
    @Operation(summary = "Obtener un Item por su ID")
    @GetMapping("/{id}")
    public ResponseEntity <VentaDetalle> getVentaDetalleById(@PathVariable Long id){
        try {
            VentaDetalle ventaDetalle= ventaDetalleService.findById(id);
            return ResponseEntity.ok(ventaDetalle);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Agregar un nuevo Item a la factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item agregado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaDetalle.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping
    public ResponseEntity <VentaDetalle> createVentaDetalle(@RequestBody VentaDetalle ventaDetalle){
        try {
            VentaDetalle createdVentaDetalle1 = ventaDetalleService.createVentaDetalle(ventaDetalle);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVentaDetalle1);
        }catch (IllegalArgumentException E){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Editar un Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item editado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VentaDetalle.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity <VentaDetalle> updateVentaDetalle(@PathVariable Long id, @RequestBody VentaDetalle ventaDetalleDetails){
        try {
            VentaDetalle updatedVentaDetalle = ventaDetalleService.updateVentaDetall(id,ventaDetalleDetails);
            return ResponseEntity.ok(updatedVentaDetalle);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Eliminar una Item ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item eliminado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item no encontrado", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteVentaDetalle(@PathVariable long id){
        try {
            if (ventaDetalleService.existById(id)){
                ventaDetalleService.deleteVentaDetalle(id);
                return ResponseEntity.noContent().build();
            }else {
                return  ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
