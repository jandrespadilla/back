package com.back.repositories;

import com.back.models.Producto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository <Producto, Long> {

}
