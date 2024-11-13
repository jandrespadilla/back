package com.back.services;

import com.back.models.Producto;
import com.back.repositories.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepostery;

    public List<Producto> getAllProductos(){
        return productoRepostery.findAll();
    }
    public Producto findByID(long id){
        return productoRepostery.findById(id).orElseThrow(()->new IllegalArgumentException("Producto no encontrado"));
    }
    public boolean existByID(Long id){
        return productoRepostery.existsById(id);
    }
    @Transactional
    public Producto createProducto(Producto producto){
        return productoRepostery.save(producto);
    }
    @Transactional
    public Producto updateProducto(Long id, Producto productoDetail){
        Producto producto = productoRepostery.findById(id).orElseThrow(()-> new IllegalArgumentException("Producto no encontrado"));
        if (productoDetail.getNombre()!=null){
            producto.setNombre(productoDetail.getNombre());
        }else{
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o estar vacio");
        }
        if (productoDetail.getStock()>=0){
            producto.setStock(productoDetail.getStock());
        }else{
            throw new IllegalArgumentException("El stock del producto no puede ser menor a 0");
        }
        producto.setDescripcion(productoDetail.getDescripcion());
        return productoRepostery.save(producto);
    }
    public void deleteProducto(Long id){
        if (!productoRepostery.existsById(id)){
            throw new IllegalArgumentException("Producto no encontrado");
        }
        productoRepostery.deleteById(id);
    }
}