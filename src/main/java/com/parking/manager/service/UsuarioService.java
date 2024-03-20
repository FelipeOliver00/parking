package com.parking.manager.service;

import com.parking.manager.entity.Usuario;
import com.parking.manager.exception.EntityNotFoundException;
import com.parking.manager.exception.UserNameUniqueViolationException;
import com.parking.manager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario savlar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        }catch (DataIntegrityViolationException e) {
            throw new UserNameUniqueViolationException(String.format("Nome de usuário: {%s} já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format("Usuário não encontrado", id)));
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("As senhas não conferem");
        }

        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("Senha atual inválida não confere");
        }

        user.setPassword(novaSenha);
        return savlar(user);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
