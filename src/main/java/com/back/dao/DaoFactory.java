package com.back.dao;

 

import com.back.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoFactory {
	@PersistenceContext
	private EntityManager em;
	@Transactional
	public void createUsuario(Usuario usuario) {
			em.persist(usuario);
		
	}
	 
}
