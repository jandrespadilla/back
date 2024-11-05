package com.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.back.models.Categoria;

import java.util.Optional;

public interface CategoriaRepostery extends JpaRepository<Categoria, Long> {
}
