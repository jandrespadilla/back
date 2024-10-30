package com.back.negocio;
import com.back.dao.DaoFactory;
import com.back.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

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
			// PASO 1
			// Crear tablas y insertar los datos en db
			this.generaDatos();
			// PASO 2
			// Lista ventas Creadas y sus detalles
			this.listarVentas(); //Uso la ultima que no lleva parametros, las otras dos son utilizarlas en otro momento
		} catch (Exception e) {
			 e.printStackTrace(System.err);
		}
	}
	// Funcion del paso uno
	// Genera las tablas y inserta los datos
	public void generaDatos(){
		// Genero los usuarios
		Usuario usuario1 = new Usuario ("Juan","Lopez", "6783833", "Zona Bajada","juanlopez@gmail.com");
		Usuario usuario2 = new Usuario ("Sebastian","Gaitan", "4664543", "Zona Redengas","seba_gaitan@gmail.com");
		Usuario usuario3 = new Usuario ("Julieta","Francisconi", "3487445", "Zona Centro","Julifrancisconi@gmail.com");
		Usuario usuario4 = new Usuario ("hugo","sanchez", "9838378", "Zona Cotapa","hugoberugo@gmail.com");
		//Genero los productos
		Producto producto1 = new Producto("Curso de Java", "Curso de java para la creacion de API",40,120000.00);
		Producto producto2 = new Producto("Curso de PHP", "Curso de PHP con laravel para la creacion de API",40,150000.00);
		Producto producto3 = new Producto("Curso de Python", "Curso de Python con Dianjo para la creacion de API",40,130000.00);
		Producto producto4 = new Producto("Curso de HTML", "Curso de HTML con CSS usando Bootstrap",40,100000.00);
		Producto producto5 = new Producto("Curso de JavaScript", "Curso de JavaScript con JQuery ",40,90000.00);
		// Genero los cursos
		Categoria categoria1 = new Categoria("Desarrollo FrontEnd");
		Categoria categoria2 = new Categoria("Desarrollo BackEnd");
		Categoria categoria3 = new Categoria("Desarrollo FullStack");
		// persisto categorias
		dao.createCategoria(categoria1);
		dao.createCategoria(categoria2);
		dao.createCategoria(categoria3);
		// persisto usuarios
		this.dao.createUsuario(usuario1);
		this.dao.createUsuario(usuario2);
		this.dao.createUsuario(usuario3);
		this.dao.createUsuario(usuario4);


		// relaciono productos con categorias
		producto1.addCategoria(categoria2);
		producto2.addCategoria(categoria1);
		producto2.addCategoria(categoria2);
		producto2.addCategoria(categoria3);
		producto3.addCategoria(categoria1);
		producto3.addCategoria(categoria2);
		producto3.addCategoria(categoria3);
		producto4.addCategoria(categoria1);
		producto4.addCategoria(categoria3);
		producto5.addCategoria(categoria1);
		producto5.addCategoria(categoria3);


		// persisto productos y la relacion entre producto y categoria
		this.dao.createProducto(producto1);
		this.dao.createProducto(producto2);
		this.dao.createProducto(producto3);
		this.dao.createProducto(producto4);
		this.dao.createProducto(producto5);

		//armo la venta 1
		Map<Producto, Integer> listaProductos1 = new HashMap<>();
		// aca lleno una lista de los productos que voy a comprar y la cantidad de cada producto
		listaProductos1.put(producto1, 2);
		listaProductos1.put(producto5, 1);
		// aca le envio la lista a procesar ventas con el usuario que va a comprar el producto
		this.procesarVenta(usuario4,listaProductos1);

		//armo la venta 2
		Map<Producto, Integer> listaProductos2 = new HashMap<>();
		// aca lleno una lista de los productos que voy a comprar y la cantidad de cada producto
		listaProductos2.put(producto4,3);
		listaProductos2.put(producto3,2);
		// aca le envio la lista a procesar ventas con el usuario que va a comprar el producto
		this.procesarVenta(usuario3,listaProductos2);
		//armo la venta 3
		Map<Producto, Integer> listaProductos3 = new HashMap<>();
		// aca lleno una lista de los productos que voy a comprar y la cantidad de cada producto
		listaProductos3.put(producto1,1);
		listaProductos3.put(producto4,1);
		// aca le envio la lista a procesar ventas con el usuario que va a comprar el producto
		this.procesarVenta(usuario1,listaProductos3);
		//armo la venta 4
		Map<Producto, Integer> listaProductos4 = new HashMap<>();
		// aca lleno una lista de los productos que voy a comprar y la cantidad de cada producto
		listaProductos4.put(producto1,10);
		// aca le envio la lista a procesar ventas con el usuario que va a comprar el producto
		this.procesarVenta(usuario2,listaProductos4);
	}




   // proceso la venta recibe como parametro el usuario que va a comprar la lista de los productos que va a adquirir y la cantidad
	public void procesarVenta(Usuario usuario, Map<Producto, Integer> productosConCantidades) {
		// este valor lo uso para despues guardar el total de la venta y poder guardarlo en la cabecera como redundante
		double totalVenta = 0.0;
		// genero la cabecera de la venta
		// en este caso dejo un TODO: el numero de factura que le paso no lo uso ya que en el dao genero un numero con el formato 001 - id completando los los digitos que faltan con 0
		VentaCabecera ventaCabecera = new VentaCabecera("001-000001", LocalDateTime.now(), usuario, 500.00, "Efectivo");
		// creo la lista que van a ser los detalles de la venta
		List<VentaDetalle> detallesVenta = new ArrayList<>();
		// recorro la lista de los productos que llegan por parametros
		for (Map.Entry<Producto, Integer> entry : productosConCantidades.entrySet()) {
			// el producto de la iteracion
			Producto producto = entry.getKey();
			// la cantidad del producto que voy a comprar
			int cantidadCompra = entry.getValue();
			// Controlo si tengo stock del producto antes de avanzar
			if (producto.getStock() >= cantidadCompra) {
					// Calculo el valor del producto por la cantidad que quiere del mismo y con eso tengo el valor total de este registro
					double totalDetalle = producto.getPrecio() * cantidadCompra;
					// genero el detalle de la venta paso por prametro la cabeza de la venta el producto el precio unitario la cantidad y el total del detalle
					VentaDetalle detalle = new VentaDetalle(ventaCabecera,  producto, producto.getPrecio(), cantidadCompra, totalDetalle);
					// lo agrego la lista de lo que va a ser el detalle de la factura
					detallesVenta.add(detalle);
					// actualizo el stock del producto
					producto.setStock(producto.getStock() - cantidadCompra);
				    // persisto en db el stock
					dao.updateProducto(producto);
					// sumo el total del detalle para persisterlo en el de la venta
					totalVenta += totalDetalle;
			} else {
				// este es un mensaje en caso de no tener stock suficiente
				System.out.println("Stock insuficiente para el producto: " + producto.getNombre());

			}
		}
		// seteo el total de la venta en la cabecera antes de persistir
		ventaCabecera.setTotalVenta(totalVenta);
		// persisto la cabecera de la venta y su correspondiente detalle
		this.dao.createVenta(ventaCabecera, detallesVenta);
	}
   // Funcion que todavia no uso no le agregue comentarios
	public void listarVentas(List<VentaCabecera> ventas) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (VentaCabecera venta : ventas) {
			String fechaFormateada = venta.getFechaVenta().format(formatter);


			System.out.println("Venta " + venta.getNumeroFactura() + ": " + venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido() +
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

	// Funcion que todavia no uso no le agregue comentarios
	public void listarVenta(VentaCabecera venta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = venta.getFechaVenta().format(formatter);

		System.out.println("Venta " + venta.getNumeroFactura() + ": " + venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido() +
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

	// lista las ventas
	public void listarVentas() {
		// trailo las ventas de la db
		List<VentaCabecera> ventas = dao.obtenerVentas();
		// un formateo para que quede bien la fecha
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// recorro las ventas
		for (VentaCabecera venta : ventas) {
			// formateo la fecha de la venta para que se vean lindas
			String fechaFormateada = venta.getFechaVenta().format(formatter);
			// hago un sysout con los datos de la cabecera traigo nombre y apellido del usuario que compro
			System.out.println("Venta " + venta.getNumeroFactura() + ": " + venta.getUsuario().getNombre() + " " + venta.getUsuario().getApellido() +
					" | Fecha: " + fechaFormateada +
					" | Total: $" + venta.getTotalVenta());

			System.out.println("    Productos: ");
			// listo los productos que compro el usuario
			if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
				for (VentaDetalle detalle : venta.getDetalles()) {
					// hago un sysout del nombre del producto la cantidad que compro el precio unitario y el total
					// aca hay otro TODO deberia hacer una funcion y que me traiga las categorias a la que pertenese el producto para que quede cheto
					System.out.println("        - " + detalle.getProducto().getNombre() +
							" | Cantidad: " + detalle.getCantidadCompra() +
							" | Precio: $ " + detalle.getPrecioProducto() +
							" | Total del detalle: $ " + detalle.getTotalDetalle());
				}
			} else {
				System.out.println("        No hay detalles para esta venta.");
			}
		}
	}
}