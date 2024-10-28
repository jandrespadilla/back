package com.back.negocio;
import com.back.dao.DaoFactory;
import com.back.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.back.models.Usuario;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.back")
@EntityScan(basePackages = "com.back.models")
public class BackApplication implements CommandLineRunner{
	
	@Autowired
	private DaoFactory dao;
	
	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}
	@Override
	public void run(String... arg) {
		try {

			Usuario usuario = new Usuario ("hugo","sanchez", "9838378", "Zona Cotapa","hugoberugo@gmail.com");
			Producto producto = new Producto("Curso de Java", "Curso de java para la creacion de API",40,1200000.00);
			dao.createUsuario(usuario);
			dao.createProducto(producto);
		} catch (Exception e) {
			 e.printStackTrace(System.err);
		}
		
	}
}