package com.back.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Schema(description = "Modelo de Categorias")
@Entity
@Table(name = "categorias")
public class Categoria  {
    @Schema(description = "ID de la Categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Nombre de la Categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "Curso")
    private String nombre;
    @Schema(description = "Descricion de la Categoria",   example = "Esta categoria representa a los cursos indivuduales")
    private String descripcion;
    @ManyToMany(mappedBy = "categorias")
    @Schema(description = "Lista de Productos que tienen asignada esta categoria")
    private List<Producto> productos = new ArrayList<>();
}
