package com.atenea.backend.repository;

import com.atenea.backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNombre(String name);
    Categoria findById(int id);
}
