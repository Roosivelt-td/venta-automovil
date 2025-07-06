# 🚀 Guía de Despliegue Gratuito - Sistema de Gestión de Ventas

## 📋 **Resumen de Opciones Gratuitas**

### **Frontend (Angular)**
- ✅ **Vercel**: Más fácil y rápido
- ✅ **Netlify**: Excelente para proyectos estáticos
- ✅ **GitHub Pages**: Integrado con tu repositorio

### **Backend (Spring Boot)**
- ✅ **Railway**: 500 horas gratis, base de datos incluida
- ✅ **Render**: Plan gratuito generoso
- ✅ **Heroku**: Plan gratuito limitado

### **Base de Datos**
- ✅ **Railway PostgreSQL**: Incluido con Railway
- ✅ **PlanetScale**: MySQL gratuito
- ✅ **Supabase**: PostgreSQL gratuito

---

## 🎯 **Opción Recomendada: Vercel + Railway**

### **Paso 1: Preparar el Repositorio**

#### **1.1 Subir a GitHub**
```bash
# Inicializar git si no está hecho
git init
git add .
git commit -m "Preparar para despliegue"

# Crear repositorio en GitHub y subir
git remote add origin https://github.com/tu-usuario/venta-automovil.git
git push -u origin main
```

#### **1.2 Estructura del Repositorio**
```
venta-automovil/
├── Frontend/          # Angular app
├── src/              # Spring Boot backend
├── pom.xml
├── application.properties
├── application-prod.properties
└── README.md
```

### **Paso 2: Desplegar Backend en Railway**

