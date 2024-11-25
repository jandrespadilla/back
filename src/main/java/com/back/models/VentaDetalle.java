package com.back.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(description = "Modelo del detalle de la venta")
@Data
@Entity
@Table(name = "ventas_detalle")
public class VentaDetalle   {

    @Schema(description = "ID deL detalle de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Schema(description = "ID de la cabecera de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "12")
    @ManyToOne
    @JoinColumn(name = "id_ventas_cabecera", nullable = false)
    @JsonIgnore

    private VentaCabecera ventaCabecera;

    @Schema(description = "Producto vendido en este detalle")
    @ManyToOne
    @JoinColumn(name = "id_productos", nullable = false)

    private Producto producto;

    @Schema(description = "Precio unitario del Producto vendido en este detalle", example = "139009")
    @Column( nullable = false)
    private double precioProducto;
    @Schema(description = "Cantidad del Producto vendido en este detalle", example = "4")
    @Column( nullable = false)
    private int cantidadCompra;
    @Schema(description = "Total del precio del Producto vendido en este detalle(P/U*Cantidad)", example = "1390094")
    @Column( nullable = false)
    private double totalDetalle;



}
