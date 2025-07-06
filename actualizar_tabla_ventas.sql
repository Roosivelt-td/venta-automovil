-- Script para actualizar la tabla ventas y agregar las columnas metodo_pago y observaciones

-- Agregar columna metodo_pago
ALTER TABLE ventas ADD COLUMN metodo_pago VARCHAR(50);

-- Agregar columna observaciones
ALTER TABLE ventas ADD COLUMN observaciones TEXT;

-- Actualizar registros existentes con valores por defecto
UPDATE ventas SET metodo_pago = 'Efectivo' WHERE metodo_pago IS NULL;
UPDATE ventas SET observaciones = '' WHERE observaciones IS NULL;

-- Verificar que las columnas se agregaron correctamente
DESCRIBE ventas; 