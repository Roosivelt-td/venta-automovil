-- Script para verificar si la tabla autos tiene el campo stock
-- Ejecutar este script en tu base de datos MySQL

-- 1. Verificar la estructura actual de la tabla autos
DESCRIBE autos;

-- 2. Verificar si existe el campo stock
SELECT 
    COLUMN_NAME, 
    DATA_TYPE, 
    IS_NULLABLE, 
    COLUMN_DEFAULT
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'autos' AND COLUMN_NAME = 'stock';

-- 3. Si no existe el campo stock, agregarlo
ALTER TABLE autos ADD COLUMN stock INT NOT NULL DEFAULT 1 AFTER precio;

-- 4. Verificar datos existentes
SELECT id, marca, modelo, anio, stock, precio, estado FROM autos LIMIT 10;

-- 5. Actualizar stock de autos existentes si es NULL
UPDATE autos SET stock = 1 WHERE stock IS NULL;

-- 6. Insertar datos de ejemplo si no hay autos
INSERT INTO autos (marca, modelo, anio, color, kilometraje, tipo_combustible, transmision, cilindrada, potencia, stock, precio, descripcion, imagen_url, estado) 
SELECT * FROM (
    SELECT 'Toyota', 'Corolla', 2023, 'Blanco', 50000, 'Gasolina', 'Automática', 2000, 150, 5, 25000.00, 'Toyota Corolla 2023 en excelente estado', 'https://ejemplo.com/corolla.jpg', 'Disponible'
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM autos WHERE marca = 'Toyota' AND modelo = 'Corolla' AND anio = 2023
) LIMIT 1; 