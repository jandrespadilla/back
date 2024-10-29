package com.back.dao;


import java.util.List;
import com.back.models.Producto;
import com.back.models.Usuario;

import com.back.models.VentaCabecera;
import com.back.models.VentaDetalle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Service
public class DaoFactory {
	@PersistenceContext
	private EntityManager em;
	@Transactional
	public void createUsuario (Usuario usuario) {
			em.persist(usuario);
		
	}
	@Transactional
	public void createProducto (Producto producto) {
		em.persist(producto);

	}

	@Transactional
	public void createVenta(VentaCabecera ventaCabecera, List<VentaDetalle> detallesVenta) {

		em.persist(ventaCabecera);


		for (VentaDetalle detalle : detallesVenta) {
			detalle.setCabecera(ventaCabecera);
			em.persist(detalle);
		}
	}
	@Transactional
	public List<VentaCabecera> obtenerVentas() {
		return em.createQuery("SELECT v FROM VentaCabecera v", VentaCabecera.class).getResultList();
	}
}
