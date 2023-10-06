package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private int id;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String clave;
    private String correoPersonal;
    private int idCorreoEmpresarial;
}
