# Configuración de Seguridad del Backend

## Resumen

Se ha actualizado la configuración de seguridad para permitir el acceso público a todos los endpoints necesarios para el funcionamiento del sistema de gestión de venta de autos.

## Archivo Modificado

**Archivo:** `src/main/java/automoviles/auth/security/SecurityConfig.java`

## Endpoints Públicos Configurados

### 1. Autenticación
- `/api/auth/**` - Endpoints de autenticación (login, registro, etc.)

### 2. Gestión de Usuarios
- `/api/usuarios/todos` - Listar todos los usuarios
- `/api/usuarios/create` - Crear nuevo usuario
- `/api/usuarios/users-disponibles` - Usuarios disponibles
- `/api/usuarios/update/**` - Actualizar usuario
- `/api/usuarios/delete/**` - Eliminar usuario
- `/api/usuarios/{id}` - Obtener usuario por ID

### 3. Gestión de Autos
- `/api/autos/**` - Todos los endpoints de autos

### 4. Gestión de Proveedores
- `/api/proveedores/**` - Todos los endpoints de proveedores

### 5. Gestión de Clientes
- `/api/clientes/**` - Todos los endpoints de clientes

### 6. Gestión de Ventas
- `/api/ventas/**` - Todos los endpoints de ventas

### 7. Gestión de Pagos
- `/api/pagos/**` - Todos los endpoints de pagos

### 8. Gestión de Compras
- `/api/compras/**` - Todos los endpoints de compras

### 9. Gestión de Reembolsos
- `/api/reembolsos/**` - Todos los endpoints de reembolsos

## Configuración CORS

El sistema está configurado para permitir peticiones desde:
- **Origen:** `http://localhost:4200` (Frontend Angular)
- **Métodos:** Todos los métodos HTTP
- **Headers:** Todos los headers
- **Credentials:** Habilitado

## Configuración de Seguridad

### Características Implementadas:
- ✅ **JWT Authentication**: Autenticación basada en tokens
- ✅ **CORS habilitado**: Para comunicación frontend-backend
- ✅ **CSRF deshabilitado**: Para APIs REST
- ✅ **Sesiones stateless**: Sin estado de sesión
- ✅ **Endpoints públicos**: Para funcionalidades básicas
- ✅ **Endpoints protegidos**: Requieren autenticación

### Estructura de Seguridad:
```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/auth/**").permitAll()
    .requestMatchers("/api/usuarios/**").permitAll()
    .requestMatchers("/api/autos/**").permitAll()
    .requestMatchers("/api/proveedores/**").permitAll()
    .requestMatchers("/api/clientes/**").permitAll()
    .requestMatchers("/api/ventas/**").permitAll()
    .requestMatchers("/api/pagos/**").permitAll()
    .requestMatchers("/api/compras/**").permitAll()
    .requestMatchers("/api/reembolsos/**").permitAll()
    .anyRequest().authenticated()
)
```

## Endpoints Disponibles por Módulo

### Autos
- `GET /api/autos/todos` - Listar todos los autos
- `GET /api/autos/{id}` - Obtener auto por ID
- `POST /api/autos/create` - Crear nuevo auto
- `PUT /api/autos/update/{id}` - Actualizar auto
- `DELETE /api/autos/delete/{id}` - Eliminar auto
- `PUT /api/autos/stock/{id}` - Actualizar stock

### Proveedores
- `GET /api/proveedores/todos` - Listar todos los proveedores
- `GET /api/proveedores/{id}` - Obtener proveedor por ID
- `POST /api/proveedores/create` - Crear nuevo proveedor
- `PUT /api/proveedores/update/{id}` - Actualizar proveedor
- `DELETE /api/proveedores/delete/{id}` - Eliminar proveedor

### Clientes
- `GET /api/clientes/todos` - Listar todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `POST /api/clientes/create` - Crear nuevo cliente
- `PUT /api/clientes/update/{id}` - Actualizar cliente
- `DELETE /api/clientes/delete/{id}` - Eliminar cliente

