import java.util.*;
import java.io.*;
/**
 * Clase principal del sistema, Desde aquí se le pregunta al usuario lo que
 * quiere hacer por medio de opciones numeradas.
 * 
 * @author Esteban Vergara Giraldo
 * @author John Alejandro Gonzales
 * @author Andres Julian Gil Nagles
 * @version 27
 */
public class Principal
{
    /** Instancia de la colección con ArrayList de usuarios */
    protected static ColeccionUsuarios coleccionUsuarios;
    /** Instancia de la colección con ArrayList de publicaciones */
    protected static ColeccionPublicaciones coleccionPublicaciones;
    /** Instancia de la colección con ArrayList de solicitudes */
    protected static ColeccionSolicitudes coleccionSolicitudes;
    /** Instancia de la colección con ArrayList de amistades */
    protected static ColeccionAmistades coleccionAmistades;
    
    /** El usuario que está actualmente utilizando el sistema. */
    protected static Usuario usuarioActivo;

    /** Función que recibe lo que escribas con el teclado. */
    protected static Scanner in = new Scanner(System.in);
    
    /**
     * El método que inicia todo el proyecto, desde donde todo empieza.
     * Puede usarse directamente desde esta clase o puede hacerse remotamente
     * desde la clase EntrarAlSistema
     */
    public static void main(String [] args) throws FileNotFoundException,IOException {
        inicializarColecciones();
        menuPrincipal();
    }
    
    /**
     * Instancia cada una de las colecciones, reservándole 
     * espacio en memoria a sus arrays correspondientes.
     */
    public static void inicializarColecciones() throws FileNotFoundException,IOException {
        coleccionUsuarios = new ColeccionUsuarios();
        coleccionPublicaciones = new ColeccionPublicaciones();
        coleccionSolicitudes = new ColeccionSolicitudes();
        coleccionAmistades = new ColeccionAmistades();
    }

