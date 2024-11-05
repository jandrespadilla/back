package com.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.back.models.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByDni(String dni);
    Optional<Usuario> findByEmail(String email);
}
