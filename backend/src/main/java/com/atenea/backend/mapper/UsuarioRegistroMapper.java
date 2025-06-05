package com.atenea.backend.mapper;

import com.atenea.backend.dto.RegistroRequestDto;
import com.atenea.backend.dto.RegistroResponseDto;
import com.atenea.backend.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * Componente Mapper para convertir DTOs de registro a entidades Usuario
 */
@Component
public class UsuarioRegistroMapper {

    /**
     * Convierte un DTO de solicitud de registro en una entidad Usuario
     * @param registroRequestDto DTO con los datos del formulario de registro
     * @return Usuario entidad con los datos mapeados
     */
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

    /**
     * Convierte una entidad Usuario en un DTO de respuesta de registro
     * @param usuario Entidad Usuario registrado
     * @return RegistroResponseDto con el correo del usuario registrado
     */
    public RegistroResponseDto toRegistroResponseDto(
            final Usuario usuario) {

        return new RegistroResponseDto(usuario.getCorreo());
    }
}
