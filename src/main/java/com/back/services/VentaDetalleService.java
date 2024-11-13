package com.back.services;

import com.back.models.VentaDetalle;
import com.back.repositories.VentaDetalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaDetalleService {
    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;

    public List<VentaDetalle> getAllVentaDetalle(){
        return ventaDetalleRepository.findAll();
    }
    public VentaDetalle findById(long id){
        return ventaDetalleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No se encontro el detalle de la venta"));
    }
    public boolean existById(Long id){
        return ventaDetalleRepository.existsById(id);
    }
    @Transactional
    public VentaDetalle createVentaDetalle(VentaDetalle ventaDetalle){
        return ventaDetalleRepository.save(ventaDetalle);
    }
    @Transactional
    public VentaDetalle updateVentaDetall(long id, VentaDetalle ventaDetalleDetail){
        VentaDetalle ventaDetalle = ventaDetalleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No se encontro el detalle de la venta"));
        if (ventaDetalle.getCantidadCompra()>0){
            ventaDetalle.setCantidadCompra(ventaDetalleDetail.getCantidadCompra());
        }else{
            throw new IllegalArgumentException("La cantidad de productos no puede ser menor a 1");
        }
        ventaDetalle.setProducto(ventaDetalleDetail.getProducto());
        return ventaDetalleRepository.save(ventaDetalle);
    }
    public void deleteVentaDetalle(long id){
        if (!ventaDetalleRepository.existsById(id)){
            throw new IllegalArgumentException("No se encontro el detalle de la venta");
        }
        ventaDetalleRepository.deleteById(id);
    }
}
