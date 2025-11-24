package co.edu.uniquindio.ProyectoGrupal.repository;


import co.edu.uniquindio.ProyectoGrupal.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, String> {
}
