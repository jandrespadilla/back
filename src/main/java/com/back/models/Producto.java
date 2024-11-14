package com.back.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "productos")
public class Producto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @Column( nullable = false)
    private int stock;
    @Column( nullable = false)
    private double precio;
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )@JsonIgnore
    private List<Categoria> categorias = new ArrayList<>();

}