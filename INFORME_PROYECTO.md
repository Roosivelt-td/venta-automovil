# Informe de Desarrollo de Sistemas

## Portada
- **Nombre del proyecto:** Sistema de Gestión de Venta de Automóviles
- **Nombre del sistema:** Venta de Automóviles
- **Nombre del desarrollador o equipo:** [Completar]
- **Institución o empresa:** [Completar]
- **Fecha:** [Completar]
- **Versión del documento:** 1.0

---

## Resumen Ejecutivo
El sistema de gestión de venta de automóviles es una solución integral para la administración de ventas, inventario, clientes, proveedores, pagos y reembolsos en una empresa dedicada a la comercialización de vehículos. Su propósito es digitalizar y optimizar los procesos de venta, permitiendo un control eficiente y seguro de la información, facilitando la toma de decisiones y mejorando la experiencia tanto del usuario interno como del cliente final. Se espera como resultado una mayor eficiencia operativa, reducción de errores y mejor trazabilidad de las operaciones.

---

## Introducción
### Objetivo del informe
Documentar el proceso de desarrollo, análisis, diseño, implementación y pruebas del sistema de gestión de venta de automóviles.

### Alcance del documento
Este informe abarca desde la concepción del sistema hasta la implementación de sus principales funcionalidades, incluyendo análisis, diseño, pruebas y recomendaciones.

### Breve descripción del sistema a desarrollar
El sistema permite gestionar el inventario de autos, registrar ventas, administrar clientes, proveedores, pagos y reembolsos, así como generar reportes y dashboards para el seguimiento de la operación. Incluye autenticación de usuarios, control de stock, gestión de pagos y reembolsos, y funcionalidades avanzadas de búsqueda y filtrado.

---

## Justificación
### ¿Por qué es necesario este sistema?
Actualmente, muchas empresas del sector automotriz gestionan sus ventas y stock de manera manual o con herramientas poco integradas, lo que genera errores, pérdida de información y dificulta la toma de decisiones. La digitalización permite mejorar la eficiencia, reducir errores y centralizar la información.

### Situación actual y problemas detectados
- Procesos manuales propensos a errores.
- Dificultad para acceder a información en tiempo real.
- Falta de integración entre áreas (ventas, inventario, pagos, etc.).
- Imposibilidad de obtener reportes y estadísticas de manera ágil.

### Beneficios del sistema propuesto
- Centralización de la información.
- Automatización de procesos.
- Mejora en la trazabilidad y control de las operaciones.
- Acceso a reportes y dashboards en tiempo real.
- Reducción de errores humanos y mejora en la toma de decisiones.

---

## Objetivos
### Objetivo general
Desarrollar un sistema web para la gestión integral de ventas de automóviles, que permita administrar inventario, clientes, proveedores, pagos y reembolsos de manera eficiente y segura.

### Objetivos específicos
- Digitalizar el proceso de registro y gestión de ventas.
- Facilitar la administración de inventario de autos.
- Gestionar información de clientes y proveedores.
- Automatizar el registro de pagos y reembolsos.
- Proveer reportes y dashboards para la toma de decisiones.
- Implementar mecanismos de seguridad y control de acceso.
- Mejorar la experiencia de usuario con una interfaz moderna y responsiva.

---

## Metodología de Desarrollo
### Modelo de desarrollo usado
[Completar: Ejemplo, Scrum, Cascada, etc.]

### Etapas del proyecto
- Análisis de requerimientos
- Diseño del sistema
- Implementación
- Pruebas
- Despliegue
- Mantenimiento y mejoras

### Herramientas utilizadas
- **Frontend:** Angular
- **Backend:** Java Spring Boot
- **Base de datos:** MySQL o PostgreSQL (según despliegue)
- **Control de versiones:** Git
- **Despliegue:** Vercel (frontend), Railway/Heroku/Render (backend)

---

## Análisis del Sistema
### Requisitos del Sistema
#### Requisitos funcionales
- Gestión de autos (alta, baja, modificación, consulta)
- Registro y gestión de ventas
- Administración de clientes y proveedores
- Registro de pagos y reembolsos
- Generación de reportes y dashboards
- Búsqueda avanzada de ventas y autos
- Control de stock automático
- Autenticación y autorización de usuarios

