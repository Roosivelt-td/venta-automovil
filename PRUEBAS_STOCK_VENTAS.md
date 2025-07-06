# Instrucciones para Probar la Actualización de Stock en Ventas

## Pasos para Probar

### 1. Verificar que las aplicaciones estén ejecutándose
- **Backend**: Debe estar corriendo en `http://localhost:8080`
- **Frontend**: Debe estar corriendo en `http://localhost:4200`

### 2. Verificar datos en la base de datos
Antes de probar, asegúrate de que:
- Existan autos en la base de datos
- Los autos tengan stock > 0
- Existan clientes registrados

### 3. Probar el registro de ventas

#### Paso 1: Ir al formulario de registro de ventas
1. Abrir el navegador en `http://localhost:4200`
2. Navegar a "Ventas" → "Registrar Venta"

#### Paso 2: Verificar que se cargan los autos
1. Abrir la consola del navegador (F12)
2. Verificar que aparezcan los logs:
   ```
   Todos los autos cargados: [...]
   Autos disponibles (todos): [...]
   Cantidad de autos: X
   Auto ID 1: Marca Modelo - Stock: X
   ```

#### Paso 3: Registrar un cliente
1. Buscar un cliente por DNI o crear uno nuevo
2. Verificar que el cliente se selecciona correctamente

#### Paso 4: Seleccionar un auto
1. En el dropdown de autos, verificar que aparezcan todos los autos
2. Seleccionar un auto con stock > 0
3. Completar los demás campos del formulario

#### Paso 5: Registrar la venta
1. Hacer clic en "Registrar Venta"
2. Verificar en la consola que aparezcan los logs de validación
3. Verificar que la venta se registra exitosamente

### 4. Verificar que el stock se actualiza

#### Paso 1: Verificar en la gestión de autos
1. Ir a "Autos" → "Gestionar Autos"
2. Verificar que el stock del auto vendido se redujo en 1

#### Paso 2: Verificar en el formulario de ventas
1. Volver a "Ventas" → "Registrar Venta"
2. Verificar que el auto vendido ya no aparece en la lista (si su stock llegó a 0)

### 5. Probar casos de error

#### Caso 1: Intentar vender un auto sin stock
1. Intentar seleccionar un auto con stock = 0
2. Verificar que se muestra un mensaje de error

#### Caso 2: Intentar registrar sin seleccionar auto
1. Completar el formulario sin seleccionar auto
2. Verificar que se muestra un mensaje de error

## Logs a Verificar en la Consola

### Al cargar autos:
```
Todos los autos cargados: [...]
Autos disponibles (todos): [...]
Cantidad de autos: X
Auto ID 1: Marca Modelo - Stock: X
```

### Al registrar venta:
```
Estado del formulario de venta:
Válido: true
Valores: {...}
Auto ID del formulario: X Tipo: string
Autos disponibles: [...]
Auto ID convertido a number: X
Auto seleccionado encontrado: {...}
Datos de venta a enviar: {...}
```

### En el backend (logs del servidor):
```
INFO: Stock actualizado para auto ID X. Stock anterior: Y, Stock nuevo: Z
```

## Posibles Problemas y Soluciones

### Problema 1: "Auto no encontrado"
**Causa**: Problema de tipos de datos entre string y number
**Solución**: Ya implementada la conversión de tipos

### Problema 2: No aparecen autos en el dropdown
**Causa**: Filtro de stock demasiado restrictivo
**Solución**: Temporalmente deshabilitado el filtro para debug

### Problema 3: Error al conectar con el backend
**Causa**: Servidor no está ejecutándose
**Solución**: Verificar que el backend esté corriendo en puerto 8080

### Problema 4: Stock no se actualiza
**Causa**: Error en el servicio de actualización de stock
**Solución**: Verificar logs del backend y la implementación del método

## Comandos Útiles

### Verificar que el backend esté corriendo:
```bash
curl http://localhost:8080/api/autos/
```

### Verificar que el frontend esté corriendo:
```bash
curl http://localhost:4200
```

### Ver logs del backend:
```bash
# En la terminal donde está corriendo el backend
# Los logs aparecerán automáticamente
```

## Restaurar el Filtro de Stock

Una vez que se confirme que todo funciona correctamente, restaurar el filtro en `registrar-venta.ts`:

```typescript
// Cambiar de:
this.autos = autos;

// A:
this.autos = autos.filter(auto => auto.stock > 0);
``` 