package co.edu.uniquindio.automaticmail.servicios.interfaces;

import co.edu.uniquindio.automaticmail.modelo.dto.PlantillaDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.PolizaDTO;

public interface EmailInterface {
    void enviarCorreo(PlantillaDTO plantillaDTO, PolizaDTO polizaDTO) throws Exception;
}
