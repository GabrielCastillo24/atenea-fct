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
           'Libro para aprobar la FCT',
           'Este libro nos brinda el conocimiento necesario para aprobar la FCT jajajaja xd',
           30.99,
           'https://static.vecteezy.com/system/resources/previews/007/517/574/non_2x/red-closed-book-school-collection-illustration-cartoon-style-on-a-white-background-vector.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'HARRY POTTER 1',
           'Arquitectura & Libros',
           30.99,
           'https://www.naoslibros.es/media/img/portadas/_visd_0000JPG02BWP.jpg',
           1
       );


INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'El Principito',
           '(Edición en gran formato)',
           23.99,
           'https://static.fnac-static.com/multimedia/Images/ES/NR/03/9e/71/7446019/1520-1.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'El Señor de los Anillos ',
           '(nº 01/03 la Comunidad del Anillo )',
           16.99,
           'https://images.cdn3.buscalibre.com/fit-in/360x360/54/49/5449ba87a3e457a22dd6d0972b5c261e.jpg',
           1
       );


INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Ruination.',
           'Una novela de League of Legends ',
           36.99,
           'https://m.media-amazon.com/images/I/91JxDQVQOpL._AC_UF1000,1000_QL80_.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop Jinx ARCANE',
           'Juguete de 12cm coleccionable',
           19.99,
           'https://frikipolis.com/wp-content/uploads/2024/11/arcane-league-of-legends-pop-vinyl-figura-jinx-9cm1.jpg',
           1
       );
INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop Levi ',
           'Juguete de 12cm coleccionable',
           19.99,
           'https://i.ebayimg.com/images/g/jyIAAOSwBf5lGA1I/s-l1200.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop Pain ',
           'Juguete de 12cm coleccionable',
           19.99,
           'https://cdn.grupoelcorteingles.es/SGFM/dctm/MEDIA03/202208/04/00183180131142____3__1200x1200.jpg',
           1
       );


INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'LEGO Minecraft ',
           '12 personajes coleccionables de las Latas Misteriosas LEGO ',
           30.99,
           'https://media.ldlc.com/r1600/ld/products/00/06/20/39/LD0006203925.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Vehículo de construcción Coche Lamborghini Huracán Tecnica LEGO',
           'LEGO Technic ',
           40.99,
           'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202409/30/00197630168991____18__1200x1200.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Rompecabezas De 1000 Piezas ',
           'Juegos Educativos para Niños  ',
           15.99,
           'https://m.media-amazon.com/images/I/71DtPLFExCL._AC_UF1000,1000_QL80_.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop Braum',
           '12 personajes coleccionables de las Latas Misteriosas LEGO ',
           20.99,
           'https://www.funkotienda.com/wp-content/uploads/2018/07/Funko-Pop-League-of-Legends-Braum.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop  League Of Legends Ahri ',
           'figura de 12cm ',
           17.99,
           'https://img.dynos.es/img/1cb91/2062b/13010030008-0.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Funko Pop eague Of Legends Akali',
           'figura de 12cm',
           20.99,
           'https://tcgfactory.com/73663-large_default/funko-pop-vinyl-akali-league-of-legends.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Figura Wolverine ',
           'Figura de 15cm',
           15.99,
           'https://www.ociostock.com/productos/imagenes/img_362238_5c882ec69e0cfeccbfc4a84013fb7c3b_20.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Figura Coloso Deadpool Marvel Serie Legends',
           'Figura 15cm',
           20.99,
           'https://erikstore.com/74878-large_default/figura-coloso-deadpool-marvel-legends.jpg',
           1
       );

INSERT INTO producto (nombre, descripcion, precio, url_img, id_categoria)
VALUES (
           'Figura Deadpool ',
           'Deadpool Marvel 15cm ',
           20.99,
           'https://www.ociostock.com/productos/imagenes/img_345170_5e8456976641d01fa278fe004616b37d_20.jpg',
           1
       );