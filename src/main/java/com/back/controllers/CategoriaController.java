package com.back.controllers;
import com.back.models.Categoria;
import com.back.services.CategoriaService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Gestion de Categorias", description = "Endpoints para Gestionar ABMC de categorias")
public class CategoriaController {
        @Autowired
        private CategoriaService categoriaService;
        @Operation(summary = "Obtener la Lista de todas las categorias.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Lista de categorias obtenida correctamente", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
                @ApiResponse(responseCode = "500", description = "Error interno al tratar de obtener la lista de categorias", content = @Content)
        })
        @GetMapping
        public ResponseEntity<List<Categoria>> getAllCategorias(){
            try {
                List<Categoria> categorias = categoriaService.getAllCategorias();
                return ResponseEntity.ok(categorias);
            }catch (Exception e){
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada", content = @Content) })
        @Operation(summary = "Obtener una categorias por su ID")
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
        @Operation(summary = "Agregar una nueva Categoria")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Categoria agregada correctamente", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
        @PostMapping
        public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria){
            try {
                Categoria createdCategoria = categoriaService.createCategoria(categoria);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        @Operation(summary = "Editar una Categoría")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría editada correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
        @PutMapping("/{id}")
        public ResponseEntity<Categoria> updateCategoria(@PathVariable long id, @RequestBody Categoria categoriaDetails){
            try {
                Categoria updatedCategoria = categoriaService.updateCategoria(id,categoriaDetails);
                return ResponseEntity.ok(updatedCategoria);
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        @Operation(summary = "Eliminar una Categoría ")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content) })
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
