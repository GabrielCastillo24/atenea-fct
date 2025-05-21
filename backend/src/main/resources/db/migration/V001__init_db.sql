CREATE TABLE usuario
(
    id          CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    nombre      VARCHAR(50)  NOT NULL,
    apellido    VARCHAR(50)  NOT NULL,
    correo      VARCHAR(100) NOT NULL UNIQUE,
    telefono    VARCHAR(20),
    direccion   VARCHAR(255),
    contrasena  CHAR(60)     NOT NULL,

    creado      TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    actualizado TIMESTAMP            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_usuario_correo ON usuario (correo);