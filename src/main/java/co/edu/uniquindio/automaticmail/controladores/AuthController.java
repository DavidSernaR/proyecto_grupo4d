package co.edu.uniquindio.automaticmail.controladores;

import co.edu.uniquindio.automaticmail.modelo.dto.MensajeDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.SesionDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.UsuarioDTO;
import co.edu.uniquindio.automaticmail.servicios.interfaces.SesionServicio;
import co.edu.uniquindio.automaticmail.servicios.interfaces.UsuarioInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioInterface usuarioInterface;
    private final SesionServicio sesionServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginPerson) {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, sesionServicio.login(loginPerson)) );
    }
    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registro(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        usuarioInterface.registrarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Usuario registrado correctamente"));
    }

}
