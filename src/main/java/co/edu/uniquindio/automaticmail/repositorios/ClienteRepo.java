package co.edu.uniquindio.automaticmail.repositorios;

import co.edu.uniquindio.automaticmail.modelo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
    @Query("select c from Cliente c where c.numDocumento = :numDocumento")
    Cliente buscarClientePorNumDocumento(int numDocumento);
    @Query("select c from Cliente c join c.usuario u where c.estado = true and u.id = :idUsuario")
    List<Cliente> listarClientesActivos(int idUsuario);
}