#### **2.1 Crear cuenta en Railway**
1. Ve a [railway.app](https://railway.app)
2. Inicia sesión con GitHub
3. Crea un nuevo proyecto

#### **2.2 Conectar Repositorio**
1. Selecciona "Deploy from GitHub repo"
2. Selecciona tu repositorio `venta-automovil`
3. Railway detectará automáticamente que es una app Java

#### **2.3 Configurar Variables de Entorno**
En Railway, ve a la pestaña "Variables" y agrega:

```env
# Base de datos (Railway te dará estos valores)
DATABASE_URL=postgresql://usuario:password@host:puerto/database
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_password

# JWT
JWT_SECRET=tu-secreto-super-seguro-para-produccion

# Frontend URL (actualizar después de desplegar el frontend)
FRONTEND_URL=https://tu-frontend.vercel.app

# Puerto
PORT=8080
```

#### **2.4 Configurar Base de Datos**
1. En Railway, ve a "New Service" → "Database" → "PostgreSQL"
2. Railway te dará automáticamente las credenciales
3. Copia las variables de entorno a tu servicio web

#### **2.5 Desplegar**
1. Railway detectará automáticamente tu `pom.xml`
2. Construirá y desplegará tu aplicación
3. Obtendrás una URL como: `https://tu-app.railway.app`

### **Paso 3: Desplegar Frontend en Vercel**

#### **3.1 Crear cuenta en Vercel**
1. Ve a [vercel.com](https://vercel.com)
2. Inicia sesión con GitHub
3. Importa tu repositorio

#### **3.2 Configurar Proyecto**
1. Selecciona el directorio `Frontend`
2. Framework preset: `Angular`
3. Build command: `npm run build`
4. Output directory: `dist/venta-automovil`

#### **3.3 Configurar Variables de Entorno**
En Vercel, agrega:
```env
BACKEND_URL=https://tu-backend.railway.app
```

#### **3.4 Actualizar Configuración de API**
En `Frontend/src/app/config/api.config.ts`, actualiza la URL de producción:

```typescript
const baseUrl = isProduction 
  ? 'https://tu-backend.railway.app/api' // Tu URL real de Railway
  : 'http://localhost:8080/api';
```

#### **3.5 Desplegar**
1. Vercel construirá y desplegará automáticamente
2. Obtendrás una URL como: `https://tu-frontend.vercel.app`

### **Paso 4: Configurar CORS**

#### **4.1 Actualizar SecurityConfig.java**
```java
config.addAllowedOrigin("https://tu-frontend.vercel.app"); // Tu URL real de Vercel
```

#### **4.2 Re-desplegar Backend**
Railway detectará los cambios y re-desplegará automáticamente.

---

## 🔧 **Configuraciones Adicionales**

### **Configurar Dominio Personalizado (Opcional)**

#### **Vercel (Frontend)**
1. Ve a tu proyecto en Vercel
2. Settings → Domains
3. Agrega tu dominio personalizado
4. Configura los DNS según las instrucciones

#### **Railway (Backend)**
1. Ve a tu proyecto en Railway
2. Settings → Domains
3. Agrega tu dominio personalizado

### **Configurar SSL**
- ✅ **Vercel**: SSL automático incluido
- ✅ **Railway**: SSL automático incluido
- ✅ **Netlify**: SSL automático incluido

---

## 📱 **Otras Opciones de Despliegue**

### **Opción 2: Netlify + Render**

#### **Frontend en Netlify**
```bash
# Instalar Netlify CLI
npm install -g netlify-cli

# Construir proyecto
cd Frontend
ng build --prod

# Desplegar
netlify deploy --dir=dist/venta-automovil --prod
```

#### **Backend en Render**
1. Ve a [render.com](https://render.com)
2. Crea un nuevo Web Service
3. Conecta tu repositorio de GitHub
4. Configura:
   - **Build Command**: `./mvnw clean package`
   - **Start Command**: `java -jar target/venta-automovil-0.0.1-SNAPSHOT.jar`

### **Opción 3: GitHub Pages + Heroku**

#### **Frontend en GitHub Pages**
```bash
# Instalar angular-cli-ghpages
npm install -g angular-cli-ghpages

# Construir y desplegar
ng build --prod --base-href=/venta-automovil/
npx angular-cli-ghpages --dir=dist/venta-automovil
```

#### **Backend en Heroku**
```bash
# Instalar Heroku CLI
# Crear app
heroku create tu-app-venta-automovil

# Configurar variables de entorno
heroku config:set DATABASE_URL=tu-url-de-base-de-datos
heroku config:set JWT_SECRET=tu-secreto

# Desplegar
git push heroku main
```

---

## 🧪 **Pruebas Post-Despliegue**

### **1. Verificar Frontend**
- ✅ Acceder a la URL del frontend
- ✅ Verificar que carga correctamente
- ✅ Probar navegación básica

### **2. Verificar Backend**
- ✅ Acceder a `https://tu-backend.railway.app/api/autos`
- ✅ Verificar que responde correctamente
- ✅ Probar autenticación

### **3. Verificar Conexión**
- ✅ Probar login desde el frontend
- ✅ Verificar que las peticiones llegan al backend
- ✅ Probar funcionalidades principales

### **4. Verificar Base de Datos**
- ✅ Verificar que las tablas se crearon
- ✅ Probar inserción de datos
- ✅ Verificar que los datos persisten

---

## 🔍 **Solución de Problemas Comunes**

### **Error de CORS**
```java
// En SecurityConfig.java, agregar temporalmente:
config.addAllowedOriginPattern("*");
```

### **Error de Base de Datos**
1. Verificar variables de entorno en Railway
2. Verificar que la base de datos esté activa
3. Revisar logs en Railway

### **Error de Build**
1. Verificar que `pom.xml` esté correcto
2. Verificar versión de Java (17)
3. Revisar logs de build

### **Error de Rutas en Frontend**
1. Verificar `vercel.json`
2. Verificar configuración de Angular
3. Verificar rutas en `app.routes.ts`

---

## 📊 **Monitoreo y Mantenimiento**

### **Railway Dashboard**
- ✅ Monitorear uso de recursos
- ✅ Ver logs en tiempo real
- ✅ Configurar alertas

### **Vercel Dashboard**
- ✅ Monitorear rendimiento
- ✅ Ver analytics
- ✅ Configurar webhooks

### **Base de Datos**
- ✅ Monitorear conexiones
- ✅ Verificar backups automáticos
- ✅ Optimizar consultas

---

## 💰 **Costos y Límites**

### **Gratuito (Plan Básico)**
- ✅ **Vercel**: 100GB bandwidth/mes
- ✅ **Railway**: 500 horas/mes
- ✅ **Netlify**: 100GB bandwidth/mes
- ✅ **Render**: 750 horas/mes

### **Cuándo Actualizar a Pago**
- 📈 Más de 100GB de tráfico/mes
- 📈 Más de 500 horas de uso/mes
- 📈 Necesitas más recursos
- 📈 Soporte prioritario

---

## 🎉 **Resultado Final**

Después del despliegue tendrás:
- 🌐 **Frontend**: `https://tu-frontend.vercel.app`
- 🔧 **Backend**: `https://tu-backend.railway.app`
- 🗄️ **Base de datos**: PostgreSQL en Railway
- 🔒 **SSL**: Automático en todas las URLs
- 📱 **Responsive**: Funciona en todos los dispositivos

### **Compartir con Otros**
- ✅ Envía las URLs a quien quieras que pruebe
- ✅ No necesitas configurar nada adicional
- ✅ Funciona desde cualquier dispositivo
- ✅ Acceso 24/7

¡Tu sistema estará disponible para que cualquier persona lo pruebe sin costo alguno! 