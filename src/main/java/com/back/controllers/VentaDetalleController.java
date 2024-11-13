package com.back.controllers;

import com.back.models.VentaDetalle;
import com.back.services.VentaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventadetalle")
public class VentaDetalleController {
    @Autowired
    private VentaDetalleService ventaDetalleService;
    @GetMapping
    public ResponseEntity<List<VentaDetalle>> getAllVentaDetalle(){
        try {
            List<VentaDetalle> ventaDetalles = ventaDetalleService.getAllVentaDetalle();
            return ResponseEntity.ok(ventaDetalles);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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
    @PostMapping
    public ResponseEntity <VentaDetalle> createVentaDetalle(@RequestBody VentaDetalle ventaDetalle){
        try {
            VentaDetalle createdVentaDetalle1 = ventaDetalleService.createVentaDetalle(ventaDetalle);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVentaDetalle1);
        }catch (IllegalArgumentException E){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity <VentaDetalle> updateVentaDetalle(@PathVariable Long id, @RequestBody VentaDetalle ventaDetalleDetails){
        try {
            VentaDetalle updatedVentaDetalle = ventaDetalleService.updateVentaDetall(id,ventaDetalleDetails);
            return ResponseEntity.ok(updatedVentaDetalle);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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
