package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private int numDocumento;
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;
    private boolean estado;
    private int idUsuario;
}
