-- Script para actualizar la tabla autos con los campos faltantes del frontend
-- Ejecutar este script en tu base de datos MySQL

-- 1. Agregar nuevos campos a la tabla autos
ALTER TABLE autos 
ADD COLUMN tipo_combustible VARCHAR(255) NOT NULL DEFAULT 'Gasolina' AFTER kilometraje,
ADD COLUMN transmision VARCHAR(255) NOT NULL DEFAULT 'Manual' AFTER tipo_combustible,
ADD COLUMN cilindrada INT NOT NULL DEFAULT 2000 AFTER transmision,
ADD COLUMN potencia INT NOT NULL DEFAULT 150 AFTER cilindrada,
ADD COLUMN stock INT NOT NULL DEFAULT 1 AFTER potencia;

-- 2. Eliminar el campo 'tipo' que ya no se usa (reemplazado por tipo_combustible)
ALTER TABLE autos DROP COLUMN tipo;

-- 3. Cambiar el nombre de la columna imagenUrl a imagen_url para consistencia
ALTER TABLE autos CHANGE COLUMN imagenUrl imagen_url TEXT;

-- 4. Agregar restricciones NOT NULL a campos importantes
ALTER TABLE autos 
MODIFY COLUMN marca VARCHAR(255) NOT NULL,
MODIFY COLUMN modelo VARCHAR(255) NOT NULL,
MODIFY COLUMN anio INT NOT NULL,
MODIFY COLUMN color VARCHAR(255) NOT NULL,
MODIFY COLUMN kilometraje INT NOT NULL,
MODIFY COLUMN precio DECIMAL(10,2) NOT NULL,
MODIFY COLUMN estado VARCHAR(255) NOT NULL DEFAULT 'Disponible';

-- 5. Agregar índices para mejorar el rendimiento
CREATE INDEX idx_autos_marca ON autos(marca);
CREATE INDEX idx_autos_modelo ON autos(modelo);
CREATE INDEX idx_autos_anio ON autos(anio);
CREATE INDEX idx_autos_precio ON autos(precio);
CREATE INDEX idx_autos_stock ON autos(stock);
CREATE INDEX idx_autos_estado ON autos(estado);

-- 6. Insertar algunos datos de ejemplo con los nuevos campos
INSERT INTO autos (marca, modelo, anio, color, kilometraje, tipo_combustible, transmision, cilindrada, potencia, stock, precio, descripcion, imagen_url, estado) VALUES
('Toyota', 'Corolla', 2023, 'Blanco', 50000, 'Gasolina', 'Automática', 2000, 150, 5, 25000.00, 'Toyota Corolla 2023 en excelente estado, automático, gasolina', 'https://ejemplo.com/corolla.jpg', 'Disponible'),
('Honda', 'Civic', 2022, 'Negro', 35000, 'Gasolina', 'Manual', 1800, 140, 3, 28000.00, 'Honda Civic 2022, manual, muy bien cuidado', 'https://ejemplo.com/civic.jpg', 'Disponible'),
('Ford', 'F-150', 2024, 'Gris', 15000, 'Gasolina', 'Automática', 3500, 400, 2, 45000.00, 'Ford F-150 2024, pickup potente, automático', 'https://ejemplo.com/f150.jpg', 'Disponible'),
('BMW', 'X5', 2023, 'Azul', 25000, 'Diesel', 'Automática', 3000, 286, 1, 65000.00, 'BMW X5 2023, SUV de lujo, diesel', 'https://ejemplo.com/x5.jpg', 'Disponible'),
('Tesla', 'Model 3', 2024, 'Rojo', 10000, 'Eléctrico', 'Automática', 0, 350, 2, 55000.00, 'Tesla Model 3 2024, eléctrico, autopilot', 'https://ejemplo.com/tesla.jpg', 'Disponible');

-- 7. Verificar que la tabla se actualizó correctamente
SELECT 
    COLUMN_NAME, 
    DATA_TYPE, 
    IS_NULLABLE, 
    COLUMN_DEFAULT
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'autos' 
ORDER BY ORDINAL_POSITION;

-- Actualizar la tabla clientes para agregar la columna apellidos
ALTER TABLE clientes ADD COLUMN apellidos VARCHAR(100) AFTER nombre;

-- Verificar la estructura actualizada
DESCRIBE clientes; 