package com.back.controllers;

import com.back.models.Categoria;
import com.back.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
        @Autowired
        private CategoriaService categoriaService;
        @GetMapping
        public ResponseEntity<List<Categoria>> getAllCategorias(){
            try {
                List<Categoria> categorias = categoriaService.getAllCategorias();
                return ResponseEntity.ok(categorias);
            }catch (Exception e){
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @GetMapping("/{id}")
        public ResponseEntity <Categoria> getCategoriaByID(@PathVariable Long id){
            try {
                Categoria categoria = categoriaService.findBiID(id);
                return ResponseEntity.ok(categoria);
            }catch (IllegalArgumentException e){
                return ResponseEntity.noContent().build();
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @PostMapping
        public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria){
            try {
                Categoria createdCategoria = categoriaService.createCategoria(categoria);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @PutMapping("/{id}")
        public ResponseEntity<Categoria> updateCategoria(@PathVariable long id, @RequestBody Categoria categoriaDetails){
            try {
                Categoria updatedCategoria = categoriaService.updateCategoria(id,categoriaDetails);
                return ResponseEntity.ok(updatedCategoria);
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategoria(@PathVariable long id){
                try {
                    if (categoriaService.existsById(id)){
                        categoriaService.deleteCategoria(id);
                        return ResponseEntity.noContent().build();
                    }else {
                        return  ResponseEntity.notFound().build();

                    }
                }catch (Exception e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }
}
