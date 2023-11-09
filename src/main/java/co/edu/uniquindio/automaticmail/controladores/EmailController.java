package co.edu.uniquindio.automaticmail.controladores;

import co.edu.uniquindio.automaticmail.modelo.dto.MensajeDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.PlantillaDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.PolizaDTO;
import co.edu.uniquindio.automaticmail.servicios.interfaces.EmailInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailInterface emailInterface;

    @PostMapping("/enviar")
    public ResponseEntity<MensajeDTO> enviarCorreo(@RequestBody PlantillaDTO plantillaDTO) throws Exception {
        emailInterface.enviarCorreo(plantillaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body( new MensajeDTO(HttpStatus.CREATED, false,"Correo enviado correctamente"));
    }

}
