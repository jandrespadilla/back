package com.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.back.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
