package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlantillaDTO {
    private int id;
    private String asunto;
    private String contenido;
    private int idUsuario;
    private int idPoliza;
    private int numDocumentoCliente;
}
