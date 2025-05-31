-- Tabla categoria
CREATE TABLE categoria
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_categoria_nombre ON categoria (nombre);

INSERT INTO categoria (nombre)
VALUES ('Juguete');
INSERT INTO categoria (nombre)
VALUES ('Papelería');
INSERT INTO categoria (nombre)
VALUES ('Libro');
