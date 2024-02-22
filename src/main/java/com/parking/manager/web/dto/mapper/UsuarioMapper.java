package com.parking.manager.web.dto.mapper;

import com.parking.manager.entity.Usuario;
import com.parking.manager.web.dto.UsuarioCreateDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = String.valueOf(usuario.getRole()).substring("ROLE_".length());

        PropertyMap<Usuario, UsuarioResponseDto> propps = new PropertyMap<Usuario, UsuarioResponseDto>() {
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(propps);

        return mapper.map(usuario, UsuarioResponseDto.class);
    }

}
