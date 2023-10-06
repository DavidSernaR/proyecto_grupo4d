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
    private String descripcion;
    private LocalDate fecha;
    private int idUsuario;
}
