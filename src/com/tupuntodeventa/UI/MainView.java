package com.tupuntodeventa.UI;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import com.tupuntodeventa.TL.*;

public class MainView {
    static Scanner in = new Scanner(System.in);
    static Random rdm = new Random();

    static UsuarioController gestorUsuarios = new UsuarioController();
    static CuponController gestorCupones = new CuponController();
    static  ProductoController gestorProductos = new ProductoController();
    static PuestoController gestorPuestos = new PuestoController();
    static OrdenController gestorOrdenes = new OrdenController();

    static PerfilView perfilView = new PerfilView();

    static String usuarioActivo;

    public static void main(String[] args) {
        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

//  despliega las opciones disponibles
    public static int menu() {
        int opcionMenu;
        do{
            System.out.println("\n=== Bienvenido a <mipuntodeventa.com> ===\n");
            System.out.println("[1]. Iniciar sesión");
            System.out.println("[2]. Registrar cliente");
            System.out.println("[0]. Salir");

            opcionMenu = in.nextInt();

            return opcionMenu;
        }while(opcionMenu != 0);

    }

//  procesa la opcion digitada por el usuario
    public static void procesarOpcion(int opcion) {
        switch (opcion){
            case 0:
                System.out.println("\nGracias por su visita :)");
                break;

            case 1: iniciarSesion();
                break;

            case 2: registrarCliente();
                break;

            default:
                System.err.println("Opcion invalida, intente de nuevo");
                int opcionMenu = menu();
                procesarOpcion(opcionMenu);
                break;
        }
    }

    //  solicita la informacion para el correcto proceso de verificacion del usuario
    public static void iniciarSesion(){
        in.nextLine();

        System.out.println("Ingrese el nombre de usuario");
        String nombreUsuario = in.nextLine();

        System.out.println("Ingrese su contraseña");
        String claveUsuario = in.nextLine();

        int verUsuario = verificarUsuario(nombreUsuario, claveUsuario);

//        si admin existe valide y ejecute segun las posibilidades de la bandera `err`
        int opcion;
        switch (verUsuario){
            case 0:
                System.out.println("Informacion verificada");
                perfilView.perfilMenu();
                break;

            case 1:
                System.err.println("Contraseña incorrecta, intente de nuevo");
                opcion = menu();
                procesarOpcion(opcion);
                break;

            case 2:
                System.err.println("No se encontro un usuario administrador en el sistema");
                System.err.println("Registre uno para continuar [ok]");
                registrarAdmin();

                break;
        }

    }

    //  verifica la informacion digitada y retorna el estado de la verificacion
    public static int verificarUsuario(String nombreUsuario, String contrasenaUsuario){
        int err = 0;
        boolean adm = false;

        ArrayList<String> listaInfoLogin = obtenerInfoLogin();

        if(listaInfoLogin.size() != 0){
            for(String infoUsuarioLogin : listaInfoLogin){

//              separar la informacion que viene en `infoUsuarioLogin` para su verificacion
                String nombreUsuarioSys = infoUsuarioLogin.split("_")[0],
                        contrasenaUsuarioSys = infoUsuarioLogin.split("_")[1],
                        tipoUsuario = infoUsuarioLogin.split("_")[3];

                if(tipoUsuario.equals("0")){
                    adm = true;
                }

                if(nombreUsuarioSys.equals(nombreUsuario)){
                    if(contrasenaUsuario.equals(contrasenaUsuarioSys)){
//                      una vez validado el nombre de usuario y la contrasena, la banderilla vuelve al valor `0` para especificar que no hay error en el proceso de validacion y agrego los datos correctamente validados en `usuario activo`
                        err = 0;
                        usuarioActivo = infoUsuarioLogin;
                    }else{
//                      si se valida el nombre de usuario, pero no la contrasena, contrasena incorrecta
                        err = 1;
                    }
                }
            }
        }

        if(!adm){
            err = 2;
        }

        return err;
    }

    //  solicita la informacion especifica para el correcto registro de un usuario administrador
    public static void registrarAdmin() {
        ArrayList<String> infoUsuario = solicitarInfoUsuario();

        int identificacion = Integer.parseInt(infoUsuario.get(0));
        int clave = Integer.parseInt(infoUsuario.get(1));
        String correoElectronico = infoUsuario.get(2);
        String nombreUsuario = infoUsuario.get(3);
        String contrasena = infoUsuario.get(4);
        String nombreCompleto = infoUsuario.get(5);
        String fechaNacimiento = infoUsuario.get(6);
        int edad = Integer.parseInt(infoUsuario.get(7));
        String genero = infoUsuario.get(8);
        int telefono = Integer.parseInt(infoUsuario.get(9));

        if(edad >= 18){
            boolean err = gestorUsuarios.registrarAdmin(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);

            if(err){
                System.err.println("El usuario ya esta agregado en el sistema.");
            }else{
                System.out.println("Usuario agregado con exito.");
            }
        }else{
            System.err.println("El usuario no cumple la edad minima");
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

    //  solicita la informacion especifica para el registro de un usuario cliente
    public static void registrarCliente() {
        ArrayList<String> infoUsuario = solicitarInfoUsuario();

        int identificacion = Integer.parseInt(infoUsuario.get(0));
        int clave = Integer.parseInt(infoUsuario.get(1));
        String correoElectronico = infoUsuario.get(2);
        String nombreUsuario = infoUsuario.get(3);
        String contrasena = infoUsuario.get(4);
        String nombreCompleto = infoUsuario.get(5);
        String fechaNacimiento = infoUsuario.get(6);
        int edad = Integer.parseInt(infoUsuario.get(7));
        String genero = infoUsuario.get(8);
        int telefono = Integer.parseInt(infoUsuario.get(9));


        System.out.println("== A continuacion le pediremos la informacion de su dirección [ok] ==");
        in.nextLine();

        ArrayList<String> infoDireccion = solicitarInfoDireccion();

        String direccionExacta = infoDireccion.get(0);
        String canton = infoDireccion.get(1);
        String distrito = infoDireccion.get(2);
        String provincia = infoDireccion.get(3);
        int distancia = Integer.parseInt(infoDireccion.get(4));

        if(edad >= 15){
            boolean err = gestorUsuarios.registrarCliente(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, direccionExacta, canton, distrito, provincia, distancia);

            if(err){
                System.err.println("El usuario ya esta agregado en el sistema.");
            }else{
                System.out.println("Usuario agregado con exito.");
            }
        }else{
            System.err.println("El usuario no cumple la edad minima");
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

//  solicita la informacion basica que cualquier usuario requiere
    public static ArrayList<String> solicitarInfoUsuario() {
        ArrayList<String> infoUsuario = new ArrayList<>();

        in.nextLine();
        System.out.println("Ingrese su identificacion:");
        int identificacion = in.nextInt();

        in.nextLine();
        System.out.println("Ingrese la clave numeral:");
        String clave = in.nextLine();

        System.out.println("Ingrese el correo electronico:");
        String correoElectronico = in.nextLine();

        System.out.println("Ingrese el nombre de usuario:");
        String nombreUsuario = in.nextLine();

        System.out.println("Ingrese su contrasena");
        String contrasena = in.nextLine();

        System.out.println("Ingrese el nombre completo:");
        String nombreCompleto = in.nextLine();

        System.out.println("Ingrese la fecha de nacimiento: (dd/mm/yyyy)");
        String fechaNacimiento = in.nextLine();

        System.out.println("Ingrese la edad:");
        String edad = in.nextLine();

        System.out.println("Ingrese el genero: (Masc / Fem)");
        String genero = in.nextLine();

        System.out.println("Ingrese el numero de telefono:");
        String telefono = in.nextLine();

        infoUsuario.add(String.valueOf(identificacion));
        infoUsuario.add(clave);
        infoUsuario.add(correoElectronico);
        infoUsuario.add(nombreUsuario);
        infoUsuario.add(contrasena);
        infoUsuario.add(nombreCompleto);
        infoUsuario.add(fechaNacimiento);
        infoUsuario.add(edad);
        infoUsuario.add(genero);
        infoUsuario.add(telefono);

        return infoUsuario;
    }

//  solicita la informacion requerida para agregar la direccion al usuario cliente
    public static ArrayList<String> solicitarInfoDireccion() {
        ArrayList<String> infoDireccion = new ArrayList<>();

        in.nextLine();
        System.out.println("Ingrese la direccion exacta:");
        String direccionExacta = in.nextLine();

        System.out.println("Ingrese el canton:");
        String canton = in.nextLine();

        System.out.println("Ingrese el distrito:");
        String distrito = in.nextLine();

        System.out.println("Ingrese la provincia:");
        String provincia = in.nextLine();

        System.out.println("Ingrese la distancia:");
        String distancia = in.nextLine();

        infoDireccion.add(direccionExacta);
        infoDireccion.add(canton);
        infoDireccion.add(distrito);
        infoDireccion.add(provincia);
        infoDireccion.add(distancia);

        return infoDireccion;
    }

//  obtengo los datos de los usuario en formato especifico para la validacion de inicio de sesion
    private static ArrayList<String> obtenerInfoLogin() {
        ArrayList<String> listaInfoLogin = gestorUsuarios.obtenerInfoLoginUsuarios();

        return listaInfoLogin;
    }
}