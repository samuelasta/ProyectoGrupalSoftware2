package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, String> {
}
