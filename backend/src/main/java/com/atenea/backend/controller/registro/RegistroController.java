package com.atenea.backend.controller.registro;

import com.atenea.backend.api.dto.UsuarioDTO;
import com.atenea.backend.api.service.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/registro")
public class RegistroController {

    private final IRegistroService registroService;

    @Autowired
    public RegistroController(IRegistroService registroService) {
        this.registroService = registroService;
    }

    @PostMapping("/registrar-usuario")
    public ResponseEntity<String> resgistrarUsuario(@RequestBody UsuarioDTO usuario){
        try {
            System.out.println("usuario : ------>"+ usuario);
            registroService.registrarUsuario(usuario);
            return ResponseEntity.ok("Usuario registrado ");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar usuario");
        }
    }
}
