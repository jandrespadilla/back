package com.back.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Schema(description = "Modelo de Productos")
@Table(name = "productos")
public class Producto  {
    @Schema(description = "ID del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Curso de Tejido")
    private String nombre;
    @Schema(description = "Descripcion del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Descripcion de la orientacion del curso de tejido")
    private String descripcion;
    @Schema(description = "Stock del producto",   example = "13")

    @Column( nullable = false)
    private int stock;
    @Schema(description = "Precio unitario del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Precio unitario del producto ")
    @Column( nullable = false)
    private double precio;
    @Schema(description = "Categorias asociadas del producto")
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )@JsonIgnore
    private List<Categoria> categorias = new ArrayList<>();
}