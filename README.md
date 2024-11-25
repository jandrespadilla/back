# API REST - BackEnd para Manejo de Venta de Productos

**Descripción**: Esta API proporciona endpoints para el manejo de un sistema rudimentario de usuarios, productos, sus categorías y las ventas de los mismos.

**Versión**: 0.4

## Contacto

**Nombre**: Andres Padilla  
**URL**: [jorgeandrespadilla.com.ar](https://jorgeandrespadilla.com.ar)  
**Email**: [jandrespadilla@gmail.com](mailto:jandrespadilla@gmail.com)

## Servidor

`http://localhost:8080`

## Documentación

Esta documentación describe las API disponibles para el sistema de venta de productos.

## Autenticación

Actualmente la API no requiere autenticación para su uso.

## Tags

- **Gestión de items de las facturas**
- **Cabecera de las facturas realizadas**
- **Gestión de Usuarios**
- **Gestión de Productos**
- **Gestión de Categorías**

## Endpoints Disponibles

### Usuarios

**`/api/usuarios`**

- **GET**: Obtiene la lista de todos los usuarios.
- **POST**: Agregar un nuevo usuario.
- **DELETE**: Eliminar un usuario (recibe id de usuario por parámetro).

**`/api/usuarios/{id}`**

- **GET**: Obtener un usuario por su ID.
- **PUT**: Editar un usuario (recibe id de usuario por parámetro).

### Productos

**`/api/productos`**

- **GET**: Obtener la lista de todos los productos.
- **POST**: Agregar un nuevo producto.

**`/api/productos/{id}`**

- **GET**: Obtener un producto por su ID.
- **PUT**: Editar un producto (recibe id de producto por parámetro).
- **DELETE**: Eliminar un producto (recibe id de producto por parámetro).

**`/api/productos/asignarcategoria`**

- **POST**: Asignar una categoría al producto.

### Categorías

**`/api/categorias`**

- **GET**: Obtener la lista de todas las categorías.
- **POST**: Agregar una nueva categoría.

**`/api/categorias/{id}`**

- **GET**: Obtener una categoría por su ID.
- **PUT**: Editar una categoría (recibe id de categoría por parámetro).
- **DELETE**: Eliminar una categoría (recibe id de categoría por parámetro).

### Facturas

**`/api/ventacabecera`**

- **GET**: Obtener la lista de todas las facturas.
- **POST**: Agregar una nueva factura.

**`/api/ventacabecera/{id}`**

- **GET**: Obtener una factura por su ID.
- **PUT**: Editar una factura (recibe id de factura por parámetro).
- **DELETE**: Eliminar una factura (recibe id de factura por parámetro).

### Items de Facturas

**`/api/vent
