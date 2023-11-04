package co.edu.uniquindio.automaticmail.seguridad.servicios;

import co.edu.uniquindio.automaticmail.modelo.entidades.Usuario;
import co.edu.uniquindio.automaticmail.repositorios.UsuarioRepo;
import co.edu.uniquindio.automaticmail.seguridad.modelo.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepo.buscarUsuarioPorCorreoPersonal(email));

        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("El usuario no existe");
        } else {
            return UserDetailsImpl.build(usuario.get());
        }

    }

}
