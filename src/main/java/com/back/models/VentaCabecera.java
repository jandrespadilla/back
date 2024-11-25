package com.back.models;


import com.back.interfaces.Identificable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "Modelo de la Cabecera de la venta")
@Entity
@Table(name = "ventas_cabecera")
public class VentaCabecera implements Identificable {
    @Schema(description = "ID de la cabecera de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Numero de factura de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "000-0001")
    @Column( nullable = false)
    private String numeroFactura;
    @Schema(description = "Fecha y hora en que se realizo la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "01/08/2024")
    @Column( nullable = false)
    private LocalDateTime fechaVenta = LocalDateTime.now();
    @Schema(description = "Usuario que realizo la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "Juan Peres")
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    @Schema(description = "Total de la venta",  example = "120000")
    private double totalVenta;
    @Schema(description = "Forma de pago de la venta",  example = "Efectivo")
    private String formaPago;



    @Schema(description = "Detalle de los items de la venta")
    @OneToMany(mappedBy = "ventaCabecera",  fetch = FetchType.EAGER)

    private List<VentaDetalle> detalles;




}
