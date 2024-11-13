package com.back.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "categorias")
// hice la clase abstracta item para que implemente id nombre y descripcion
public class Categoria  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos = new ArrayList<>(); // Inicialización de la lista


    public Categoria() {
        super();

    }


}
