package com.negocio.models;

import com.negocio.abstractas.Persona;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")

// esta clase abstracta es para que implemente id nombre  apellido y dni
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String nombre;

    @Column(nullable = false)
    protected String apellido;

    @Column(nullable = false)
    protected String dni;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String email;

    // Constructor sin argumentos (default constructor)
    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email; 

    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
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
