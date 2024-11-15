import java.util.*;
import java.io.*;
/**
 * En esta clase se maneja la colección de los usuarios que se han
 * inscrito en la aplicación.
 * 
 * @author Esteban Vergara Giraldo 
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 10
 */
public class ColeccionUsuarios
{
    /**
     * ArrayList donde se almacenan las referencias a los usuarios
     */
    protected ArrayList<Usuario> usuarios;
    
    /**
     * Constructor
     * Se asigna memoria al nuevo ArrayList y se recuperan los usuarios antiguos
     */
    public ColeccionUsuarios() throws FileNotFoundException, IOException {
        usuarios = new ArrayList<>();
        File usuariosFile = new File("usuariosFile.txt");
        if(!usuariosFile.exists()){ usuariosFile.createNewFile();}
        Scanner scan = new Scanner(usuariosFile);
        while(scan.hasNextLine()) {
            String linea = scan.nextLine();
            Scanner scannerLinea = new Scanner(linea);
            scannerLinea.useDelimiter(",");
            String usuario = scannerLinea.next();
            String clave = scannerLinea.next();
            String nombreCompleto = scannerLinea.next();
            Usuario u = new Usuario(usuario, clave, nombreCompleto);
            usuarios.add(u);
        }
        scan.close();
    }

    /**
     * Se agrega un usuario. Se presume que ya se ha chequeado y no existe
     * @param usuario Referencia al nuevo usuario que se trata de agregar
     * @return true si se pudo agregar el usuario, false de lo contrario
     */
    public boolean agregarUsuario(Usuario usuario) throws IOException{
        File archivo = new File("usuariosFile.txt");
        PrintWriter out = new PrintWriter(new FileWriter(archivo, true));
        out.println(usuario.getUsuario()+","+usuario.getClave()+","+usuario.getNombreCompleto());
        out.close();
        return usuarios.add(usuario);
    }
    
    /**
     * Se busca si el usuario ya existe en la colección o no.
     * @param usuarioBuscado Usuario que se trata de agregar
     * @return Si el usuario existe, se returna la referencia, de lo contrario
     * se retorna null
     */
    public Usuario buscarUsuario(String usuarioBuscado) {
        for(Usuario u: usuarios) {
            if(u.getUsuario().equals(usuarioBuscado)) {
                return u;   
            }
        }
        return null;
    }
    
    /**
     * Buscar si el usuario y clave que digita el usuario son válidos
     * @param usuario usuario que ingresa el usuario
     * @param clave clave que ingresa el usuario
     * @return Se retorna la referencia del usuario que tenga ese
     * nickname y esa contraseña. Si nadie los tiene, se retorna null
     */
    public Usuario buscarUsuarioClave(String usuario, String clave) {
        for(Usuario u: usuarios) {
            if(u.getUsuario().equals(usuario) && u.getClave().equals(clave)) {
                return u;
            }
        }
        return null;
    }
}
