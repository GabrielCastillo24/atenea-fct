package com.atenea.backend.repository;

import com.atenea.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repositorio para operaciones CRUD de la entidad Producto
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /**
     * Busca un producto por su nombre
     */
    Producto findByNombre(String name);

    /**
     * Busca un producto por su identificador
     */
    Producto findById(int id);

    /**
     * Busca todos los productos de una categoría
     */
    List<Producto> findByCategoriaId(int id);

    /**
     * Busca todos los productos de una categoría ordenados por nombre descendente
     */
    List<Producto> findByCategoriaIdOrderByNombreDesc(int id);

    /**
     * Busca productos que empiecen con el texto especificado ignorando mayúsculas
     */
    List<Producto> findByNombreStartingWithIgnoreCase(String prefix);
}
