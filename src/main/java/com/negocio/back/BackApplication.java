package com.negocio.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.back.models.Usuario;
import com.coderhouse.dao.DaoFactory;
import com.coderhouse.modelo.Alumno;
import com.coderhouse.modelo.Curso;

@SpringBootApplication
public class BackApplication {
	
	@Autowired
	private DaoFactory dao;
	
	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
	public void run(String... arg) {
		try {

			Usuario usuario = new Usuario ("hugo","sanchez", 283434342, "Zona Cotapa","hugoberugo@gmail.com");	
			
			dao.createAlumno(usuario);

		} catch (Exception e) {
			 e.printStackTrace(System.err);
		}
		
	}
}
