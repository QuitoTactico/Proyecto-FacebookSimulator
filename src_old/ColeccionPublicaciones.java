import java.util.*;
import java.io.*;
/**
 * En esta clase se maneja la colección de las publicaciones
 * que han hecho todos los usuarios.
 * 
 * @author Esteban Vergara Giraldo
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 7
 */
public class ColeccionPublicaciones
{
    /**
     * ArrayList donde se almacenan las publicaciones hechas por todos los usuarios
     */
    protected ArrayList<Publicacion> publicaciones;
    
    /**
     * Constructor,
     * Se asigna memoria al nuevo ArrayList y se recuperan las publicaciones antiguas.
     */
    public ColeccionPublicaciones() throws FileNotFoundException,IOException{
        publicaciones = new ArrayList<>();
        File publicacionesFile = new File("publicacionesFile.txt");
        if(!publicacionesFile.exists()){ publicacionesFile.createNewFile();}
        Scanner scan = new Scanner(publicacionesFile);
        while(scan.hasNextLine()) {
            String linea = scan.nextLine();
            Scanner scannerLinea = new Scanner(linea);
            scannerLinea.useDelimiter("/");
            String usuario = scannerLinea.next();
            String texto = scannerLinea.next();
            String fechaHora = scannerLinea.next();
            Publicacion p = new Publicacion(usuario, texto, fechaHora);
            publicaciones.add(p);
        }
        scan.close();
    }
    
    /**
     * Se agrega una publicación. Aunque sean una nueva publicación, puede 
     * tener el mismo contenido que alguna anterior
     * @param p Referencia a la nueva publicación que se trata de agregar
     * @return true si se pudo agregar la publicación, false de lo contrario
     */
    public boolean agregarPublicacion(Publicacion p) throws IOException{
        File archivo = new File("publicacionesFile.txt");
        PrintWriter out = new PrintWriter(new FileWriter(archivo, true));
        out.println(p.getUsuario()+"/"+p.getTexto()+"/"+p.getFechaHora());
        out.close();
        return publicaciones.add(p);
    }
    
    /**
     * Se buscan las publicaciones del usuario ingresado (recientes primero)
     * @param usuario Usuario al que se le buscan las publicaciones
     * @return Retorna un ArrayList con todas las publicaciones que ha hecho
     * comenzando con las recientes, puede estar vacío si no ha hecho una aún.
     */
    public ArrayList<Publicacion> getPublicacionesDeUnUsuario(String usuario) {
        ArrayList<Publicacion> seleccionadas = new ArrayList<>();
        for(Publicacion p: publicaciones) {
            if(p.getUsuario().equals(usuario)) {
                seleccionadas.add(p);
            }
        }
        ArrayList<Publicacion> seleccionadasRecientes = new ArrayList<>(seleccionadas.size());
        for(int i = seleccionadas.size(); i > 0 ; i-- ){
            seleccionadasRecientes.add(seleccionadas.get(i-1));
        }
        return seleccionadasRecientes;
    }
    
    /**
     * Imprime las publicaciones del usuario ingresado (recientes primero).
     * Imprime el ArrayList de todas las publicaciones que ha hecho comenzando
     * con las recientes, es capaz de informar si no ha publicado nada aún.
     * @param usuario Usuario al que se le imprimirán las publicaciones
     */
    public void imprimirPublicaciones(String usuario) {
        ArrayList<Publicacion> seleccionadas = getPublicacionesDeUnUsuario(usuario);
        if(seleccionadas.size() == 0){
                System.out.println(usuario + " no ha publicado nada aún.");
        }else{
            int i = 1;
            System.out.println("Las publicaciones de " + usuario + "(recientes primero): ");
            for(Publicacion p: seleccionadas) {
                System.out.println();
                System.out.println(i + ". " + p.getFechaHora());
                System.out.println("   “" + p.getTexto() + "”");
                i++;
            }
        }
    }
}
