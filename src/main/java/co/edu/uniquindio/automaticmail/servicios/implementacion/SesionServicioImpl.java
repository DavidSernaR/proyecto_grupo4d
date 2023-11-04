package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.SesionDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.TokenDTO;
import co.edu.uniquindio.automaticmail.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.automaticmail.seguridad.servicios.JwtService;
import co.edu.uniquindio.automaticmail.servicios.interfaces.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenDTO login(SesionDTO sesionDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDTO.getEmail(),
                        sesionDTO.getPassword())

        );
        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        return new TokenDTO(jwtToken);
    }
}
