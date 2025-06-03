-- Tabla pedido actualizada con dirección de envío
CREATE TABLE pedido
(
    id               CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    id_usuario       CHAR(36)       NOT NULL,
    fecha_pedido     TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    metodo_pago      VARCHAR(50)    NOT NULL,
    estado           VARCHAR(20)    NOT NULL DEFAULT 'PENDIENTE',
    total            DECIMAL(10, 2) NOT NULL,
    direccion_envio  VARCHAR(255)   NOT NULL,

    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_pedido_usuario ON pedido (id_usuario);
CREATE INDEX idx_pedido_fecha ON pedido (fecha_pedido);
CREATE INDEX idx_pedido_estado ON pedido (estado);

-- Tabla intermedia: productos en pedido
CREATE TABLE pedido_producto
(
    id              CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    id_pedido       CHAR(36)       NOT NULL,
    id_producto     INT            NOT NULL,
    cantidad        INT            NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,

    CONSTRAINT fk_pedido_producto_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedido (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_pedido_producto_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_pedido_producto_pedido ON pedido_producto (id_pedido);
CREATE INDEX idx_pedido_producto_producto ON pedido_producto (id_producto);