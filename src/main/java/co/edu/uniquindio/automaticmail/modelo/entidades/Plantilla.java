package co.edu.uniquindio.automaticmail.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plantilla implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String asunto;
    @Column(nullable = false)
    private String contenido;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Poliza poliza;

    @ManyToMany(mappedBy = "plantillas")
    private List<Usuario> usuarios;
}
