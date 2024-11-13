package com.back.services;

import com.back.models.VentaCabecera;
import com.back.repositories.VentaCabeceraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaCabeceraService {
    @Autowired
    private VentaCabeceraRepository ventaCabeceraRepository;
    public List<VentaCabecera> getAllVentaCabecera(){
        return ventaCabeceraRepository.findAll();
    }
    public VentaCabecera findById(Long id){
        return ventaCabeceraRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No se encontro el comprobante de venta"));
    }
    public boolean existById(Long id){
        return ventaCabeceraRepository.existsById(id);
    }
    @Transactional
    public VentaCabecera createVentaCabecera(VentaCabecera ventaCabecera){
        return ventaCabeceraRepository.save(ventaCabecera);
    }
    @Transactional
    public VentaCabecera updateVentaCabecera(Long id, VentaCabecera ventaCabeceraDetails){
        VentaCabecera ventaCabecera= ventaCabeceraRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No se encontro el comprobante para editar."));

        if (ventaCabeceraDetails.getFormaPago()!=null){
            ventaCabecera.setFormaPago(ventaCabeceraDetails.getFormaPago());
        }else {
            throw new IllegalArgumentException("No es correcta la forma de pago");
        }
        ventaCabecera.setFechaVenta(ventaCabeceraDetails.getFechaVenta());

        ventaCabecera.setTotalVenta(ventaCabeceraDetails.getTotalVenta());

        ventaCabecera.setNumeroFactura(ventaCabeceraDetails.getNumeroFactura());
        return ventaCabeceraRepository.save(ventaCabecera);
    }
    public void deleteVentaCabecera(long id){
        if (!ventaCabeceraRepository.existsById(id)){
            throw new IllegalArgumentException("No se encontro el comprobante para eliminar");
        }
        ventaCabeceraRepository.deleteById(id);
    }
}