    /**
     * El primer menú que ve el usuario, sirve para crearse
     * una cuenta (1) y luego ingresar al sistema con ella (2).
     */
    public static void menuPrincipal() throws IOException{
        int opcion;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("1. Crear usuario");
            System.out.println("2. Ingresar al sistema");
            System.out.println("9. Salir del sistema");
            System.out.println("Por favor digite su opción");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    menuCrearUsuario();
                    break;
                case 2:
                    menuIngresar();
                    break;
                case 9:
                    System.out.println("¡Adiós!, vuelve pronto");
                    System.out.println("[El sistema se ha cerrado.]");
            }
        } while (opcion != 9);
    }

    /**
     * Opción 1, Con este menú se crean cuentas con el username y 
     * contraseña preferidos, además de tu propio nombre.
     * Primer menú al que debe ingresar un usuario que usa por primera vez el sistema.
     */
    public static void menuCrearUsuario() throws IOException {
        boolean exito = false;
        String usuario;
        String clave;
        String nombreCompleto;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("Favor ingresar el nuevo usuario: (o fin para terminar)");
            usuario = in.nextLine();
            if (usuario.equals("fin")) return;
            if(coleccionUsuarios.buscarUsuario(usuario) != null) {
                System.out.println("Ese usuario ya existe");
            } else {
                exito = true;
            }
        } while (!exito);
        System.out.println("Favor ingresar la clave: ");
        clave = in.nextLine();
        System.out.println("Favor ingresar el nombre completo: ");
        nombreCompleto = in.nextLine();
        Usuario nuevoUsuario = new Usuario(usuario, clave, nombreCompleto);
        coleccionUsuarios.agregarUsuario(nuevoUsuario);
        System.out.println("Usuario creado correctamente");
        System.out.println("Bienvenido(a) al sistema");
        return;
    }

    /**
     * Opción 2, Con este menú se puede ingresar al sistema con el usuario y 
     * contraseña que hayas creado.
     * Segundo menú al que debe ingresar un usuario que usa por primera vez el sistema.
     */
    public static void menuIngresar() throws FileNotFoundException,IOException{
        boolean exito = false;
        String usuario;
        String clave;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("Favor ingresar su usuario: (fin para terminar)");
            usuario = in.nextLine();
            if(usuario.equals("fin")) return;
            System.out.println("Favor ingresar su clave: ");
            clave = in.nextLine();
            Principal.usuarioActivo = coleccionUsuarios.buscarUsuarioClave(usuario, clave);
            if(coleccionUsuarios.buscarUsuario(usuario) == null) {
                System.out.println("Usuario(a) ingresado no existe en el sistema");
            }else if(usuarioActivo == null) {
                System.out.println("Combinación usuario y clave inválida");
            } else {
                exito = true;
            }
        } while (!exito);
        System.out.println("Hola, " + Principal.usuarioActivo.getNombreCompleto());
        System.out.println("Estás en el sistema");
        menuUsuarioActivo();
    }
    
    /**
     * Haz ingresado al sistema, desde aquí se despliegan ciertas funciones 
     * numeradas a las que puedes acceder, como hacer una publicación, ver 
     * las que haz hecho, agregar amigos, y ver sus publicaciones también.
     * Menú al que se llega si el usuario existe y se 
     * realiza correctamente su ingreso al sistema. 
     */
    public static void menuUsuarioActivo() throws FileNotFoundException,IOException{
        int opcion;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("1. Crear una publicación");
            System.out.println("2. Enviar solicitud de amistad");
            System.out.println("3. Aceptar solicitudes de amistad");
            System.out.println("4. Ver mis amigos");
            System.out.println("5. Ver las publicaciones de un(a) amigo(a)");
            System.out.println("6. Ver mis publicaciones");
            System.out.println("9. Regresar");
            System.out.println("Por favor digite su opción");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    menuCrearPublicacion();
                    break;
                case 2:
                    menuPedirAmistad();
                    break;
                case 3:
                    menuAceptarSolicitudes();
                    break;
                case 4:
                    menuVerAmigos();
                    break;
                case 5:
                    menuVerPublicacionesAmigo();
                    break;
                case 6:
                    menuVerPublicacionesPropias();
                    break;
                case 7:
                    easterEgg();
                    break;
            }
        } while (opcion != 9);
    }
    
    /**
     * Función 1, Puedes escribir cualquier cosa, esto será publicado.
     * Tus amigos y tú pueden ver tus publicaciones
     */
    public static void menuCrearPublicacion() throws FileNotFoundException,IOException{
        String texto;
        in = new Scanner(System.in);
        System.out.println("========================");
        System.out.println("Favor ingresar el texto de su publicación: ");
        texto = in.nextLine();
        if(texto.isEmpty()){texto = " ";}
        Publicacion publicacion = new Publicacion(Principal.usuarioActivo.getUsuario(), texto);
        Principal.coleccionPublicaciones.agregarPublicacion(publicacion);
        System.out.println("La publicación ha sido agregada");
    }

    /**
     * Función 2, Puedes hacerte amigo de otro usuario, sólo tienes que 
     * escribir su username y esperar a que te acepte.
     * Se confirmará si el usuario existe y que no eres tú mismo, luego revisará si
     * aún no son amigos. Si pasa todos los filtros, se creará la solicitud de amistad.
     * 
     * El otro usurio debe aceptar la solicitud antes de poder ser amigos.
     */
    public static void menuPedirAmistad() throws FileNotFoundException,IOException{
        boolean exito = false;
        String usuario;
        Usuario amigo;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("Favor ingresar el(la) usuario(a): (fin para terminar)");
            usuario = in.nextLine();
            if(usuario.equals("fin")) return;
            amigo = coleccionUsuarios.buscarUsuario(usuario);
            if(amigo == null) {
                System.out.println("Usuario(a) no existe en el sistema");
                continue;
            }
            if(Principal.coleccionAmistades.buscarAmistad(Principal.usuarioActivo.getUsuario(), usuario)) {
                System.out.println("La amistad ya existe");
                continue;
            }
            if(Principal.coleccionSolicitudes.buscarSolicitud(Principal.usuarioActivo.getUsuario(), usuario)) {
                System.out.println("La solicitud de amistad ya fué enviada, aún espera ser aceptada");
                break;
            }
            if(Principal.coleccionSolicitudes.buscarSolicitud(usuario, Principal.usuarioActivo.getUsuario())) {
                System.out.println("El usuario(a) ingresado ya te envió a tí una");
                System.out.println("solicitud de amistad. ¿Deseas aceptarla?:");
                System.out.println("1. Sí" + "\n" + "2. No");
                String respuesta = (in.nextLine()).toLowerCase();
                if((respuesta.equals("1")) || (respuesta.equals("si")) || (respuesta.equals("sí"))){
                    Amistad amistad = new Amistad(Principal.usuarioActivo.getUsuario(), amigo.getUsuario());
                    Principal.coleccionAmistades.agregarAmistad(amistad);
                    System.out.print(Principal.usuarioActivo.getNombreCompleto() + ", ");
                    System.out.println("la amistad ha sido creada");
                    System.out.println("¡Ahora " + usuario + " y tú son amigos!");
                    exito = true;
                    break;
                }else{ System.out.println("Ok");}
                continue;
            }
            if(usuario.equals(Principal.usuarioActivo.getUsuario())) {
                System.out.println("No tiene sentido que te pidas amistad a tí mismo(a)");
                continue;
            }
            Solicitud solicitud = new Solicitud(Principal.usuarioActivo.getUsuario(), amigo.getUsuario());
            Principal.coleccionSolicitudes.agregarSolicitud(solicitud);
            System.out.print(Principal.usuarioActivo.getNombreCompleto() + ", ");
            System.out.println("la solicitud de amistad ha sido enviada.");
            System.out.println("¡Ahora espera a que " + usuario + " la acepte para ser amigos!");
            exito = true;
        } while (!exito);
    }
    
    /**
     * Función 3, Desde aquí puedes aceptar las solicitudes que te han enviado, se desplegará
     * una lista con ellas y luego solo tienes que escribir el usuario que quieras
     * tener como amigo.
     */
    public static void menuAceptarSolicitudes() throws FileNotFoundException,IOException{
        String yo = Principal.usuarioActivo.getUsuario();
        boolean exito = false;
        String usuario;
        Usuario amigo;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            Principal.coleccionSolicitudes.imprimirSolicitudes(yo, coleccionAmistades.getAmistadesDeUnUsuario(yo));
            System.out.println("\n" + "Escribe el usuario que quieras aceptar como amigo:");
            System.out.println("(o fin para terminar)");
            usuario = in.nextLine();
            if(usuario.equals("fin")) return;
            amigo = coleccionUsuarios.buscarUsuario(usuario);
            if(amigo == null) {
                System.out.println("Usuario(a) no existe en el sistema");
                continue;
            }
            if(Principal.coleccionAmistades.buscarAmistad(yo, usuario)) {
                System.out.println("La amistad ya existe");
                continue;
            }
            if(Principal.coleccionSolicitudes.buscarSolicitud(usuario, yo) == false) {
                System.out.println("El usuario(a) ingresado no te ha enviado una solicitud");
                System.out.println("de amistad. Selecciona a alguien de la lista");
                continue;
            }
            if(usuario.equals(Principal.usuarioActivo.getUsuario())) {
                System.out.println("No tiene sentido que aceptes tu propia solicitud");
                continue;
            }
            Amistad amistad = new Amistad(Principal.usuarioActivo.getUsuario(), amigo.getUsuario());
            Principal.coleccionAmistades.agregarAmistad(amistad);
            System.out.print(Principal.usuarioActivo.getNombreCompleto() + ", ");
            System.out.println("la amistad ha sido creada");
            System.out.println("¡Ahora " + usuario + " y tú son amigos!");
            exito = true;
        } while (!exito);
    }
    
    /**
     * Función 4, se desplegará una lista con todos tus amigos.
     * Puedes ver sus publicaciones con la función 5.
     */
    public static void menuVerAmigos() {
        String usuario = Principal.usuarioActivo.getUsuario();
        Principal.coleccionAmistades.imprimirAmistades(usuario);
    }

    /**
     * Función 5, Luego de hacerte amigo de otro usuario, puedes ver sus
     * publicaciones, sólo tienes que escribir su username.
     * Se confirmará si el usuario existe y que no eres tú mismo,
     * luego revisará si efectivamente son amigos. Si pasa todos los filtros,
     * mandará a imprimir las publicaciones de tu amigo empezando por las recientes.
     */
    public static void menuVerPublicacionesAmigo() {
        boolean exito = false;
        String usuario;
        Usuario amigo;
        do {
            in = new Scanner(System.in);
            System.out.println("========================");
            System.out.println("Favor ingresar el(la) amigo(a) cuyas publicaciones quieres ver:");
            System.out.println("(escribe fin para terminar)");
            usuario = in.nextLine();
            if(usuario.equals("fin")) return;
            amigo = coleccionUsuarios.buscarUsuario(usuario);
            if(amigo == null) {
                System.out.println("\n" + "Usuario(a) no existe en el sistema");
                continue;
            }
            if(usuario.equals(Principal.usuarioActivo.getUsuario())) {
                System.out.println("\n" + "Recuerda usar la función 4 para ver tus propias publicaciones.");
                menuVerPublicacionesPropias();
                exito = true;
                break;
            }
            if((Principal.coleccionAmistades.buscarAmistad(Principal.usuarioActivo.getUsuario(), usuario) == false)
            && (Principal.coleccionSolicitudes.buscarSolicitud(Principal.usuarioActivo.getUsuario(), usuario) == true)) {
                System.out.println("Aún no eres amigo de " + usuario + ",");
                System.out.println("pero ya le enviaste una solicitud.");
                System.out.println("Para ver las publicaciones de " + usuario + ", debes");
                System.out.println("esperar a que tu SOLICITUD DE AMISTAD sea ACEPTADA.");
                break;
            }
            if(Principal.coleccionAmistades.buscarAmistad(Principal.usuarioActivo.getUsuario(), usuario) == false) {
                System.out.println("Aún no eres amigo de " + usuario + ".");
                System.out.println("Para ver las publicaciones de " + usuario + ", primero debes");
                System.out.println("enviarle una SOLICITUD DE AMISTAD.");
                break;
            }
            Principal.coleccionPublicaciones.imprimirPublicaciones(usuario);
            exito = true;
        } while (!exito);
    }

    /**
     * Función 6, Puedes ver tus propias publicaciones.
     * Es automático, no tienes que ingresar nada, se mandarán 
     * a imprimir tus publicaciones empezando por las recientes.
     */
    public static void menuVerPublicacionesPropias() {
        String usuario = Principal.usuarioActivo.getUsuario();
        Principal.coleccionPublicaciones.imprimirPublicaciones(usuario);
    }
    
    /**
     * Función 7, Just do it.
     */
    public static void easterEgg() throws FileNotFoundException{
        File bruh = new File("README.TXT");
        Scanner scan = new Scanner(bruh);
        while(scan.hasNextLine()) {
            String linea = scan.nextLine();
            System.out.println(linea);
        }
        scan.close();
    }
}
