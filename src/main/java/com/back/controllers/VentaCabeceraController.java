package com.back.controllers;

import com.back.models.VentaCabecera;
import com.back.services.VentaCabeceraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventacabecera")
public class VentaCabeceraController {
    @Autowired
    private VentaCabeceraService ventaCabeceraService;
    @GetMapping
    public ResponseEntity<List<VentaCabecera>> getAllVentaCabecera(){
        try {
            List<VentaCabecera> ventaCabeceras=ventaCabeceraService.getAllVentaCabecera();
            return ResponseEntity.ok(ventaCabeceras);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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
