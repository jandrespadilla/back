package com.back.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "ventas_detalle")
public class VentaDetalle   {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "id_ventas_cabecera", nullable = false)
    @JsonIgnore

    private VentaCabecera ventaCabecera;


    @ManyToOne
    @JoinColumn(name = "id_productos", nullable = false)

    private Producto producto;


    @Column( nullable = false)
    private double precioProducto;

    @Column( nullable = false)
    private int cantidadCompra;

    @Column( nullable = false)
    private double totalDetalle;



}
