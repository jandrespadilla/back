package com.back.abstractas;


import com.back.interfaces.Identificable;
import jakarta.persistence.*;

@MappedSuperclass
// esta clase abstracta es para que implemente id nombre y descripcion
// hice la interface identificable para que implemente el setid y getid
public abstract class Item implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column( nullable = false)
    protected String nombre;
    protected String descripcion;
    public Item() {


    }
    public Item( String nombre) {

        this.nombre = nombre;

    }
    public Item( String nombre, String descripcion) {

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return id;
    }


    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
