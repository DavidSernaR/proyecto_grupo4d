package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MensajeDTO {
    private int id;
    private String asunto;
    private int idPlantilla;
    private int idUsuario;
}
