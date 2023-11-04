package co.edu.uniquindio.automaticmail.servicios.interfaces;

import co.edu.uniquindio.automaticmail.modelo.dto.TareaDTO;

import java.util.List;

public interface TareaInterface {
    public int registrarTarea(TareaDTO tareaDTO) throws Exception;
    public int actualizarTarea(int idTarea, TareaDTO tareaDTO) throws Exception;
    public boolean eliminarTarea(int idTarea) throws Exception;
    public List<TareaDTO> listarTareasPorUsuario(int idUsuario);
    public TareaDTO getTareaDTO(int idTarea) throws Exception;
}
