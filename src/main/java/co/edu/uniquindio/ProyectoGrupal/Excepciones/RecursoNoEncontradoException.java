package co.edu.uniquindio.ProyectoGrupal.Excepciones;

public class RecursoNoEncontradoException extends RuntimeException{
    /**
     * Constructor que crea el mensaje de la excepción especificando el tipo y el ID del recurso.
     *
     * @param tipoRecurso El tipo de recurso que no se pudo encontrar (ej: "Usuario", "Alojamiento").
     * @param idRecurso El ID del recurso que causó la excepción.
     */
    public RecursoNoEncontradoException(String tipoRecurso, Object idRecurso) {
        super(String.format("El %s con ID %s no fue encontrado en el sistema.", tipoRecurso, idRecurso.toString()));
    }

    /**
     * Constructor que acepta un mensaje detallado.
     *
     * @param mensaje El mensaje descriptivo de la excepción.
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
