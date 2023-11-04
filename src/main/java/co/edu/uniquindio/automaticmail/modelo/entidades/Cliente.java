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
public class Cliente implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private int numDocumento;

    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false, length = 10)
    private String celular;
    @Column(nullable = false)
    private String correo;
    @Column(nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "cliente")
    private List<Plantilla> plantillas;
    @OneToMany(mappedBy = "cliente")
    private List<Poliza> polizas;

    @ManyToOne
    private Usuario usuario;
}
