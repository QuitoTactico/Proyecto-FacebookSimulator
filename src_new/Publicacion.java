import java.util.Date;
/**
 * En esta clase se manejan los datos referentes a una publicación individual,
 * Tuve que modificar los Date para que fueran Strings, de otra forma no se guardaban.
 * 
 * @author Esteban Vergara Giraldo 
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 7
 */
public class Publicacion
{
    private String usuario;
    private String fechaHora;
    private String texto;
    
    /**
     * Constructor de una nueva publicación
     * @param usuario usuario que realiza la publicación
     * @param texto texto de la publicación
     */
    public Publicacion(String usuario, String texto) {
        this.usuario = usuario;
        Date fechaHora = new Date();
        this.fechaHora = fechaHora.toString();
        this.texto = texto;
    }
    
    /**
     * Constructor de una nueva publicación cuando esta ya tiene una fecha existente
     * @param usuario usuario que realiza la publicación
     * @param texto texto de la publicación
     * @param fechaHora la fecha y la hora de cuando fué publicada en formato String
     */
    public Publicacion(String usuario, String texto, String fechaHora) {
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.texto = texto;
    }
    
    /**
     * Se retorna el nombre de usuario del usuario que realiza la publicación
     * @return El username del autor
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Se retorna la fecha (y hora) de la publicación
     * @return La fecha y hora solicitados pero ya en forma de String
     */
    public String getFechaHora() {
        return fechaHora;
    }
    
    /**
     * Se retorna el texto que contiene la publicación
     * @return El texto solicitado
     */
    public String getTexto() {
        return texto;
    }
}
