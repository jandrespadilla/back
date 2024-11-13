package com.back.models;


import com.back.interfaces.Identificable;

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
@Entity
@Table(name = "ventas_cabecera")
public class VentaCabecera implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    private String numeroFactura;
    @Column( nullable = false)
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    private double totalVenta;
    private String formaPago;




    @OneToMany(mappedBy = "ventaCabecera",  fetch = FetchType.EAGER)

    private List<VentaDetalle> detalles;




}
