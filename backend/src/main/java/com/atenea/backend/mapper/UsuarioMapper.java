package com.atenea.backend.mapper;

import com.atenea.backend.dto.PerfilUsuarioDto;
import com.atenea.backend.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * Componente Mapper para convertir entidades de usuario a DTOs
 */
@Component
public class UsuarioMapper {

    /**
     * Convierte una entidad Usuario a PerfilUsuarioDto
     * @param usuario Entidad Usuario de la base de datos
     * @return PerfilUsuarioDto con información del perfil del usuario
     */
    public PerfilUsuarioDto toPerfilUsuarioDto(final Usuario usuario) {
        // Extrae solo los datos necesarios para el perfil público
        return new PerfilUsuarioDto(usuario.getCorreo(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDireccion());
    }
}
