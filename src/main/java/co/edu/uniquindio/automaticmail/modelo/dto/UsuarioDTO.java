package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String clave;
    private String correoPersonal;
    private int idCorreoEmpresarial;
}
