package co.edu.uniquindio.automaticmail.repositorios;

import co.edu.uniquindio.automaticmail.modelo.entidades.CorreoEmpresarial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorreoEmpresarialRepo extends JpaRepository<CorreoEmpresarial, Integer> {
    @Query("select c from CorreoEmpresarial c where c.id = :id")
    CorreoEmpresarial buscarCorreoEmpresarialPorId(int id);
}
