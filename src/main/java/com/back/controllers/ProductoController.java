package com.back.controllers;



import java.util.List;

import com.back.models.Producto;
import com.back.repository.ProductosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductosRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            Producto alumno = productoRepository.findById(id).get();
            return ResponseEntity.ok(alumno);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

}