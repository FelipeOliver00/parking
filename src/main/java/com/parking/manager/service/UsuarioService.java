package com.parking.manager.service;

import com.parking.manager.entity.Usuario;
import com.parking.manager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario savlar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
