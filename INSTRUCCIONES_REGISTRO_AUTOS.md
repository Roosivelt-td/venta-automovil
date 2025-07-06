# 🚗 Sistema de Registro de Autos - Instrucciones de Uso

## 🎯 **Funcionalidades Implementadas**

### ✅ **Frontend (Angular):**
- **Formulario completo** para registrar autos con todos los campos
- **Validaciones** en tiempo real
- **Modal responsivo** con diseño moderno
- **Mensajes de estado** (éxito, error, carga)
- **Tabla de gestión** con datos reales de la base de datos
- **Estadísticas dinámicas** (total, disponibles, valor, stock)
- **Acciones CRUD** (Crear, Leer, Eliminar)

### ✅ **Backend (Spring Boot):**
- **API REST completa** para autos
- **Modelo actualizado** con todos los campos
- **Base de datos sincronizada** con el frontend
- **Validaciones** en el servidor
- **Manejo de errores** robusto

## 🚀 **Pasos para Probar el Sistema**

### **1. Preparar la Base de Datos**
```sql
-- Ejecutar el script SQL para actualizar la tabla
-- Archivo: src/main/resources/auto_updated.sql
```

### **2. Iniciar el Backend**
```bash
# En el directorio raíz del proyecto
./mvnw spring-boot:run
# O
mvn spring-boot:run
```

**Verificar que el backend esté corriendo:**
- URL: `http://localhost:8080`
- API: `http://localhost:8080/api/autos/`

### **3. Iniciar el Frontend**
```bash
# En el directorio Frontend/
npm install
ng serve
```

**Verificar que el frontend esté corriendo:**
- URL: `http://localhost:4200`

### **4. Probar el Registro de Autos**

#### **Navegación:**
1. Ve a `http://localhost:4200`
2. Inicia sesión en el sistema
3. En el sidebar, ve a **"Autos" → "Añadir Auto"**

#### **Registrar un Auto:**
1. **Marca:** Selecciona una marca (ej: Toyota)
2. **Modelo:** Escribe el modelo (ej: Corolla)
3. **Año:** Ingresa el año (ej: 2024)
4. **Color:** Selecciona un color (ej: Blanco)
5. **Precio:** Ingresa el precio (ej: 25000)
6. **Kilometraje:** Ingresa el kilometraje (ej: 50000)
7. **Tipo de Combustible:** Selecciona (ej: Gasolina)
8. **Transmisión:** Selecciona (ej: Automática)
9. **Cilindrada:** Ingresa en cc (ej: 2000)
10. **Potencia:** Ingresa en HP (ej: 150)
11. **Stock:** Ingresa cantidad (ej: 5)
12. **Descripción:** Opcional (ej: Auto en excelente estado)
13. **URL de Imagen:** Opcional

#### **Hacer clic en "Guardar Auto"**

### **5. Verificar el Registro**

#### **En la Base de Datos:**
```sql
-- Verificar que el auto se registró
SELECT * FROM autos ORDER BY id DESC LIMIT 1;
```

#### **En el Frontend:**
1. Ve a **"Autos" → "Gestionar Autos"**
2. Verifica que el auto aparezca en la tabla
3. Revisa las estadísticas actualizadas

## 🔧 **Endpoints de la API**

### **Crear Auto:**
```
POST http://localhost:8080/api/autos/create
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2024,
  "color": "Blanco",
  "precio": 25000,
  "kilometraje": 50000,
  "tipoCombustible": "Gasolina",
  "transmision": "Automática",
  "cilindrada": 2000,
  "potencia": 150,
  "stock": 5,
  "descripcion": "Auto en excelente estado",
  "imagenUrl": "https://ejemplo.com/imagen.jpg",
  "estado": "Disponible"
}
```

### **Obtener Todos los Autos:**
```
GET http://localhost:8080/api/autos/
```

### **Obtener Auto por ID:**
```
GET http://localhost:8080/api/autos/{id}
```

### **Actualizar Auto:**
```
PUT http://localhost:8080/api/autos/update/{id}
```

### **Eliminar Auto:**
```
DELETE http://localhost:8080/api/autos/delete/{id}
```

## 📊 **Campos del Formulario**

| Campo | Tipo | Requerido | Descripción |
|-------|------|-----------|-------------|
| Marca | Select | ✅ | Marca del vehículo |
| Modelo | Text | ✅ | Modelo específico |
| Año | Number | ✅ | Año de fabricación |
| Color | Select | ✅ | Color del vehículo |
| Precio | Number | ✅ | Precio en dólares |
| Kilometraje | Number | ✅ | Kilometraje actual |
| Tipo Combustible | Select | ✅ | Gasolina, Diesel, etc. |
| Transmisión | Select | ✅ | Manual, Automática, etc. |
| Cilindrada | Number | ✅ | Cilindrada en cc |
| Potencia | Number | ✅ | Potencia en HP |
| Stock | Number | ✅ | Cantidad disponible |
| Descripción | Textarea | ❌ | Descripción opcional |
| URL Imagen | URL | ❌ | URL de imagen opcional |

## 🎨 **Características del Frontend**

### **Validaciones:**
- ✅ Campos requeridos marcados con *
- ✅ Validación de rangos (año, precio, etc.)
- ✅ Mensajes de error personalizados
- ✅ Estilos visuales para campos inválidos

### **Experiencia de Usuario:**
- ✅ Indicador de carga durante el envío
- ✅ Mensajes de éxito/error
- ✅ Formulario se limpia después del envío
- ✅ Redirección automática después del éxito
- ✅ Botones deshabilitados durante la carga

### **Responsive Design:**
- ✅ Modal adaptativo para móviles
- ✅ Grid responsivo en el formulario
- ✅ Tabla con scroll horizontal en móviles

## 🐛 **Solución de Problemas**

### **Error de Conexión:**
- Verificar que el backend esté corriendo en puerto 8080
- Verificar que no haya errores de CORS
- Revisar la consola del navegador

### **Error de Base de Datos:**
- Verificar que MySQL esté corriendo
- Verificar las credenciales en `application.properties`
- Ejecutar el script SQL de actualización

### **Error de Validación:**
- Verificar que todos los campos requeridos estén completos
- Verificar que los valores estén en los rangos correctos
- Revisar los mensajes de error en el formulario

## 🎯 **Próximos Pasos Sugeridos**

1. **Implementar edición de autos**
2. **Agregar filtros y búsqueda**
3. **Implementar paginación**
4. **Agregar subida de imágenes**
5. **Implementar reportes y estadísticas**
6. **Agregar autenticación y autorización**

## 📞 **Soporte**

Si encuentras algún problema:
1. Revisa la consola del navegador (F12)
2. Revisa los logs del backend
3. Verifica la conexión a la base de datos
4. Asegúrate de que todos los servicios estén corriendo 