package com.atenea.backend.mapper;

import com.atenea.backend.dto.RegistroRequestDto;
import com.atenea.backend.dto.RegistroResponseDto;
import com.atenea.backend.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRegistroMapper {
    public Usuario toEntity(RegistroRequestDto registroRequestDto) {
        final var usuario = new Usuario();

        usuario.setNombre(registroRequestDto.nombre());
        usuario.setApellido(registroRequestDto.apellido());
        usuario.setCorreo(registroRequestDto.correo());
        usuario.setTelefono(registroRequestDto.telefono());
        usuario.setDireccion(registroRequestDto.direccion());
        usuario.setContrasena(registroRequestDto.contrasena());

        return usuario;
    }

    public RegistroResponseDto toRegistroResponseDto(
            final Usuario usuario) {

        return new RegistroResponseDto(usuario.getCorreo());
    }
}
