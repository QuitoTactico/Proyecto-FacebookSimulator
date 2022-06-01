
/**
 * En esta clase se crean y manejan los datos referentes a un usuario.
 * 
 * @author Esteban Vergara Giraldo
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 2
 */
public class Usuario
{
    private String usuario;
    private String clave;
    private String nombreCompleto;
    
    /**
     * Constructor
     * Se crea un usuario con las especificaciones ingresadas.
     * @param usuario El nombre de usuario que se prefiere
     * @param clave La contraseña que se quiere
     * @param nombreCompleto El nombre de la persona que crea el usuario
     */
    public Usuario(String usuario, String clave, String nombreCompleto) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
    }
    
    /**
     * Se retorna el usuario que quiere saber su nombre de usuario
     * @return El nombre de usuario solicitado
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Se retorna la clave del usuario
     * @return La clave solicitada
     */
    public String getClave() {
        return clave;
    }
    
    /**
     * Se retorna el nombre completo real del usuario
     * @return El nombre completo solicitado
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    

}
