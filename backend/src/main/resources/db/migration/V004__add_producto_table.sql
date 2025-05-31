-- Tabla producto
CREATE TABLE producto
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100)   NOT NULL,
    descripcion  TEXT,
    precio       DECIMAL(10, 2) NOT NULL,
    url_img      VARCHAR(255),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categoria (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_producto_id_categoria ON producto (id_categoria);

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop Jinx ARCANE',
           'Juguete de 12cm coleccionable',
           19.99,
           '../../../assets/img/productos/FunkoJINX.png',
           1
       );