package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PolizaDTO {
    private int id;
    private String nombre;
    private String publicId;
    private String url;
    private boolean estado;
    private int numDocumentoCliente;
}
