package com.back.controllers;

import com.back.dtos.AignarCategoriaDTO;
import com.back.models.Producto;
import com.back.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos(){
        try {
            List<Producto> productos = productoService.getAllProductos();
            return ResponseEntity.ok(productos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity <Producto> getProductoById(@PathVariable Long id){
        try {
            Producto producto = productoService.findByID(id);
            return ResponseEntity.ok(producto);
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
        try {
            Producto createdProducto = productoService.createProducto(producto);
            return ResponseEntity.ok(createdProducto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable long id, @RequestBody Producto productoDetaill){
        try {
            Producto updatedProducto = productoService.updateProducto(id,productoDetaill);
            return ResponseEntity.ok(updatedProducto);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        try {
            if (productoService.existByID(id)){
                productoService.deleteProducto(id);
                return ResponseEntity.noContent().build();
            }else{
                return  ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/asignarcategoria")
    public ResponseEntity<Producto> asignarCategoria(@RequestBody AignarCategoriaDTO asignarCategoria){
        try {
            Producto productoActualizado = productoService.asignarCategoriaProducto(asignarCategoria);
            return ResponseEntity.ok(productoActualizado);
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

