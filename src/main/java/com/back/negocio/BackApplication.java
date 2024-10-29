package com.back.negocio;
import com.back.dao.DaoFactory;
import com.back.models.Producto;
import com.back.models.VentaCabecera;
import com.back.models.VentaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

import com.back.models.Usuario;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
			this.generaDatos();
			this.generaDatos();
		} catch (Exception e) {
			 e.printStackTrace(System.err);
		}
	}
	public void generaDatos(){

		Usuario usuario1 = new Usuario ("Juan","Lopez", "6783833", "Zona Bajada","juanlopez@gmail.com");
		Usuario usuario2 = new Usuario ("Sebastian","Gaitan", "4664543", "Zona Redengas","seba_gaitan@gmail.com");
		Usuario usuario3 = new Usuario ("Julieta","Francisconi", "3487445", "Zona Centro","Julifrancisconi@gmail.com");
		Usuario usuario4 = new Usuario ("hugo","sanchez", "9838378", "Zona Cotapa","hugoberugo@gmail.com");
		Producto producto1 = new Producto("Curso de Java", "Curso de java para la creacion de API",40,120000.00);
		Producto producto2 = new Producto("Curso de PHP", "Curso de PHP con laravel para la creacion de API",40,150000.00);
		Producto producto3 = new Producto("Curso de Python", "Curso de Python con Dianjo para la creacion de API",40,130000.00);
		Producto producto4 = new Producto("Curso de HTML", "Curso de HTML con CSS usando Bootstrap",40,100000.00);
		Producto producto5 = new Producto("Curso de JavaScript", "Curso de JavaScript con JQuery ",40,90000.00);
		dao.createUsuario(usuario1);
		dao.createUsuario(usuario2);
		dao.createUsuario(usuario3);
		dao.createUsuario(usuario4);

		dao.createProducto(producto1);
		dao.createProducto(producto2);
		dao.createProducto(producto3);
		dao.createProducto(producto4);
		dao.createProducto(producto5);

		Map<Producto, Integer> listaProductos1 = new HashMap<>();
		listaProductos1.put(producto1, 2);
		listaProductos1.put(producto5, 1);

		this.procesarVenta(usuario4,listaProductos1);

		Map<Producto, Integer> listaProductos2 = new HashMap<>();
		listaProductos2.put(producto4,3);
		listaProductos2.put(producto3,2);
		this.procesarVenta(usuario3,listaProductos2);

		Map<Producto, Integer> listaProductos3 = new HashMap<>();
		listaProductos3.put(producto1,1);
		listaProductos3.put(producto4,1);
		this.procesarVenta(usuario1,listaProductos3);

		Map<Producto, Integer> listaProductos4 = new HashMap<>();
		listaProductos4.put(producto1,10);

		this.procesarVenta(usuario2,listaProductos4);
	}

	public void procesarVenta(Usuario usuario, Map<Producto, Integer> productosConCantidades) {
		double totalVenta = 0.0;
		VentaCabecera ventaCabecera = new VentaCabecera("001-000001", LocalDateTime.now(), usuario, 500.00, "Efectivo");
		List<VentaDetalle> detallesVenta = new ArrayList<>();
		for (Map.Entry<Producto, Integer> entry : productosConCantidades.entrySet()) {
			Producto producto = entry.getKey();
			int cantidadCompra = entry.getValue();
			double totalDetalle = producto.getPrecio() * cantidadCompra;
			VentaDetalle detalle = new VentaDetalle(ventaCabecera,  producto, producto.getPrecio(), cantidadCompra, totalDetalle);
			detallesVenta.add(detalle);
			totalVenta += totalDetalle;
		}
		ventaCabecera.setTotalVenta(totalVenta);
		dao.createVenta(ventaCabecera, detallesVenta);
	}

	public void listarVentas(List<VentaCabecera> ventas) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (VentaCabecera venta : ventas) {
			String fechaFormateada = venta.getFechaVenta().format(formatter);


			System.out.println("Venta " + venta.getId() + ": " + venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido() +
					" | Fecha: " + fechaFormateada +
					" | Total: $" + venta.getTotalVenta());


			System.out.println("    Productos: ");
			for (VentaDetalle detalle : venta.getDetalles()) {
				System.out.println("        - " + detalle.getProducto().getNombre() +
						" | Cantidad: " + detalle.getCantidadCompra() +
						" | Precio: $" + detalle.getPrecioProducto() +
						" | Total del detalle: $" + detalle.getTotalDetalle());
			}
		}
	}


	public void listarVenta(VentaCabecera venta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = venta.getFechaVenta().format(formatter);

		System.out.println("Venta " + venta.getId() + ": " + venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido() +
				" | Fecha: " + fechaFormateada +
				" | Total: $" + venta.getTotalVenta());

		System.out.println("    Productos: ");
		for (VentaDetalle detalle : venta.getDetalles()) {
			System.out.println("        - " + detalle.getProducto().getNombre() +
					" | Cantidad: " + detalle.getCantidadCompra() +
					" | Precio: $" + detalle.getPrecioProducto() +
					" | Total del detalle: $" + detalle.getTotalDetalle());
		}
	}
}