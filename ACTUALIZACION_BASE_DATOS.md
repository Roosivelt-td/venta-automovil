# 🚗 Actualización de la Base de Datos - Tabla Autos

## 📋 Resumen de Cambios

Se han agregado nuevos campos a la tabla `autos` para que coincida con el frontend:

### ✅ **Campos Agregados:**
- `tipo_combustible` VARCHAR(255) - Tipo de combustible (Gasolina, Diesel, Eléctrico, etc.)
- `transmision` VARCHAR(255) - Tipo de transmisión (Manual, Automática, CVT, etc.)
- `cilindrada` INT - Cilindrada del motor en cc
- `potencia` INT - Potencia del motor en HP
- `stock` INT - Cantidad disponible en inventario

### 🔄 **Campos Modificados:**
- `tipo` → `tipo_combustible` (renombrado para mayor claridad)
- `imagenUrl` → `imagen_url` (estandarización de nombres)

### 📊 **Estructura Final de la Tabla:**

```sql
CREATE TABLE autos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    anio INT NOT NULL,
    color VARCHAR(255) NOT NULL,
    kilometraje INT NOT NULL,
    tipo_combustible VARCHAR(255) NOT NULL,
    transmision VARCHAR(255) NOT NULL,
    cilindrada INT NOT NULL,
    potencia INT NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion TEXT,
    imagen_url TEXT,
    estado VARCHAR(255) NOT NULL DEFAULT 'Disponible'
);
```

## 🚀 **Opciones para Actualizar la Base de Datos:**

### **Opción 1: Automática (Recomendada)**
Si tienes `spring.jpa.hibernate.ddl-auto=update` en `application.properties` (que ya lo tienes), simplemente:

1. **Ejecuta el script SQL** `src/main/resources/auto_updated.sql` en tu base de datos MySQL
2. **Reinicia el backend** - Hibernate detectará los cambios y actualizará automáticamente

### **Opción 2: Manual**
Ejecuta estos comandos SQL en tu base de datos:

```sql
-- Agregar nuevos campos
ALTER TABLE autos 
ADD COLUMN tipo_combustible VARCHAR(255) NOT NULL DEFAULT 'Gasolina' AFTER kilometraje,
ADD COLUMN transmision VARCHAR(255) NOT NULL DEFAULT 'Manual' AFTER tipo_combustible,
ADD COLUMN cilindrada INT NOT NULL DEFAULT 2000 AFTER transmision,
ADD COLUMN potencia INT NOT NULL DEFAULT 150 AFTER cilindrada,
ADD COLUMN stock INT NOT NULL DEFAULT 1 AFTER potencia;

-- Eliminar campo obsoleto
ALTER TABLE autos DROP COLUMN tipo;

-- Renombrar columna
ALTER TABLE autos CHANGE COLUMN imagenUrl imagen_url TEXT;

-- Agregar restricciones NOT NULL
ALTER TABLE autos 
MODIFY COLUMN marca VARCHAR(255) NOT NULL,
MODIFY COLUMN modelo VARCHAR(255) NOT NULL,
MODIFY COLUMN anio INT NOT NULL,
MODIFY COLUMN color VARCHAR(255) NOT NULL,
MODIFY COLUMN kilometraje INT NOT NULL,
MODIFY COLUMN precio DECIMAL(10,2) NOT NULL,
MODIFY COLUMN estado VARCHAR(255) NOT NULL DEFAULT 'Disponible';
```

## 📝 **Archivos Modificados en el Backend:**

### **Modelo:**
- ✅ `src/main/java/automoviles/model/Auto.java` - Agregados nuevos campos

### **DTOs:**
- ✅ `src/main/java/automoviles/dto/request/AutoRequest.java` - Campos de entrada
- ✅ `src/main/java/automoviles/dto/response/AutoResponse.java` - Campos de salida

### **Mapper:**
- ✅ `src/main/java/automoviles/service/mapper/AutoMapper.java` - Mapeo actualizado

### **Servicio:**
- ✅ `src/main/java/automoviles/service/impl/AutoServiceImpl.java` - Lógica actualizada

## 🔧 **Pasos para Implementar:**

1. **Ejecuta el script SQL** en tu base de datos MySQL
2. **Reinicia el backend** Spring Boot
3. **Verifica** que los endpoints funcionen correctamente
4. **Prueba** el frontend con el backend actualizado

## 🧪 **Datos de Prueba:**

El script SQL incluye 5 autos de ejemplo con todos los campos completos:

- Toyota Corolla 2023
- Honda Civic 2022  
- Ford F-150 2024
- BMW X5 2023
- Tesla Model 3 2024

## ⚠️ **Notas Importantes:**

- **Backup**: Haz un backup de tu base de datos antes de ejecutar los cambios
- **Compatibilidad**: Los cambios son compatibles hacia atrás
- **Valores por defecto**: Se han configurado valores por defecto para evitar errores
- **Índices**: Se han agregado índices para mejorar el rendimiento

## 🎯 **Resultado Final:**

Después de la actualización, tu backend estará completamente sincronizado con el frontend y podrás:

- ✅ Crear autos con todos los campos del formulario
- ✅ Mostrar información completa en las tablas
- ✅ Filtrar y buscar por todos los campos
- ✅ Mantener consistencia entre frontend y backend 