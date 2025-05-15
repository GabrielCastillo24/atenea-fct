
-- Asegúrate de estar en la base Atenea
USE atenea;

-- tabla Usuario
CREATE TABLE Usuario (
    id               INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(50)  NOT NULL,
    apellido         VARCHAR(50)  NOT NULL,
    correo           VARCHAR(100) NOT NULL UNIQUE,   
    telefono         VARCHAR(20)  NOT NULL,
    direccion        VARCHAR(255),                   
    hash_contrasena  CHAR(60)     NOT NULL          
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- Tabla Producto
CREATE TABLE Producto (
    id           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    descripcion  TEXT,
    precio       DECIMAL(10, 2) NOT NULL,
    stock        INT DEFAULT 0
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- tabla carrito 
CREATE TABLE Carrito (
    idUsuario        INT UNSIGNED NOT NULL,
    idProducto       INT UNSIGNED NOT NULL,
    cantidadProducto INT NOT NULL DEFAULT 1,

    PRIMARY KEY (idUsuario, idProducto),

    FOREIGN KEY (idUsuario) REFERENCES Usuario(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    FOREIGN KEY (idProducto) REFERENCES Producto(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
    
    
DROP TABLE IF EXISTS Producto;
    
    
    
    