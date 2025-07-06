# Funcionalidad de Búsqueda de Ventas

## Descripción
Se ha implementado una funcionalidad completa de búsqueda de ventas que permite filtrar y encontrar ventas específicas basándose en múltiples criterios.

## Funcionalidades Implementadas

### Frontend (Angular)

#### 1. Búsqueda en Tiempo Real
- **Campo de búsqueda** que filtra automáticamente mientras el usuario escribe
- **Búsqueda en múltiples campos**:
  - ID de venta
  - Nombre del cliente
  - DNI del cliente
  - Marca del auto
  - Modelo del auto
  - Año del auto
  - Nombre del usuario
  - Fecha de venta
  - Precio de venta

#### 2. Estadísticas en Tiempo Real
- **Contador de ventas**: Muestra total vs filtradas
- **Ingresos totales**: Calcula ingresos de todas las ventas
- **Ingresos filtrados**: Calcula ingresos de las ventas filtradas
- **Promedio por venta**: Calcula el promedio de precio por venta

#### 3. Interfaz Mejorada
- **Botón de limpiar filtro**: Permite resetear la búsqueda
- **Información de resultados**: Muestra cuántas ventas se encontraron
- **Mensajes informativos**: Diferencia entre "no hay ventas" y "no se encontraron resultados"

### Backend (Java Spring Boot)

#### 1. Repositorio (VentaRepository)
```java
// Búsquedas específicas
List<Venta> findByClienteNombreContainingIgnoreCase(String nombreCliente);
List<Venta> findByAutoMarcaContainingIgnoreCaseOrAutoModeloContainingIgnoreCase(String marca, String modelo);
List<Venta> findByUsuarioNombreContainingIgnoreCase(String nombreUsuario);
List<Venta> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
List<Venta> findByPrecioVentaBetween(BigDecimal precioMin, BigDecimal precioMax);
```

#### 2. Servicio (VentaService)
- `buscarVentasPorCliente(String nombreCliente)`
- `buscarVentasPorAuto(String marca, String modelo)`
- `buscarVentasPorUsuario(String nombreUsuario)`
- `buscarVentasPorFecha(LocalDate fechaInicio, LocalDate fechaFin)`
- `buscarVentasPorPrecio(BigDecimal precioMin, BigDecimal precioMax)`
- `buscarVentasPorTermino(String termino)` - Búsqueda general

#### 3. Controlador (VentaController)
```java
GET /api/ventas/buscar/cliente/{nombreCliente}
GET /api/ventas/buscar/auto?marca={marca}&modelo={modelo}
GET /api/ventas/buscar/usuario/{nombreUsuario}
GET /api/ventas/buscar/fecha?fechaInicio={fecha}&fechaFin={fecha}
GET /api/ventas/buscar/precio?precioMin={min}&precioMax={max}
GET /api/ventas/buscar/termino/{termino}
```

## Cómo Usar la Búsqueda

### 1. Búsqueda General
- Escribe cualquier término en el campo de búsqueda
- El sistema buscará automáticamente en todos los campos
- Los resultados se actualizan en tiempo real

### 2. Ejemplos de Búsqueda
- **Por cliente**: "Juan", "Pérez"
- **Por auto**: "Toyota", "Corolla", "2023"
- **Por precio**: "25000", "50000"
- **Por fecha**: "15/12/2024"
- **Por ID**: "1", "25"

### 3. Búsquedas Específicas (API)
```bash
# Buscar ventas de un cliente específico
GET /api/ventas/buscar/cliente/Juan

# Buscar ventas de un auto específico
GET /api/ventas/buscar/auto?marca=Toyota&modelo=Corolla

# Buscar ventas por rango de fechas
GET /api/ventas/buscar/fecha?fechaInicio=2024-01-01&fechaFin=2024-12-31

# Buscar ventas por rango de precio
GET /api/ventas/buscar/precio?precioMin=20000&precioMax=50000
```

## Características Técnicas

### Frontend
- **Filtrado en tiempo real**: No requiere presionar botón de búsqueda
- **Búsqueda case-insensitive**: No distingue entre mayúsculas y minúsculas
- **Búsqueda parcial**: Encuentra coincidencias parciales
- **Manejo de errores**: Muestra mensajes informativos

### Backend
- **Búsquedas optimizadas**: Usa métodos específicos del repositorio
- **Búsqueda general**: Combina múltiples criterios en una sola consulta
- **Manejo de errores**: Retorna códigos HTTP apropiados
- **Validación de datos**: Verifica formatos de fecha y números

## Archivos Modificados

### Frontend:
- `Frontend/src/app/components/dashboard/ventas/ventas-list/ventas-list.ts`
- `Frontend/src/app/components/dashboard/ventas/ventas-list/ventas-list.html`
- `Frontend/src/app/services/venta.service.ts`

### Backend:
- `src/main/java/automoviles/repository/VentaRepository.java`
- `src/main/java/automoviles/service/VentaService.java`
- `src/main/java/automoviles/service/impl/VentaServiceImpl.java`
- `src/main/java/automoviles/controller/VentaController.java`

## Beneficios

1. **Búsqueda Rápida**: Encuentra ventas específicas en segundos
2. **Flexibilidad**: Múltiples criterios de búsqueda
3. **Experiencia de Usuario**: Interfaz intuitiva y responsiva
4. **Estadísticas en Tiempo Real**: Información actualizada automáticamente
5. **Escalabilidad**: Fácil de extender con nuevos criterios

## Próximas Mejoras

- Búsqueda por rangos de fechas en la interfaz
- Filtros avanzados con múltiples criterios
- Exportación de resultados de búsqueda
- Historial de búsquedas recientes
- Búsqueda por pagos asociados 