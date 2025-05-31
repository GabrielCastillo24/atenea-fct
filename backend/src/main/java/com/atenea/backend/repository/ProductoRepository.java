package com.atenea.backend.repository;

import com.atenea.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByNombre(String name);
    Producto findById(int id);
    List<Producto> findByCategoriaId(int id);
    List<Producto> findByCategoriaIdOrderByNombreDesc(int id);
    List<Producto> findByNombreStartingWithIgnoreCase(String prefix);
}
