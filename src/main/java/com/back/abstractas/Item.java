package com.back.abstractas;


import com.back.interfaces.Identificable;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class Item implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column( nullable = false)
    protected String nombre;
    protected String descripcion;
    public Item() {


    }
    public Item( String nombre, String descripcion) {

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub

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
