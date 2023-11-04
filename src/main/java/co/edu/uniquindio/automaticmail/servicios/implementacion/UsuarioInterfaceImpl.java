package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.UsuarioDTO;
import co.edu.uniquindio.automaticmail.modelo.entidades.Usuario;
import co.edu.uniquindio.automaticmail.repositorios.CorreoEmpresarialRepo;
import co.edu.uniquindio.automaticmail.repositorios.UsuarioRepo;
import co.edu.uniquindio.automaticmail.servicios.interfaces.UsuarioInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioInterfaceImpl implements UsuarioInterface {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CorreoEmpresarialRepo correoEmpresarialRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception {

        Usuario banderaCorreoPersonal = usuarioRepo.buscarUsuarioPorCorreoPersonal(usuarioDTO.getCorreoPersonal());

        if (banderaCorreoPersonal != null) {
            throw new Exception("El correo " + usuarioDTO.getCorreoPersonal() + " ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setClave(passwordEncoder.encode(usuarioDTO.getClave()));
        usuario.setCorreoPersonal(usuarioDTO.getCorreoPersonal());
        usuario.setCorreoEmpresarial(correoEmpresarialRepo.buscarCorreoEmpresarialPorId(usuarioDTO.getIdCorreoEmpresarial()));

        usuarioRepo.save(usuario);

        return usuario.getId();
    }

    @Override
    public UsuarioDTO getUsuarioDTO(int idUsuario) throws Exception {

        Optional<Usuario> buscarUsuario = usuarioRepo.findById(idUsuario);

        if (buscarUsuario.isEmpty()) {
            throw new Exception("El usuario con id " + idUsuario + " no existe");
        }

        return convertirADTO(buscarUsuario.get());
    }

    private UsuarioDTO convertirADTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getClave(),
                usuario.getCorreoPersonal(),
                usuario.getCorreoEmpresarial().getId()
        );

        return usuarioDTO;
    }
}
