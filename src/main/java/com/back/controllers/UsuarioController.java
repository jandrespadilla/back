package com.back.controllers;

import com.back.models.Usuario;
import com.back.repositories.UsuarioRepository;

import com.back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario(){
        try {
            List<Usuario> usuarios= usuarioService.getAllUsuarios();
            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


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
