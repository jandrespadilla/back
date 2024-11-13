package com.back.services;

import com.back.models.Usuario;
import com.back.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        public List<Usuario> getAllUsuarios(){
                return usuarioRepository.findAll();
        }

        public Usuario findById(long id){
            return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no Encontrado."));
        }

        public Usuario saveUsuario(Usuario usuario){

            usuarioRepository.findByDni(usuario.getDni()).ifPresent(existingUsuario -> {
                throw new IllegalArgumentException("El DNI ya está en uso."); });
            usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(existingUsuario -> {
                throw new IllegalArgumentException("El Email ya está en uso.");
            });
            return usuarioRepository.save(usuario);

        }

        public Usuario updateUsuario(Long id, Usuario usuarioDetails){
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setNombre(usuarioDetails.getNombre());
            String nuevoDNI = usuarioDetails.getDni();
            if (usuarioDetails.getDni()!=null && !nuevoDNI.equals(usuario.getDni())){
                usuarioRepository.findByDni(usuarioDetails.getDni()).ifPresent(existingUsuario -> {
                    throw new IllegalArgumentException("El DNI ya está en uso.");
                });
                usuario.setDni(usuarioDetails.getDni());
            }
            String nuevoEmail = usuarioDetails.getEmail();
            if (usuarioDetails.getEmail()!=null && !nuevoEmail.equals(usuario.getEmail())){
                usuarioRepository.findByEmail(usuarioDetails.getEmail()).ifPresent(existingUsuario -> {
                    throw new IllegalArgumentException("El Email ya está en uso.");
                });
                usuario.setEmail(usuarioDetails.getEmail());
            }
            usuario.setDireccion(usuarioDetails.getDireccion());

            return  usuarioRepository.save(usuario);
        }

        public void deleteUsuario(long id){
            if (!usuarioRepository.existsById(id)){
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            usuarioRepository.deleteById(id);
        }

}
