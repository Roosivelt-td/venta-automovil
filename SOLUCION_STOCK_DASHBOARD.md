# Solución al Problema del Stock en el Dashboard

## Problema Identificado

El contador de "Stock Disponible" en el dashboard está mostrando cero (0) cuando hay autos en la base de datos.

## Posibles Causas

### 1. Campo `stock` no existe en la base de datos
- La tabla `autos` puede no tener la columna `stock`
- Los autos existentes pueden no tener valores en el campo `stock`

### 2. Campo `stock` está en null/undefined
- Los autos pueden tener el campo `stock` pero con valores null
- El campo puede estar vacío en la base de datos

### 3. Problema en la API
- El endpoint `/api/autos/todos` puede no estar devolviendo el campo `stock`
- Puede haber un problema de mapeo en el backend

## Diagnóstico Implementado

Se han agregado logs de depuración en el dashboard para identificar el problema:

### Logs Agregados

```typescript
// Debug: Ver qué datos se están recibiendo
console.log('Autos recibidos:', autosArray);
console.log('Primer auto:', autosArray[0]);

// Debug: Ver el stock de cada auto
const stockDisponible = autosArray.reduce((sum, auto) => {
  const stock = auto.stock || 0;
  console.log(`Auto ${auto.marca} ${auto.modelo}: stock = ${stock}`);
  return sum + stock;
}, 0);

// Debug: Mostrar resultados de cálculos
console.log('Resultados de cálculos:');
console.log('- Total autos:', totalAutos);
console.log('- Stock disponible:', stockDisponible);
console.log('- Autos sin stock:', autosSinStock);
```

## Pasos para Verificar

### Paso 1: Verificar en el Navegador

1. **Abrir las herramientas de desarrollador** (F12)
2. **Ir a la pestaña Console**
3. **Navegar al dashboard**
4. **Revisar los logs** que aparecen en la consola

### Paso 2: Verificar la Base de Datos

```sql
-- Verificar si existe la columna stock
DESCRIBE autos;

-- Verificar los datos de autos
SELECT id, marca, modelo, anio, stock FROM autos;

-- Verificar autos sin stock
SELECT id, marca, modelo, anio, stock FROM autos WHERE stock IS NULL OR stock = 0;
```

### Paso 3: Verificar la API

```bash
# Probar el endpoint directamente
curl http://localhost:8080/api/autos/todos
```

## Soluciones Posibles

### Solución 1: Actualizar la Base de Datos

Si la columna `stock` no existe:

```sql
-- Agregar columna stock si no existe
ALTER TABLE autos ADD COLUMN stock INT DEFAULT 1;

-- Actualizar autos existentes con stock por defecto
UPDATE autos SET stock = 1 WHERE stock IS NULL;
```

### Solución 2: Actualizar Autos Existentes

Si la columna existe pero está vacía:

```sql
-- Actualizar todos los autos con stock por defecto
UPDATE autos SET stock = 1 WHERE stock IS NULL OR stock = 0;

-- O asignar stock específico por auto
UPDATE autos SET stock = 5 WHERE id = 1;
UPDATE autos SET stock = 3 WHERE id = 2;
-- ... etc
```

### Solución 3: Verificar el Backend

Si el problema está en la API, verificar:

1. **Modelo Auto.java**: Asegurar que tiene el campo `stock`
2. **Repository**: Verificar que la consulta incluye el campo `stock`
3. **Controller**: Verificar que devuelve todos los campos

### Solución 4: Código de Respuesta Temporal

Si necesitas una solución temporal, puedes modificar el cálculo:

```typescript
// Opción 1: Asignar stock por defecto si no existe
const stockDisponible = autosArray.reduce((sum, auto) => {
  const stock = auto.stock || 1; // Asignar 1 por defecto
  return sum + stock;
}, 0);

// Opción 2: Contar autos con stock > 0
const stockDisponible = autosArray.filter(auto => (auto.stock || 0) > 0).length;

// Opción 3: Usar totalAutos como stock disponible
const stockDisponible = totalAutos;
```

## Verificación de la Solución

### 1. Verificar en el Dashboard
- El contador "Stock Disponible" debe mostrar un número > 0
- Debe coincidir con la suma del stock de todos los autos

### 2. Verificar en la Base de Datos
```sql
-- Verificar que todos los autos tienen stock
SELECT COUNT(*) as total_autos, SUM(stock) as total_stock FROM autos;
```

### 3. Verificar en la API
```bash
# Verificar que la API devuelve el campo stock
curl http://localhost:8080/api/autos/todos | jq '.[0]'
```

## Cálculo Actual del Stock

El dashboard actualmente calcula el stock disponible como:

```typescript
const stockDisponible = autosArray.reduce((sum, auto) => {
  const stock = auto.stock || 0; // Si stock es null/undefined, usa 0
  return sum + stock;
}, 0);
```

Esto significa:
- **Si `auto.stock` existe**: usa ese valor
- **Si `auto.stock` es null/undefined**: usa 0
- **Resultado**: suma de todos los stocks

## Interpretación del Stock

### Stock Disponible vs Total de Autos

- **Total de Autos**: Número total de autos en la base de datos
- **Stock Disponible**: Suma del stock de todos los autos
- **Autos Sin Stock**: Autos con stock = 0

### Ejemplo

Si tienes 3 autos:
- Auto 1: stock = 2
- Auto 2: stock = 0 (sin stock)
- Auto 3: stock = 1

Resultado:
- **Total de Autos**: 3
- **Stock Disponible**: 3 (2+0+1)
- **Autos Sin Stock**: 1

## Próximos Pasos

1. **Revisar los logs** en la consola del navegador
2. **Verificar la base de datos** con los comandos SQL
3. **Aplicar la solución** correspondiente
4. **Verificar que funciona** en el dashboard
5. **Remover los logs de debug** una vez solucionado

## Notas Importantes

- El stock disponible es la **suma** del stock de todos los autos
- Si un auto tiene stock = 0, no contribuye al total
- Los logs de debug ayudarán a identificar exactamente qué está pasando
- La solución dependerá de si el problema está en la base de datos o en el código 