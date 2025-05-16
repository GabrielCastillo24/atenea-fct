package com.atenea.backend.model.impl;

import com.atenea.backend.api.dto.UsuarioDTO;
import com.atenea.backend.api.service.IRegistroService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class RegistroServiceImpl implements IRegistroService {

    @Override
    public void registrarUsuario(UsuarioDTO usuario){
        System.out.println("Usuario en impl : --------> "+usuario);
     }
}
