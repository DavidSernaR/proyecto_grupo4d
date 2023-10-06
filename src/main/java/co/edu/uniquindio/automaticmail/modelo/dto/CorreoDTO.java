package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CorreoDTO {
    private int id;
    private String nombre;
    private int numDocumentoCliente;
}
