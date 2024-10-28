package com.back.dao;

 

import com.back.models.Producto;
import com.back.models.Usuario;

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
}
