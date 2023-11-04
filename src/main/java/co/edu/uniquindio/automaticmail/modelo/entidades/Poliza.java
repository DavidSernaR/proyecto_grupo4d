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
public class Poliza implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String publicId;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "poliza")
    private List<Plantilla> plantillas;

    @ManyToOne
    private Cliente cliente;
}
