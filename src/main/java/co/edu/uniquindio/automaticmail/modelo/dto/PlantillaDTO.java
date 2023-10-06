package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlantillaDTO {
    private int id;
    private String contenido;
    private int numDocumentoCliente;
}
