package com.atenea.backend.repository;

import com.atenea.backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para operaciones CRUD de la entidad Categoria
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    /**
     * Busca una categoría por su nombre
     */
    Categoria findByNombre(String name);

    /**
     * Busca una categoría por su identificador
     */
    Categoria findById(int id);
}