#### Requisitos no funcionales
- Seguridad en el acceso (autenticación JWT, roles)
- Interfaz amigable y responsiva
- Disponibilidad y rendimiento adecuados
- Escalabilidad y mantenibilidad
- Soporte multiplataforma (web, móvil)

### Casos de Uso Principales
- **Gestión de Autos:** Registrar, editar, eliminar y consultar autos. Controlar stock y estado.
- **Gestión de Ventas:** Registrar ventas, asociar cliente, auto y usuario vendedor. Actualizar stock automáticamente.
- **Gestión de Clientes:** Registrar, editar, eliminar y consultar clientes. Búsqueda inteligente por nombre, DNI, correo, teléfono.
- **Gestión de Proveedores:** Registrar, editar, eliminar y consultar proveedores.
- **Gestión de Compras:** Registrar compras de autos a proveedores.
- **Gestión de Pagos y Reembolsos:** Registrar pagos asociados a ventas y gestionar reembolsos.
- **Dashboard:** Visualizar estadísticas clave, actividad reciente, alertas de stock, top autos vendidos, resumen financiero.
- **Autenticación:** Registro, login, recuperación y restablecimiento de contraseña.

#### Ejemplo de Flujo de Caso de Uso: Registrar Venta
1. El usuario accede al formulario de registro de ventas.
2. Selecciona cliente (o lo registra si es nuevo).
3. Selecciona auto disponible (stock > 0).
4. Ingresa datos de la venta (precio, método de pago, observaciones).
5. El sistema valida y registra la venta, actualiza el stock y registra el pago.
6. Se muestra confirmación y se actualizan las estadísticas del dashboard.

---

## Modelo de Dominio
El sistema está basado en entidades principales que representan los objetos del negocio:

- **Usuario:** Representa a los usuarios internos del sistema (administradores, vendedores). Incluye datos personales, credenciales y rol.
- **Cliente:** Persona que realiza la compra de un auto. Incluye nombre, DNI, teléfono, dirección y correo.
- **Auto:** Vehículo en inventario. Incluye marca, modelo, año, color, kilometraje, tipo de combustible, transmisión, cilindrada, potencia, stock, precio, descripción, imagen y estado.
- **Proveedor:** Empresa o persona que provee autos para la venta.
- **Compra:** Registro de adquisición de un auto a un proveedor.
- **Venta:** Registro de la venta de un auto a un cliente. Incluye referencias a cliente, auto, usuario vendedor, fecha, precio, método de pago y observaciones.
- **Pago:** Registro de pago asociado a una venta.
- **Reembolso:** Registro de devolución de dinero asociado a una venta.

Las relaciones principales son:
- Un **usuario** puede registrar muchas ventas.
- Un **cliente** puede tener muchas ventas.
- Un **auto** puede estar asociado a muchas ventas y compras.
- Un **proveedor** puede tener muchas compras.
- Una **venta** puede tener un pago y un reembolso asociado.

---

## Diseño del Sistema
### Arquitectura General
El sistema sigue una arquitectura cliente-servidor, donde el frontend (Angular) consume servicios RESTful expuestos por el backend (Spring Boot). La base de datos relacional almacena la información persistente.

#### Componentes principales:
- **Frontend Angular:** Interfaz de usuario, validaciones, navegación, consumo de APIs, dashboards y reportes.
- **Backend Spring Boot:** Lógica de negocio, controladores REST, servicios, seguridad, acceso a base de datos.
- **Base de datos relacional:** MySQL o PostgreSQL, con tablas para usuarios, autos, clientes, proveedores, ventas, compras, pagos y reembolsos.

#### Diagrama de componentes (descripción textual):
- El usuario interactúa con la interfaz Angular.
- Angular realiza peticiones HTTP a los endpoints del backend.
- El backend procesa la lógica, accede a la base de datos y responde con datos o confirmaciones.
- El frontend actualiza la interfaz y muestra mensajes, estadísticas y reportes.

### Interfaz de Usuario
- Diseño responsivo y moderno, con sidebar de navegación, header y cards de estadísticas.
- Formularios con validaciones en tiempo real y mensajes claros de error/éxito.
- Tablas de gestión con filtros, búsqueda inteligente y acciones rápidas.
- Dashboard con tarjetas de estadísticas, actividad reciente, alertas y ranking de autos vendidos.
- Modalidad oscura y animaciones para mejorar la experiencia de usuario.

