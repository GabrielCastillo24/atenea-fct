-- Tabla carrito (un carrito por usuario)
CREATE TABLE carrito
(
    id          CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    id_usuario  CHAR(36) NOT NULL UNIQUE,
    creado      TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    actualizado TIMESTAMP            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_carrito_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_carrito_usuario ON carrito (id_usuario);

-- Tabla intermedia: productos en carrito
CREATE TABLE carrito_producto
(
    id          CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    id_carrito  CHAR(36) NOT NULL,
    id_producto INT      NOT NULL,
    cantidad    INT      NOT NULL    DEFAULT 1,
    agregado    TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_carrito_producto_carrito FOREIGN KEY (id_carrito)
        REFERENCES carrito (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_carrito_producto_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    -- Evita productos duplicados en el mismo carrito
    UNIQUE KEY uk_carrito_producto (id_carrito, id_producto)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_carrito_producto_carrito ON carrito_producto (id_carrito);
CREATE INDEX idx_carrito_producto_producto ON carrito_producto (id_producto);