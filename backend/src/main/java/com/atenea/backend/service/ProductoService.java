package com.atenea.backend.service;

import com.atenea.backend.dto.ProductosDto;
import com.atenea.backend.model.Producto;
import com.atenea.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private  final ProductoRepository productoRepository;

    public List<ProductosDto> obtenerProductos() {
        List<Producto> productos = productoRepository.findAll();

        return productos.stream()
                .map(producto -> new ProductosDto(
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio(),
                        producto.getUrlImg()

                ))
                .collect(Collectors.toList());
    }
}