### Base de Datos
- Modelo entidad-relación con tablas para usuarios, autos, clientes, proveedores, ventas, compras, pagos y reembolsos.
- Integridad referencial mediante claves foráneas.
- Scripts SQL disponibles para creación y actualización de tablas.

---

## Implementación
### Tecnologías utilizadas
- **Frontend:** Angular, TypeScript, HTML5, CSS3, Bootstrap/Tailwind
- **Backend:** Java 17+, Spring Boot, Spring Security, JPA/Hibernate
- **Base de datos:** MySQL o PostgreSQL
- **Herramientas adicionales:** Git, Vercel, Railway, Heroku, Render

### Estructura del código
- Separación clara entre frontend y backend.
- Backend organizado en controladores, servicios, repositorios, modelos y DTOs.
- Frontend organizado en módulos, componentes, servicios y rutas.
- Uso de buenas prácticas de desarrollo, modularidad y reutilización de código.

### Funcionalidades implementadas
- Gestión completa de autos, ventas, clientes, proveedores, compras, pagos y reembolsos.
- Autenticación JWT y control de acceso por roles.
- Dashboard con estadísticas en tiempo real y alertas automáticas.
- Búsqueda avanzada y filtrado en tablas.
- Validaciones robustas en frontend y backend.
- Manejo de errores y mensajes claros al usuario.
- Actualización automática de stock y sincronización de datos.

### Capturas del sistema
- [Por completar: Incluir capturas de pantalla de las principales vistas]

---

## Pruebas
### Tipos de pruebas realizadas
- Pruebas unitarias en servicios y componentes clave.
- Pruebas de integración entre frontend y backend.
- Pruebas funcionales de los flujos principales (registro de ventas, gestión de autos, etc.).
- Pruebas de validación de formularios y manejo de errores.

### Plan de pruebas y resultados
- Verificación de la actualización automática de stock al registrar ventas.
- Pruebas de búsqueda y filtrado en tablas.
- Pruebas de seguridad en endpoints protegidos.
- Pruebas de conectividad y despliegue en entornos locales y en la nube.
- [Por completar: Detalles de resultados y métricas]

### Herramientas de testing
- Herramientas de testing de Angular y Spring Boot.
- Pruebas manuales y automatizadas.

---

## Resultados y Evaluación
### Resultados obtenidos
- El sistema permite gestionar de manera eficiente todas las áreas clave del proceso de venta de automóviles.
- Se logró la integración entre frontend y backend, con sincronización de datos y actualización en tiempo real.
- El dashboard proporciona información relevante y actualizada para la toma de decisiones.

### Comparación con los objetivos
- Se cumplieron los objetivos funcionales y no funcionales principales.
- El sistema es escalable, seguro y fácil de usar.

### Métricas de evaluación
- [Por completar: Métricas de rendimiento, satisfacción de usuario, etc.]

---

## Conclusiones
### Lecciones aprendidas
- La importancia de una buena planificación y análisis previo.
- El valor de la modularidad y la reutilización de componentes.
- La necesidad de pruebas exhaustivas para garantizar la calidad.

### Puntos fuertes del sistema
- Modularidad, seguridad, facilidad de uso, interfaz moderna y responsiva.
- Automatización de procesos y reducción de errores.

### Posibles mejoras futuras
- Integración con sistemas externos (facturación, CRM, etc.).
- Mejoras en la interfaz de usuario y experiencia móvil.
- Implementación de reportes avanzados y analítica.
- Soporte para múltiples sucursales o empresas.

---

## Recomendaciones
### Para mantenimiento
- Documentar el código y procesos.
- Realizar backups periódicos.
- Mantener actualizadas las dependencias y librerías.

### Futuras versiones
- [Por completar: Sugerencias específicas para nuevas funcionalidades]

### Usuarios o clientes
- [Por completar: Listado de usuarios finales o clientes del sistema]

---

## Anexos
- Código fuente
- Manual de usuario
- Manual técnico
- Diagramas UML
- Bibliografía 