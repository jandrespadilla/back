package com.back.models;

import com.back.interfaces.Identificable;
import jakarta.persistence.*;

@Entity
@Table(name = "ventas_detalle")
public class VentaDetalle  implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @ManyToOne
    @JoinColumn(name = "id_ventas_cabecera", nullable = false)
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
    public VentaDetalle() {

    }
    public VentaDetalle(VentaCabecera cabecera, Producto producto, double precioProducto, int cantidadCompra, double totalDetalle) {

        this.ventaCabecera = cabecera;
        this.producto = producto;
        this.precioProducto = precioProducto;
        this.cantidadCompra = cantidadCompra;
        this.totalDetalle = calcularTotalDetalle();
    }

    // Método para calcular el total del detalle
    private double calcularTotalDetalle() {
        return this.precioProducto * this.cantidadCompra;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub

    }

    public VentaCabecera getCabecera() {
        return ventaCabecera;
    }

    public void setCabecera(VentaCabecera cabecera) {
        this.ventaCabecera = cabecera;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public double getTotalDetalle() {
        return totalDetalle;
    }

    @Override
    public String toString() {
        return "VentaDetalle{" +
                "id=" + id +
                ", producto=" + producto.getNombre() +
                ", precioProducto=" + precioProducto +
                ", cantidadCompra=" + cantidadCompra +
                ", totalDetalle=" + totalDetalle +
                '}';
    }
}
