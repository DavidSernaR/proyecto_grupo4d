package co.edu.uniquindio.automaticmail.servicios.implementacion;

import co.edu.uniquindio.automaticmail.modelo.dto.TareaDTO;
import co.edu.uniquindio.automaticmail.modelo.entidades.Tarea;
import co.edu.uniquindio.automaticmail.repositorios.TareaRepo;
import co.edu.uniquindio.automaticmail.repositorios.UsuarioRepo;
import co.edu.uniquindio.automaticmail.servicios.interfaces.TareaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TareaInterfaceImpl implements TareaInterface {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private TareaRepo tareaRepo;

    @Override
    public int registrarTarea(TareaDTO tareaDTO) throws Exception {
        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setFecha(tareaDTO.getFecha());
        tarea.setEstado(true);
        tarea.setUsuario(usuarioRepo.buscarUsuarioPorId(tareaDTO.getIdUsuario()));

        tareaRepo.save(tarea);

        return tarea.getId();
    }

    @Override
    public int actualizarTarea(int idTarea, TareaDTO tareaDTO) throws Exception {

        Optional<Tarea> buscarTarea = tareaRepo.findById(idTarea);

        if (buscarTarea.isEmpty()) {
            throw new Exception("La actividad con id " + idTarea + " no existe");
        }

        Tarea tareaActualizada = buscarTarea.get();

        tareaActualizada.setNombre(tareaDTO.getNombre());
        tareaActualizada.setDescripcion(tareaDTO.getDescripcion());
        tareaActualizada.setFecha(tareaDTO.getFecha());

        tareaRepo.save(tareaActualizada);

        return tareaActualizada.getId();
    }

    @Override
    public boolean eliminarTarea(int idTarea) throws Exception {

        Optional<Tarea> buscarTarea = tareaRepo.findById(idTarea);

        if (buscarTarea.isEmpty()) {
            throw new Exception("La actividad con id " + idTarea + " no existe");
        }

        Tarea tareaEliminada = buscarTarea.get();

        tareaEliminada.setEstado(false);

        tareaRepo.save(tareaEliminada);

        return tareaEliminada.isEstado();
    }

    @Override
    public List<TareaDTO> listarTareasPorUsuario(int idUsuario) {
        List<Tarea> listaTareas = tareaRepo.listarTareasActivas(idUsuario);
        List<TareaDTO> listaTareasDTO = new ArrayList<>();

        for (Tarea tarea : listaTareas) {
            listaTareasDTO.add(convertirADTO(tarea));
        }

        return listaTareasDTO;
    }

    @Override
    public TareaDTO getTareaDTO(int idTarea) throws Exception {

        Optional<Tarea> buscarTarea = tareaRepo.findById(idTarea);

        if (buscarTarea.isEmpty()) {
            throw new Exception("La actividad con id " + idTarea + " no existe");
        }

        return convertirADTO(buscarTarea.get());
    }

    private TareaDTO convertirADTO(Tarea tarea){
        TareaDTO tareaDTO = new TareaDTO(
                tarea.getId(),
                tarea.getNombre(),
                tarea.getDescripcion(),
                tarea.getFecha(),
                tarea.isEstado(),
                tarea.getUsuario().getId()
        );

        return tareaDTO;
    }
}
