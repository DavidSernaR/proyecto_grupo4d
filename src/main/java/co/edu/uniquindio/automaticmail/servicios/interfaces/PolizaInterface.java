package co.edu.uniquindio.automaticmail.servicios.interfaces;

import co.edu.uniquindio.automaticmail.modelo.dto.PolizaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PolizaInterface {
    public Map subirArchivo(File archivo, String directory, PolizaDTO polizaDTO) throws Exception;
    public Map eliminarArchivo(String publicId, int idPoliza) throws Exception;
    public List<PolizaDTO> listarPolizasPorUsuarios(int idUsuario);
    public PolizaDTO getPolizaDTO(int idPoliza) throws Exception;
    public File convert(MultipartFile file) throws IOException;
}
