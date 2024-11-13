package com.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.back.models.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
