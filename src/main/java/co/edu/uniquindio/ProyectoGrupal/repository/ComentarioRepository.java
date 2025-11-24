package co.edu.uniquindio.ProyectoGrupal.repository;

import co.edu.uniquindio.ProyectoGrupal.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, String> {
    List<Comentario> findByAlojamiento_Id(String idAlojamiento);

}
