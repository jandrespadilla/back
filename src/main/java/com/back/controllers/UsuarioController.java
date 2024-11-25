package com.back.controllers;

import com.back.models.Usuario;


import com.back.services.UsuarioService;
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
@Tag(name = "Gestion de Usuarios", description = "Endpoints para Gestionar ABMC de Usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;
    @Operation(summary = "Obtener la Lista de todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno al tratar de obtener la lista de usuarios", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario(){
        try {
            List<Usuario> usuarios= usuarioService.getAllUsuarios();
            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content) })
    @Operation(summary = "Obtener un usuario por su ID")

    @GetMapping("/{id}")
    public ResponseEntity <Usuario> getUsuarioByID(@PathVariable Long id){
         try {
             Usuario usuario = usuarioService.findById(id);
             return ResponseEntity.ok(usuario);
         }catch (IllegalArgumentException e){
             return ResponseEntity.notFound().build();
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
    }
    @Operation(summary = "Agregar un nuevo Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping("/createUsuario")
    public ResponseEntity<Usuario>  createUsuario(@RequestBody Usuario usuario){
        try {
             Usuario createUsuario  = usuarioService.saveUsuario(usuario);
             return ResponseEntity.ok(createUsuario);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @Operation(summary = "Editar un Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario editado correctamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable long id,@RequestBody Usuario usuarioDetails ){
        try {
            Usuario updateUsuario = usuarioService.updateUsuario(id,usuarioDetails);
            return ResponseEntity.ok(updateUsuario);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @Operation(summary = "Eliminar un Usuario ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content) })
    @DeleteMapping("/deleteusuario/{id}")
    public  ResponseEntity<String> deleteUsuario(@PathVariable Long id){
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
