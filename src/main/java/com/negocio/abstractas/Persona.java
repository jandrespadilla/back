package com.negocio.abstractas;

import com.negocio.interfaces.Identificable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
// esta clase abstracta es para que implemente id nombre  apellido y dni
// hice la interface identificable para que implemente el setid y getid

public abstract class Persona implements Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String dni;

    public Persona() {

    }
    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }


    @Override
	public Long getId() {
		// TODO Auto-generated method stub
		return (long) 0;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
