package co.edu.uniquindio.automaticmail.controladores;

import co.edu.uniquindio.automaticmail.modelo.dto.MensajeDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.TareaDTO;
import co.edu.uniquindio.automaticmail.servicios.interfaces.TareaInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaInterface tareaInterface;

    @PostMapping("/registrar")
    public ResponseEntity<MensajeDTO> registrarTarea(@RequestBody TareaDTO tareaDTO) throws Exception {
        tareaInterface.registrarTarea(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body( new MensajeDTO(HttpStatus.CREATED, false,"Tarea registrada correctamente"));
    }

    @PutMapping("/actualizar/{idTarea}")
    public ResponseEntity<MensajeDTO> actualizarTarea(@PathVariable int idTarea, @RequestBody TareaDTO tareaDTO) throws Exception {
        tareaInterface.actualizarTarea(idTarea, tareaDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Tarea actualizada correctamente"));
    }

    @PutMapping("/eliminar/{idTarea}")
    public ResponseEntity<MensajeDTO> eliminarTarea(@PathVariable int idTarea) throws Exception {
        tareaInterface.eliminarTarea(idTarea);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"Tarea eliminada correctamente"));
    }

    @GetMapping("/obtener/usuario/{idUsuario}")
    public ResponseEntity<MensajeDTO> listarTareasPorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, tareaInterface.listarTareasPorUsuario(idUsuario) ));
    }

    @GetMapping("/obtener/{idTarea}")
    public ResponseEntity<MensajeDTO> getTareaDTO(@PathVariable int idTarea) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, tareaInterface.getTareaDTO(idTarea) ));
    }
}