### Ventas
- `GET /api/ventas/todos` - Listar todas las ventas
- `GET /api/ventas/{id}` - Obtener venta por ID
- `POST /api/ventas/create` - Crear nueva venta
- `PUT /api/ventas/update/{id}` - Actualizar venta
- `DELETE /api/ventas/delete/{id}` - Eliminar venta
- `GET /api/ventas/buscar/**` - Búsquedas avanzadas

### Pagos
- `GET /api/pagos/todos` - Listar todos los pagos
- `GET /api/pagos/{id}` - Obtener pago por ID
- `POST /api/pagos/create` - Crear nuevo pago
- `PUT /api/pagos/update/{id}` - Actualizar pago
- `DELETE /api/pagos/delete/{id}` - Eliminar pago

### Compras
- `GET /api/compras/todos` - Listar todas las compras
- `GET /api/compras/{id}` - Obtener compra por ID
- `POST /api/compras/create` - Crear nueva compra
- `PUT /api/compras/update/{id}` - Actualizar compra
- `DELETE /api/compras/delete/{id}` - Eliminar compra

### Reembolsos
- `GET /api/reembolsos/todos` - Listar todos los reembolsos
- `GET /api/reembolsos/{id}` - Obtener reembolso por ID
- `POST /api/reembolsos/create` - Crear nuevo reembolso
- `PUT /api/reembolsos/update/{id}` - Actualizar reembolso
- `DELETE /api/reembolsos/delete/{id}` - Eliminar reembolso

## Notas Importantes

### Seguridad Temporal
⚠️ **ADVERTENCIA**: Esta configuración permite acceso público a todos los endpoints para facilitar el desarrollo y pruebas. En producción, se debe implementar un sistema de autenticación y autorización más robusto.

### Recomendaciones para Producción:
1. **Implementar roles de usuario**: ADMIN, VENDEDOR, CLIENTE, etc.
2. **Restringir endpoints por rol**: No todos los usuarios deben poder acceder a todo
3. **Validar permisos**: Verificar que cada usuario solo pueda acceder a sus datos
4. **Implementar rate limiting**: Limitar el número de peticiones por usuario
5. **Logging de auditoría**: Registrar todas las acciones importantes
6. **HTTPS obligatorio**: Encriptar todas las comunicaciones

### Configuración de Desarrollo vs Producción:
```java
// Desarrollo (actual)
.requestMatchers("/api/**").permitAll()

// Producción (recomendado)
.requestMatchers("/api/auth/**").permitAll()
.requestMatchers("/api/public/**").permitAll()
.requestMatchers("/api/admin/**").hasRole("ADMIN")
.requestMatchers("/api/vendedor/**").hasRole("VENDEDOR")
.requestMatchers("/api/cliente/**").hasRole("CLIENTE")
```

## Pruebas de Conectividad

Para verificar que los endpoints funcionan correctamente:

1. **Probar endpoint de autos**:
   ```bash
   curl http://localhost:8080/api/autos/todos
   ```

2. **Probar endpoint de pagos**:
   ```bash
   curl http://localhost:8080/api/pagos/todos
   ```

3. **Probar endpoint de ventas**:
   ```bash
   curl http://localhost:8080/api/ventas/todos
   ```

## Solución de Problemas

### Error 403 Forbidden
- Verificar que el endpoint esté incluido en la configuración de seguridad
- Revisar que el patrón de URL coincida exactamente
- Confirmar que el servidor se haya reiniciado después de los cambios

### Error CORS
- Verificar que el origen del frontend esté en la configuración CORS
- Confirmar que las credenciales estén configuradas correctamente
- Revisar que los headers estén permitidos

### Error de Autenticación
- Verificar que el token JWT sea válido
- Confirmar que el token no haya expirado
- Revisar que el usuario exista en la base de datos 