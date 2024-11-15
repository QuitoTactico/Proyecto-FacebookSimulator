
/**
 * En esta clase se mantienen los usuarios
 * y se crea la relación entre estos.
 * 
 * @author Esteban Vergara Giraldo
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 5
 */
public class Solicitud
{
    private String usuario1;
    private String usuario2;
    
    /**
     * Constructor
     * Se crea una solicitud con los usuarios ingresados.
     * @param usuario1 Usuario emisor, que quiere tener amistad con usuario2
     * @param usuario2 El usuario receptor, objetivo del usuario1
     */
    public Solicitud(String usuario1, String usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }
    
    /**
     * Se retorna el usuario que creó la solicitud inicialmente
     * @return Uno de los usuarios de la amistad, el emisor
     */
    public String getUsuario1() {
        return usuario1;
    }
    
    /**
     * Se retorna el usuario objetivo de la solicitud de usuario1
     * @return El otro usuario de la amistad, el receptor
     */
    public String getUsuario2() {
        return usuario2;
    }
}
