CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255),
    rol VARCHAR(20) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
); 

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    dni VARCHAR(15) UNIQUE,
    telefono VARCHAR(15),
    direccion TEXT,
    correo VARCHAR(100)
);
CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100),
    ruc VARCHAR(15) UNIQUE,
    contacto VARCHAR(100),
    telefono VARCHAR(15),
    direccion TEXT
);
CREATE TABLE autos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    anio YEAR,
    color VARCHAR(30),
    kilometraje INT,
    tipo VARCHAR(20),
    precio DECIMAL(10, 2),
    descripcion TEXT,
    imagen_url TEXT,
    estado VARCHAR(20)
);
CREATE TABLE compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT,
    id_auto INT,
    fecha DATE,
    precio_compra DECIMAL(10, 2),
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id),
    FOREIGN KEY (id_auto) REFERENCES autos(id)
);
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_auto INT,
    id_usuario INT,
    fecha DATE,
    precio_venta DECIMAL(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_auto) REFERENCES autos(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    metodo_pago VARCHAR(20),
    monto DECIMAL(10,2),
    fecha DATE,
    FOREIGN KEY (id_venta) REFERENCES ventas(id)
);
CREATE TABLE reembolsos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    motivo TEXT,
    monto DECIMAL(10,2),
    fecha DATE,
    FOREIGN KEY (id_venta) REFERENCES ventas(id)
);
