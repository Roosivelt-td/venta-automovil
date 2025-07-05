# Solución al Error "Auto no encontrado"

## Problema Identificado
El error "Auto no encontrado" se debe a que la base de datos no tiene el campo `stock` en la tabla `autos`, o los autos no tienen stock asignado.

## Solución

### 1. Actualizar la Base de Datos
Ejecutar el script `verificar_base_datos.sql` en tu base de datos MySQL:

```sql
-- Verificar estructura actual
DESCRIBE autos;

-- Agregar campo stock si no existe
ALTER TABLE autos ADD COLUMN stock INT NOT NULL DEFAULT 1 AFTER precio;

-- Actualizar stock de autos existentes
UPDATE autos SET stock = 1 WHERE stock IS NULL;

-- Insertar datos de ejemplo
INSERT INTO autos (marca, modelo, anio, color, kilometraje, tipo_combustible, transmision, cilindrada, potencia, stock, precio, descripcion, imagen_url, estado) VALUES
('Toyota', 'Corolla', 2023, 'Blanco', 50000, 'Gasolina', 'Automática', 2000, 150, 5, 25000.00, 'Toyota Corolla 2023', 'https://ejemplo.com/corolla.jpg', 'Disponible'),
('Honda', 'Civic', 2022, 'Negro', 35000, 'Gasolina', 'Manual', 1800, 140, 3, 28000.00, 'Honda Civic 2022', 'https://ejemplo.com/civic.jpg', 'Disponible');
```

### 2. Verificar que el Backend esté Ejecutándose
```bash
# Verificar que el servidor esté corriendo en puerto 8080
curl http://localhost:8080/api/autos/
```

### 3. Verificar que el Frontend esté Ejecutándose
```bash
# Verificar que Angular esté corriendo en puerto 4200
curl http://localhost:4200
```

### 4. Probar la Funcionalidad
1. Ir a `http://localhost:4200`
2. Navegar a "Ventas" → "Registrar Venta"
3. Abrir la consola del navegador (F12)
4. Verificar que aparezcan los logs de autos cargados
5. Registrar un cliente
6. Seleccionar un auto
7. Completar el formulario y registrar la venta

### 5. Logs a Verificar
En la consola del navegador deben aparecer:
```
Todos los autos cargados: [...]
Autos disponibles (todos): [...]
Cantidad de autos: X
Auto ID 1: Marca Modelo - Stock: X
```

### 6. Restaurar el Filtro de Stock (Opcional)
Una vez que funcione, restaurar el filtro en `registrar-venta.ts`:

```typescript
// Cambiar de:
this.autos = autos;

// A:
this.autos = autos.filter(auto => auto.stock > 0);
```

## Cambios Realizados

### Backend:
- ✅ Agregado método `actualizarStock` en `AutoService`
- ✅ Implementado en `AutoServiceImpl` con validaciones
- ✅ Modificado `VentaServiceImpl` para actualizar stock automáticamente
- ✅ Agregado endpoint en `AutoController`

### Frontend:
- ✅ Mejorado formulario de registro de ventas
- ✅ Agregadas validaciones de stock
- ✅ Mejorado manejo de tipos de datos
- ✅ Agregados logs para debug

## Estado Actual
- ✅ Funcionalidad implementada
- ✅ Validaciones agregadas
- ⚠️ Necesita actualización de base de datos
- ⚠️ Filtro de stock temporalmente deshabilitado para debug 