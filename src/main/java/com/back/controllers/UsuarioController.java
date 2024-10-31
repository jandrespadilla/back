package com.back.controllers;

import com.back.models.Usuario;
import com.back.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity <Usuario> getUsuarioByID(@PathVariable Long id){
        if (usuarioRepository.existsById(id)){
            Usuario usuario = usuarioRepository.findById(id).get();
            return ResponseEntity.ok(usuario) ;
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return  usuarioRepository.save(usuario);

    }


}
