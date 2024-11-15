
/**
 * En esta clase se mantienen los usuarios
 * y se crea la relación entre estos.
 * 
 * @author Esteban Vergara Giraldo
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 2
 */
public class Amistad
{
    private String usuario1;
    private String usuario2;
    
    /**
     * Constructor
     * Se crea una amistad con los usuarios ingresados.
     * @param usuario1 Usuario que tiene amistad con usuario2
     * @param usuario2 El usuario amigo del usuario1
     */
    public Amistad(String usuario1, String usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }
    
    /**
     * Se retorna el usuario que intentó crear la amistad inicialmente
     * @return Uno de los usuarios de la amistad, quien envió la solicitud hace tiempo
     */
    public String getUsuario1() {
        return usuario1;
    }
    
    /**
     * Se retorna el usuario objetivo de la amistad de usuario1
     * @return El otro usuario de la amistad, quien aceptó la solicitud
     */
    public String getUsuario2() {
        return usuario2;
    }
}
