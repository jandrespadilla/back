package com.back.models;

import com.back.abstractas.Persona;

public class Usuario extends Persona {
	 
	    private int id;
	    private String direccion;
	    private String email;

	    public Usuario(int id, String nombre, String apellido, String dni, String direccion, String email) {
	        super(nombre, apellido, dni);
	        this.id = id;
	        this.direccion = direccion;
	        this.email = email;
	    }

	    @Override
	    public int getId() {
	        return id;
	    }

	    @Override
	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    @Override
	    public String toString() {
	        return "Usuario{" +
	                "id=" + id +
	                ", nombre='" + nombre + '\'' +
	                ", apellido='" + apellido + '\'' +
	                ", dni='" + dni + '\'' +
	                ", direccion='" + direccion + '\'' +
	                ", email='" + email + '\'' +
	                '}';
	    }
}
