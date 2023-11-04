package co.edu.uniquindio.automaticmail.repositorios;

import co.edu.uniquindio.automaticmail.modelo.entidades.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolizaRepo extends JpaRepository<Poliza, Integer> {
    @Query("select p from Poliza p join p.cliente c where p.estado = true and c.numDocumento = :numDocumentoCliente")
    List<Poliza> listarPolizasActivasPorNumeroDocumentoCliente(int numDocumentoCliente);
}
