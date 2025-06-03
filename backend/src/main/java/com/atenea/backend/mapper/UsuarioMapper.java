package com.atenea.backend.mapper;

import com.atenea.backend.dto.PerfilUsuarioDto;
import com.atenea.backend.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public PerfilUsuarioDto toPerfilUsuarioDto(final Usuario usuario) {
        return new PerfilUsuarioDto(usuario.getCorreo(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDireccion());
    }
}
