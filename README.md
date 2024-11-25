API REST - BackEnd para Manejo de venta de productos
Descripcion: Esta API proporciona endpoints para el manejo de un sistema rudimentario de usuarios, productos, sus categorías y las ventas de los mismos.

Version: 0.4

Contacto:

Nombre: Andres Padilla
URL: https://jorgeandrespadilla.com.ar
Email: jandrespadilla@gmail.com
Servidor:

http://localhost:8080
Documentacion:

Esta documentación describe las API disponibles para el sistema de venta de productos.

Autenticacion:

Actualmente la API no requiere autenticación para su uso.

Tags:

Gestión de items de las facturas
Cabecera de las facturas realizadas
Gestión de Usuarios
Gestión de Productos
Gestión de Categorias
Endpoints Disponibles
Usuarios
** - /api/usuarios**
* GET: Obtiene la Lista de todos los usuarios.
* POST: Agregar un nuevo Usuario.
* DELETE: Eliminar un Usuario (recibe id de usuario por parametro)

** - /api/usuarios/{id}**
* GET: Obtener un usuario por su ID.
* PUT: Editar un Usuario (recibe id de usuario por parametro).

Productos
** - /api/productos**
* GET: Obtener la Lista de todos los Productos.
* POST: Agregar un nuevo Producto.

** - /api/productos/{id}**
* GET: Obtener un Producto por su ID.
* PUT: Editar un Producto (recibe id de producto por parametro).
* DELETE: Eliminar un Producto (recibe id de producto por parametro).

** - /api/productos/asignarcategoria**
* POST: Asignar una Categoria al Producto.

Categorias
** - /api/categorias**
* GET: Obtener la Lista de todas las categorias.
* POST: Agregar una nueva Categoria.

** - /api/categorias/{id}**
* GET: Obtener una categorias por su ID.
* PUT: Editar una Categoría (recibe id de categoria por parametro).
* DELETE: Eliminar una Categoría (recibe id de categoria por parametro).

Facturas
** - /api/ventacabecera**
* GET: Obtener la Lista de todas las facturas.
* POST: Agregar una nueva Factura.

** - /api/ventacabecera/{id}**
* GET: Obtener una factura por su ID.
* PUT: Editar una Factura (recibe id de factura por parametro).
* DELETE: Eliminar una Factura (recibe id de factura por parametro).

Items de Facturas
** - /api/ventadetalle**
* GET: Obtener la Lista de todos los Items facturados.
* POST: Agregar un nuevo Item a la factura.

** - /api/ventadetalle/{id}**
* GET: Obtener un Item por su ID.
* PUT: Editar un Item (recibe id de item por parametro).
* DELETE: Eliminar una Item (recibe id de item por parametro).
