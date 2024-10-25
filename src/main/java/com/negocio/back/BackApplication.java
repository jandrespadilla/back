package com.negocio.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.back.dao.DaoFactory;
import com.back.models.Usuario;


@SpringBootApplication
public class BackApplication implements CommandLineRunner{
	
	@Autowired
	private DaoFactory dao;
	
	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
	public void run(String... arg) {
		try {

			Usuario usuario = new Usuario ("hugo","sanchez", "9838378", "Zona Cotapa","hugoberugo@gmail.com");	
			
			dao.createUsuario(usuario);

		} catch (Exception e) {
			 e.printStackTrace(System.err);
		}
		
	}
}
