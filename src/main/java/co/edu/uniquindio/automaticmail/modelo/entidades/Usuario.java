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
public class Usuario implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private String clave;
    @Column(nullable = false)
    private String correoPersonal;

    @OneToMany(mappedBy = "usuario")
    private List<Cliente> clientes;
    @OneToMany(mappedBy = "usuario")
    private List<Tarea> tareas;

    @ManyToOne
    private CorreoEmpresarial correoEmpresarial;

    @ManyToMany
    private List<Plantilla> plantillas;
}
