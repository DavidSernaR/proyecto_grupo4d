package co.edu.uniquindio.automaticmail.servicios.interfaces;

import co.edu.uniquindio.automaticmail.modelo.dto.UsuarioDTO;

public interface UsuarioInterface {
    public int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;
    public UsuarioDTO getUsuarioDTO(int idUsuario) throws Exception;
}
