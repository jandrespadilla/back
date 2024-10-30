package com.back.models;

import com.back.abstractas.Item;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
// hice la clase abstracta item para que implemente id nombre y descripcion
public class Categoria extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion;

    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos = new ArrayList<>(); // Inicialización de la lista


    public Categoria() {
        super();

    }
    public Categoria(String nombre) {
        super( nombre);

    }
    public Categoria(String nombre, String descripcion) {
        super( nombre, descripcion);

    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
            producto.getCategorias().add(this); // Agrega esta categoría a la lista del producto
        }
    }

    public void removeProducto(Producto producto) {
        if (productos.contains(producto)) {
            productos.remove(producto);
            producto.getCategorias().remove(this); // Remueve esta categoría de la lista del producto
        }
    }
}
