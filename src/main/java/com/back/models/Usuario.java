package com.back.models;

import com.back.abstractas.Persona;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Schema(description = "Modelo de Usuarios")
@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@ToString
@Setter
public class Usuario {
    @Schema(description = "ID del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Nombre del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "Juan")
    @Column(nullable = false)
    protected String nombre;
    @Schema(description = "Apellido del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "Perez")
    @Column(nullable = false)
    protected String apellido;
    @Schema(description = "DNI del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "27287839")
    @Column(nullable = false)
    protected String dni;
    @Schema(description = "Direccion del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "25 de mayo 510")
    @Column(nullable = false)
    private String direccion;
    @Schema(description = "Correo electronico del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "juan.peres@gmail.com")
    @Column(nullable = false)
    private String email;




}
