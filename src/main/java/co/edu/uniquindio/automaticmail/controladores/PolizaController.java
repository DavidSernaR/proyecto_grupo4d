package co.edu.uniquindio.automaticmail.controladores;

import co.edu.uniquindio.automaticmail.modelo.dto.MensajeDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.PolizaDTO;
import co.edu.uniquindio.automaticmail.servicios.interfaces.PolizaInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/polizas")
public class PolizaController {

    private final PolizaInterface polizaInterface;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO> subirArchivo(@RequestParam("file") MultipartFile file) throws Exception {
        File archivo = polizaInterface.convert(file);
        Map response = polizaInterface.subirArchivo(archivo, "automaticmail");
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, response ) );
    }

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearArchivo(@RequestBody PolizaDTO polizaDTO) throws Exception {
        polizaInterface.crearArchivo(polizaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body( new MensajeDTO(HttpStatus.CREATED, false,"Poliza registrada correctamente"));
    }

    @DeleteMapping("/eliminar/{publicId}/{idPoliza}")
    public ResponseEntity<MensajeDTO> eliminarArchivo(@PathVariable String publicId, int idPoliza) throws Exception{
        Map response = polizaInterface.eliminarArchivo(publicId, idPoliza);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, response ) );
    }

    @GetMapping("/obtener/usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO> listarPolizasPorUsuarios(@PathVariable int idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, polizaInterface.listarPolizasPorUsuarios(idUsuario) ));
    }

    @GetMapping("/obtener/{idPoliza}")
    public ResponseEntity<MensajeDTO> getPolizaDTO (@PathVariable int idPoliza) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, polizaInterface.getPolizaDTO(idPoliza)));
    }
}
