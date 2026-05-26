# GameHub - Arquitectura de Microservicios E-Commerce

Este proyecto consiste en una solución de backend distribuida para una plataforma de e-commerce de videojuegos llamada "GameHub".
La arquitectura está compuesta por 10 microservicios independientes.

 Integrantes
* Sebastián Ignacio Sepúlveda Moraga
* BENJAMIN IGNACIO BRAVO CUETO

 Institución
* Duoc UC Valparaíso

 Microservicios Implementados
1. msvc-categories: Gestión y clasificación de categorías de videojuegos
2. msvc-products: Catálogo principal de productos y juegos
3. msvc-inventory: Control de bodega y stock físico de unidades
4. msvc-users: Registro y administración de datos demográficos de clientes
5. msvc-auth: Servicio de seguridad, login y encriptación de credenciales
6. msvc-order: Motor transaccional principal que coordina compras usando OpenFeign
7. msvc-payment: Simulación de pasarela de pagos integrada para aprobación de transacciones
8. msvc-shipping: Módulo logístico para la preparación y despacho de pedidos
9. msvc-promotion: Validación y aplicación de cupones de descuento vigentes
10. msvc-notification: Historial y registro de alertas de eventos del sistema

 Tecnologías Utilizadas
* Java 21/25 / Spring Boot
* Spring Cloud OpenFeign (Comunicación Síncrona Inter-servicio)
* Spring Data JPA / Hibernate (Persistencia Real)
* MySQL (Bases de datos independientes por servicio)
* Lombok / SLF4J (Logs estructurados)
* Jakarta Validation (Bean Validation JSR 380)

 Pasos para la Ejecución
1. Clonar el repositorio.
2. Configurar las credenciales de MySQL en el archivo application.properties de cada microservicio si difieren de root.
3. Crear las bases de datos correspondientes en MySQL (el sistema creará las tablas automáticamente).
4. Levantar cada microservicio ejecutando su clase principal `MsvcServicioApplication.java`.
