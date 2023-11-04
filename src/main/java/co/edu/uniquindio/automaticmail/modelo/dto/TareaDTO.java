package co.edu.uniquindio.automaticmail.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class TareaDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private boolean estado;
    private int idUsuario;
}
