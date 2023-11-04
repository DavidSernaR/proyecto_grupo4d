package co.edu.uniquindio.automaticmail.controladores;

import co.edu.uniquindio.automaticmail.modelo.dto.MensajeDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.UsuarioDTO;
import co.edu.uniquindio.automaticmail.servicios.interfaces.UsuarioInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioInterface usuarioInterface;

    @PostMapping("/registrar")
    public ResponseEntity<MensajeDTO> registro(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        usuarioInterface.registrarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED, false, "Usuario registrado correctamente"));
    }
    @GetMapping("/obtener/{idPerson}")
    public ResponseEntity<MensajeDTO> getPersonDTO (@PathVariable int idUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,usuarioInterface.getUsuarioDTO(idUsuario)));
    }

}
