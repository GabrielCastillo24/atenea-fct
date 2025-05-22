CREATE TABLE refresh_tokens
(
    id         CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    id_usuario CHAR(36)  NOT NULL,
    creado     TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    expiracion TIMESTAMP NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_refresh_token_id_usuario ON refresh_tokens (id_usuario);