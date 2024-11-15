import java.util.*;
import java.io.*;
/**
 * En esta clase se maneja la colección de todas las relaciones de
 * amistad que hay entre los usuarios inscritos en la aplicación.
 * 
 * @author Esteban Vergara Giraldo  
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 7
 */
public class ColeccionAmistades
{
    /**
     * ArrayList donde se almacenan las referencias a los
     * pares de usuarios que se han hecho amigos
     */
    protected ArrayList<Amistad> amistades;
    
    /**
     * Constructor,
     * Se asigna memoria al nuevo ArrayList y se recuperan las amistades antiguas.
     */
    public ColeccionAmistades() throws FileNotFoundException,IOException {
        amistades = new ArrayList<>();
        File amistadesFile = new File("amistadesFile.txt");
        if(!amistadesFile.exists()){ amistadesFile.createNewFile();}
        Scanner scan = new Scanner(amistadesFile);
        while(scan.hasNextLine()) {
            String linea = scan.nextLine();
            Scanner scannerLinea = new Scanner(linea);
            scannerLinea.useDelimiter(",");
            String usuario1 = scannerLinea.next();
            String usuario2 = scannerLinea.next();
            Amistad a = new Amistad(usuario1, usuario2);
            amistades.add(a);
            scannerLinea.close();
        }
        scan.close();
    }
    
    /**
     * Se agrega una amistad. Se presume que ya se ha chequeado y no existe
     * @param amistad Referencia a la nueva amistad que se trata de agregar
     * @return true si se pudo agregar la amistad, false de lo contrario
     */
    public boolean agregarAmistad(Amistad amistad) throws FileNotFoundException,IOException{
        File archivo = new File("amistadesFile.txt");
        PrintWriter out = new PrintWriter(new FileWriter(archivo, true));
        out.println(amistad.getUsuario1()+","+amistad.getUsuario2());
        out.close();
        return amistades.add(amistad);
    }
    
    /**
     * Se busca si la amistad ya existe en la colección o no.
     * @param usuario1 Usuario que quiere ver si tiene amistad con usuario2
     * @param usuario2 El usuario objetivo del usuario1
     * @return Si la amistad existe, se retorna true, de lo contrario
     * se retorna false
     */
    public boolean buscarAmistad(String usuario1, String usuario2) {
        for(Amistad a: amistades) {
            if(a.getUsuario1().equals(usuario1) && a.getUsuario2().equals(usuario2)) {
                return true;
            }
            if(a.getUsuario1().equals(usuario2) && a.getUsuario2().equals(usuario1)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Se buscan todas las amistades de un usuario
     * @param usuario El usuario objetivo
     * @return Un arraylist con los nombres de sus amigos
     */
    public ArrayList<String> getAmistadesDeUnUsuario(String usuario) {
        ArrayList<String> seleccionadas = new ArrayList<>();
        for(Amistad a: amistades) {
            if(a.getUsuario1().equals(usuario)) {
                seleccionadas.add(a.getUsuario2());
            }
            if(a.getUsuario2().equals(usuario)) {
                seleccionadas.add(a.getUsuario1());
            }
        }
        return seleccionadas;
    }
    
    /**
     * Para imprimir los usernames de los amigos de un usuario.
     * @param usuario El usuario objetivo
     */
    public void imprimirAmistades(String usuario) {
        ArrayList<String> seleccionadas = new ArrayList<>();
        for(Amistad a: amistades) {
            if(a.getUsuario1().equals(usuario)) {
                seleccionadas.add(a.getUsuario2());
            }
            if(a.getUsuario2().equals(usuario)) {
                seleccionadas.add(a.getUsuario1());
            }
        }
        if(seleccionadas.size() == 0){
            System.out.println("\n" + "Aún no tienes amigos :(" + "\n");
            System.out.println("¡Facebook es más divertido con amigos!,");
            System.out.println("envíale a alguien una SOLICITUD DE AMISTAD (función 2).");
        }else{
            String conjugacion = " amigos!:";
            if(seleccionadas.size() == 1){conjugacion = " amigo!:";}
            System.out.println();
            System.out.println("¡Tienes " + seleccionadas.size() + conjugacion);
            for(String a: seleccionadas) {
                System.out.println("- " + a);
            }
        }
    }
}
