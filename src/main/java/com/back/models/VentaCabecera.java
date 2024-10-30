package com.back.models;


import com.back.interfaces.Identificable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas_cabecera")
// hice la interface identificable para que implemente el setid y getid
public class VentaCabecera implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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


  // la pide hibernate
    public VentaCabecera() {
    }

   //constructor sin id
    public VentaCabecera( String numeroFactura, LocalDateTime fechaVenta, Usuario usuario, double totalVenta, String formaPago) {

        this.numeroFactura = numeroFactura;
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
        this.totalVenta = totalVenta;
        this.formaPago = formaPago;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public List<VentaDetalle> getDetalles() {

        return this.detalles;
    }
    // cargo el detalle de la venta
    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "VentaCabecera{" +
                "id=" + id +
                ", numeroFactura='" + numeroFactura + '\'' +
                ", fechaVenta=" + fechaVenta +
                ", usuario=" + usuario.getNombre() + " " + usuario.getApellido() +
                ", totalVenta=" + totalVenta +
                ", formaPago='" + formaPago + '\'' +
                '}';
    }
}
