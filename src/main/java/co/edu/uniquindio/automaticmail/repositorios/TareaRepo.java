package co.edu.uniquindio.automaticmail.repositorios;

import co.edu.uniquindio.automaticmail.modelo.entidades.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepo extends JpaRepository<Tarea, Integer> {
    @Query("select t from Tarea t join t.usuario u where t.estado = true and u.id = :idUsuario")
    List<Tarea> listarTareasActivas(int idUsuario);
}
