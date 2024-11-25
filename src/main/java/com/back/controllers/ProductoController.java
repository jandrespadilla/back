package com.back.controllers;
import com.back.dtos.AignarCategoriaDTO;
import com.back.models.Categoria;
import com.back.models.Producto;
import com.back.services.ProductoService;
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
@RestController
@Tag(name = "Gestion de Productos", description = "Endpoints para Gestionar ABMC de Productos")
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Operation(summary = "Obtener la Lista de todos los Productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Productos obtenida correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno al tratar de obtener la lista de productos", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos(){
        try {
            List<Producto> productos = productoService.getAllProductos();
            return ResponseEntity.ok(productos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
    @Operation(summary = "Obtener un Producto por su ID")
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
    @Operation(summary = "Agregar un nuevo Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
        try {
            Producto createdProducto = productoService.createProducto(producto);
            return ResponseEntity.ok(createdProducto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Editar un Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto editado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable long id, @RequestBody Producto productoDetaill){
        try {
            Producto updatedProducto = productoService.updateProducto(id,productoDetaill);
            return ResponseEntity.ok(updatedProducto);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(summary = "Eliminar una Producto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content) })
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
    @Operation(summary = "Asignar una Categoria al Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria asignada al Producto correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
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