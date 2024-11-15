import java.util.*;
import java.io.*;
/**
 * En esta clase se maneja la colección de todas las solicitudes de
 * amistad que hay entre los usuarios inscritos en la aplicación.
 * 
 * @author Esteban Vergara Giraldo  
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 4
 */
public class ColeccionSolicitudes
{
    /**
     * ArrayList donde se almacenan las referencias a los
     * pares de usuarios que se han enviado solicitud de amistad
     */
    protected ArrayList<Solicitud> solicitudes;
    
    /**
     * Constructor,
     * Se asigna memoria al nuevo ArrayList y se recuperan las solicitudes antiguas.
     */
    public ColeccionSolicitudes() throws FileNotFoundException,IOException {
        solicitudes = new ArrayList<>();
        File solicitudesFile = new File("solicitudesFile.txt");
        if(!solicitudesFile.exists()){ solicitudesFile.createNewFile();}
        Scanner scan = new Scanner(solicitudesFile);
        while(scan.hasNextLine()) {
            String linea = scan.nextLine();
            Scanner scannerLinea = new Scanner(linea);
            scannerLinea.useDelimiter(",");
            String usuario1 = scannerLinea.next();
            String usuario2 = scannerLinea.next();
            Solicitud a = new Solicitud(usuario1, usuario2);
            solicitudes.add(a);
        }
        scan.close();
    }
    
    /**
     * Se agrega una solicitud. Se presume que ya se ha chequeado y no existe
     * @param solicitud Referencia a la nueva solicitud que se trata de agregar
     * @return true si se pudo agregar la solicitud, false de lo contrario
     */
    public boolean agregarSolicitud(Solicitud solicitud) throws FileNotFoundException,IOException{
        File archivo = new File("solicitudesFile.txt");
        PrintWriter out = new PrintWriter(new FileWriter(archivo, true));
        out.println(solicitud.getUsuario1()+","+solicitud.getUsuario2());
        out.close();
        return solicitudes.add(solicitud);
    }
    
    /**
     * Se busca si la solicitud ya existe en la colección o no.
     * @param usuario1 Usuario que quiere ver si tiene solicitud con usuario2
     * @param usuario2 El usuario objetivo del usuario1
     * @return Si la solicitud existe, se retorna true, de lo contrario
     * se retorna false
     */
    public boolean buscarSolicitud(String usuario1, String usuario2) {
        for(Solicitud a: solicitudes) {
            if(a.getUsuario1().equals(usuario1) && a.getUsuario2().equals(usuario2)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Para imprimir los usernames de los que le han enviado una solicitud
     * de amistad a un usuario en específico.
     * @param usuario El usuario objetivo a quien le han mandado las solicitudes
     * @param amistades Un Arraylist con los usernames de los que ya son sus amigos
     */
    public void imprimirSolicitudes(String usuario, ArrayList<String> amistades) {
        ArrayList<String> seleccionadas = new ArrayList<>();
        for(Solicitud s: solicitudes) {
            if(s.getUsuario2().equals(usuario)) {
                boolean esAmigo = false;
                for(String a: amistades){
                    if(s.getUsuario1().equals(a)){
                        esAmigo = true;
                    }
                }
                if(esAmigo == false){
                    seleccionadas.add(s.getUsuario1());
                }
            }
        }
        if(seleccionadas.size() == 0){
            System.out.println("No tienes solicitudes que aceptar :(");
        }else{
            int i = 1;
            String conjugacion = " usuarios te han ";
            if(seleccionadas.size() == 1){conjugacion = " usuario te ha ";}
            System.out.println("¡"+seleccionadas.size() + conjugacion + "enviado solicitud de amistad!:");
            for(String s: seleccionadas) {
                System.out.println("- " + s);
            }
        }
    }
}
