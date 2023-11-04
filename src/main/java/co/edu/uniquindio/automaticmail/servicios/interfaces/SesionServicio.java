package co.edu.uniquindio.automaticmail.servicios.interfaces;

import co.edu.uniquindio.automaticmail.modelo.dto.SesionDTO;
import co.edu.uniquindio.automaticmail.modelo.dto.TokenDTO;

public interface SesionServicio {
    public TokenDTO login(SesionDTO sesionDTO);
}
