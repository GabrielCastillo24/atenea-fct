package com.atenea.backend.controller;

import com.atenea.backend.dto.ProductosDto;
import com.atenea.backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private  final ProductoService productoService;

    @GetMapping("/obtener-productos")
    public ResponseEntity<List<ProductosDto>> getProducto(Authentication authentication) {
        try {
            List<ProductosDto> productos = productoService.obtenerProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
