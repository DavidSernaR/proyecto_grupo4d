package co.edu.uniquindio.automaticmail.repositorios;

import co.edu.uniquindio.automaticmail.modelo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    @Query("select u from Usuario u where u.id = :id")
    Usuario buscarUsuarioPorId(int id);
    @Query("select u from Usuario u where u.correoPersonal = :correoPersonal")
    Usuario buscarUsuarioPorCorreoPersonal(String correoPersonal);
}
