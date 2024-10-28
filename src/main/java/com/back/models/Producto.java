package com.back.models;

import com.back.abstractas.Item;
import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( nullable = false)
    private int stock;
    @Column( nullable = false)
    private double precio;

    public Producto(String nombre, String descripcion, int stock, double precio) {
        super( nombre, descripcion);
        this.stock = stock;
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}