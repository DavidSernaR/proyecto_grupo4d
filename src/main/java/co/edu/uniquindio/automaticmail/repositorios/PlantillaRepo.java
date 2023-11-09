package co.edu.uniquindio.automaticmail.repositorios;

import co.edu.uniquindio.automaticmail.modelo.entidades.Plantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantillaRepo extends JpaRepository<Plantilla, Integer> {
}
