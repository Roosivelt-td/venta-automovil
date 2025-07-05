# Actualización Automática de Stock en Ventas

## Descripción del Problema
Cuando se registraba una venta, el stock de autos disponibles no se actualizaba automáticamente, lo que podía llevar a inconsistencias en el inventario.

## Solución Implementada

### Backend (Java Spring Boot)

#### 1. Nuevo Método en AutoService
- **Archivo**: `src/main/java/automoviles/service/AutoService.java`
- **Método agregado**: `void actualizarStock(Long idAuto, Integer cantidadVendida)`

#### 2. Implementación en AutoServiceImpl
- **Archivo**: `src/main/java/automoviles/service/impl/AutoServiceImpl.java`
- **Funcionalidad**:
  - Verifica que el auto existe
  - Valida que hay stock suficiente
  - Reduce el stock en la cantidad vendida
  - Cambia el estado a "Vendido" si el stock llega a 0
  - Guarda los cambios en la base de datos

#### 3. Modificación en VentaServiceImpl
- **Archivo**: `src/main/java/automoviles/service/impl/VentaServiceImpl.java`
- **Cambios realizados**:
  - Agregada inyección de `AutoService`
  - En `crearVenta()`: Se actualiza el stock después de guardar la venta
  - En `actualizarVenta()`: Se restaura el stock del auto anterior y se actualiza el del nuevo auto
  - En `eliminarVenta()`: Se restaura el stock cuando se elimina una venta

#### 4. Nuevo Endpoint en AutoController
- **Archivo**: `src/main/java/automoviles/controller/AutoController.java`
- **Endpoint agregado**: `PUT /api/autos/{id}/stock?cantidad={cantidad}`
- **Propósito**: Permite actualizar el stock de un auto específico

### Frontend (Angular)

#### 1. Mejoras en el Componente de Registro de Ventas
- **Archivo**: `Frontend/src/app/components/dashboard/ventas/registrar-venta/registrar-venta.html`
- **Cambios**:
  - Se muestra el stock disponible en el selector de autos
  - Se deshabilitan autos sin stock disponible
  - Se agrega texto informativo sobre stock disponible

#### 2. Validaciones en el Componente TypeScript
- **Archivo**: `Frontend/src/app/components/dashboard/ventas/registrar-venta/registrar-venta.ts`
- **Mejoras**:
  - Filtrado de autos con stock > 0
  - Validación adicional antes de registrar la venta
  - Recarga automática de la lista de autos después de una venta exitosa
  - Mensaje de confirmación que incluye información sobre la actualización del stock

#### 3. Nuevo Método en AutoService del Frontend
- **Archivo**: `Frontend/src/app/services/auto.service.ts`
- **Método agregado**: `actualizarStock(id: number, cantidad: number)`
- **Propósito**: Permite actualizar el stock desde el frontend si es necesario

## Flujo de Funcionamiento

### Al Registrar una Venta:
1. El usuario selecciona un auto con stock disponible
2. Se valida que el auto tenga stock > 0
3. Se registra la venta en la base de datos
4. **Automáticamente** se reduce el stock del auto en 1
5. Si el stock llega a 0, el estado cambia a "Vendido"
6. Se muestra confirmación al usuario
7. Se actualiza la lista de autos disponibles

### Al Actualizar una Venta:
1. Se restaura el stock del auto anterior (+1)
2. Se actualiza la venta con el nuevo auto
3. Se reduce el stock del nuevo auto (-1)

### Al Eliminar una Venta:
1. Se restaura el stock del auto (+1)
2. Se elimina la venta de la base de datos

## Beneficios

1. **Consistencia de Datos**: El stock siempre refleja las ventas reales
2. **Prevención de Errores**: No se pueden vender autos sin stock
3. **Automatización**: No requiere intervención manual para actualizar stock
4. **Trazabilidad**: Se mantiene un registro claro de las transacciones
5. **Experiencia de Usuario**: Interfaz clara que muestra stock disponible

## Validaciones Implementadas

- **Stock Insuficiente**: Se verifica que haya stock disponible antes de vender
- **Auto No Encontrado**: Se valida que el auto existe en la base de datos
- **Stock Negativo**: Se previene que el stock sea menor a 0
- **Estado Automático**: El estado cambia automáticamente según el stock

## Archivos Modificados

### Backend:
- `src/main/java/automoviles/service/AutoService.java`
- `src/main/java/automoviles/service/impl/AutoServiceImpl.java`
- `src/main/java/automoviles/service/impl/VentaServiceImpl.java`
- `src/main/java/automoviles/controller/AutoController.java`

### Frontend:
- `Frontend/src/app/components/dashboard/ventas/registrar-venta/registrar-venta.html`
- `Frontend/src/app/components/dashboard/ventas/registrar-venta/registrar-venta.ts`
- `Frontend/src/app/services/auto.service.ts`

## Pruebas Recomendadas

1. **Registrar una venta** y verificar que el stock se reduce
2. **Intentar vender un auto sin stock** y verificar que se bloquea
3. **Actualizar una venta** y verificar que el stock se maneja correctamente
4. **Eliminar una venta** y verificar que el stock se restaura
5. **Verificar que el estado cambia** cuando el stock llega a 0 