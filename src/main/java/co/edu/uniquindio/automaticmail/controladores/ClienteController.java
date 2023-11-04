package co.edu.uniquindio.automaticmail.controladores;

import co.edu.uniquindio.automaticmail.modelo.dto.ClienteDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.MensajeDTO;
import co.edu.uniquindio.automaticmail.servicios.interfaces.ClienteInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteInterface clienteInterface;

    @PostMapping("/registrar")
    public ResponseEntity<MensajeDTO> registrarCliente(@RequestBody ClienteDTO clienteDTO) throws Exception {
        clienteInterface.registrarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body( new MensajeDTO(HttpStatus.CREATED, false,"Cliente registrado correctamente"));
    }

    @PutMapping("/actualizar/{numDocumento}")
    public ResponseEntity<MensajeDTO> actualizarCliente(@PathVariable int numDocumento, @RequestBody ClienteDTO clienteDTO) throws Exception {
        clienteInterface.actualizarCliente(numDocumento, clienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Cliente actualizado correctamente"));
    }

    @PutMapping("/eliminar/{numDocumento}")
    public ResponseEntity<MensajeDTO> eliminarCliente(@PathVariable int numDocumento) throws Exception {
        clienteInterface.eliminarCliente(numDocumento);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Cliente eliminado correctamente"));
    }

    @GetMapping("/obtener/usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO> listarClientesPorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, clienteInterface.listarClientesPorUsuario(idUsuario) ));
    }

    @GetMapping("/obtener/{numDocumento}")
    public ResponseEntity<MensajeDTO> getClienteDTO(@PathVariable int numDocumento) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, clienteInterface.getClienteDTO(numDocumento) ));
    }

}
