package com.negocio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.negocio.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
