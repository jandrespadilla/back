package com.back.dao;


import java.util.List;

import com.back.models.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DaoFactory {
	@PersistenceContext
	private EntityManager em;

	// persisto usuario
	@Transactional
	public void createUsuario (Usuario usuario) {
			em.persist(usuario);
		
	}
	// persisto productos
	@Transactional
	public void createProducto (Producto producto) {
		em.persist(producto);

	}
	// persisto categorias
	@Transactional
	public void createCategoria (Categoria categoria) {
		em.persist(categoria);

	}

	// persisto ventas
	@Transactional
	public void createVenta(VentaCabecera ventaCabecera, List<VentaDetalle> detallesVenta) {
		em.persist(ventaCabecera);
		em.flush();
		Long id = (long) ventaCabecera.getId();
		String numeroFactura = String.format("001-%06d", id);
		ventaCabecera.setNumeroFactura(numeroFactura);
		em.merge(ventaCabecera);
		for (VentaDetalle detalle : detallesVenta) {
			detalle.setCabecera(ventaCabecera);
			em.persist(detalle);
		}
	}
	//  traigo lista de ventas
	@Transactional
	public List<VentaCabecera> obtenerVentas() {
		String jpql = "SELECT v FROM VentaCabecera v LEFT JOIN FETCH v.detalles";
		return em.createQuery(jpql, VentaCabecera.class).getResultList();
	}
	// persisto actualizacion de productos
	@Transactional
	public void updateProducto(Producto producto) {
		em.merge(producto);
	}

}
