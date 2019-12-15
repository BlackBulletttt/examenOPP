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

    static PerfilView perfilView = new PerfilView();

    static String usuarioActivo;

    public static void main(String[] args) {
        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

//  despliega las opciones disponibles
    public static int menu() {
        int opcionMenu = 0;
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

            case 3: registrarCupon();
                break;

            case 4: registrarProducto();
                break;

            case 5: listarUsuarios();
                break;

            case 6: listarCupones();
                break;

            case 7: listarProductos();
                break;

            default:
                System.err.println("Opcion invalida, intente de nuevo");
                int opcionMenu = menu();
                procesarOpcion(opcionMenu);
                break;
        }
    }

//  solicita la informacion basica que cualquier usuario requiere
    public static ArrayList<String> solicitarInfoUsuario() {
        ArrayList<String> infoUsuario = new ArrayList<>();

        in.nextLine();
        System.out.println("Ingrese la clave numeral:");
        String clave = in.nextLine();

        System.out.println("Ingrese el correo electronico:");
        String correoElectronico = in.nextLine();

        System.out.println("Ingrese el nombre de usuario:");
        String nombreUsuario = in.nextLine();

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

        infoUsuario.add(clave);
        infoUsuario.add(correoElectronico);
        infoUsuario.add(nombreUsuario);
        infoUsuario.add(nombreCompleto);
        infoUsuario.add(fechaNacimiento);
        infoUsuario.add(edad);
        infoUsuario.add(genero);
        infoUsuario.add(telefono);

        return infoUsuario;
    }

//  solicita la informacion especifica para el correcto registro de un usuario administrador
    public static void registrarAdmin() {
        ArrayList<String> infoUsuario = solicitarInfoUsuario();

        int clave = Integer.parseInt(infoUsuario.get(0));
        String correoElectronico = infoUsuario.get(1);
        String nombreUsuarrio = infoUsuario.get(2);
        String nombreCompleto = infoUsuario.get(3);
        String fechaNacimiento = infoUsuario.get(4);
        int edad = Integer.parseInt(infoUsuario.get(5));
        String genero = infoUsuario.get(6);
        int telefono = Integer.parseInt(infoUsuario.get(7));

        if(edad >= 18){
            boolean err = gestorUsuarios.registrarAdmin(clave, correoElectronico, nombreUsuarrio, nombreCompleto, fechaNacimiento, edad, genero, telefono);

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

//  solicita la informacion para el correcto proceso de verificacion del usuario
    public static void iniciarSesion(){
        in.nextLine();

        System.out.println("Ingrese el nombre de usuario");
        String nombreUsuario = in.nextLine();

        System.out.println("Ingrese su contraseña");
        String claveUsuario = in.nextLine();

        int verUsuario = verificarUsuario(nombreUsuario, claveUsuario);

        System.out.println(verUsuario);

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
                    System.err.println("El usuario ingresado no está registrado");
                    opcion = menu();
                    procesarOpcion(opcion);
                    break;

                case 3:
                    System.err.println("No se encontro un usuario administrador en el sistema");
                    System.err.println("Registre uno para continuar [ok]");
                    registrarAdmin();

                    break;
            }

    }

//  verifica la informacion digitada y retorna el estado de la verificacion
    public static int verificarUsuario(String nombreUsuario, String claveUsuario){
        int err = 0;
        boolean adm = false;

        ArrayList<String> listaInfoLogin = obtenerInfoLogin();

        if(listaInfoLogin.size() != 0){
            for(String infoUsuarioLogin : listaInfoLogin){

//              separar la informacion que viene en `infoUsuarioLogin` para su verificacion
                String nombreUsuarioSys = infoUsuarioLogin.split("_")[0];
                int claveUsuarioSys = Integer.parseInt(infoUsuarioLogin.split("_")[1]),
                        tipoUsuario = Integer.parseInt(infoUsuarioLogin.split("_")[2]);

                if(tipoUsuario == 0){
                    adm = true;
                }

                if(nombreUsuarioSys.equals(nombreUsuario)){
                    if(Integer.parseInt(claveUsuario) == claveUsuarioSys){
//                      una vez validado el nombre de usuario y la contrasena, la banderilla vuelve al valor `0` para especificar que no hay error en el proceso de validacion y agrego los datos correctamente validados en `usuario activo`
                        err = 0;
                        usuarioActivo = infoUsuarioLogin;
                    }else{
                        err = 1;
//                      contrasena incorrecta
                    }

//                  una vez encontrado romper el ciclo y evitar verificaciones innecesarias
                    break;
                }else{
                    err = 2;
//                  no se encuentra regitrado en el sistema
                }
            }
        }

        if(!adm){
            err = 3;
        }

        return err;
    }

//  obtengo los datos de los usuario en formato especifico para la validacion de inicio de sesion
    private static ArrayList<String> obtenerInfoLogin() {
        ArrayList<String> listaInfoLogin = gestorUsuarios.obtenerInfoLoginUsuarios();

        return listaInfoLogin;
    }

//  solicita la informacion especifica para el registro de un usuario cliente
    public static void registrarCliente() {
        ArrayList<String> infoUsuario = solicitarInfoUsuario();

        int clave = Integer.parseInt(infoUsuario.get(0));
        String correoElectronico = infoUsuario.get(1);
        String nombreUsuarrio = infoUsuario.get(2);
        String nombreCompleto = infoUsuario.get(3);
        String fechaNacimiento = infoUsuario.get(4);
        int edad = Integer.parseInt(infoUsuario.get(5));
        String genero = infoUsuario.get(6);
        int telefono = Integer.parseInt(infoUsuario.get(7));

        System.out.println("Ingrese su identificacion: ");
        int identificacion = in.nextInt();

        ArrayList<String> infoDireccion = solicitarInfoDireccion();

        String direccionExacta = infoDireccion.get(0);
        String canton = infoDireccion.get(1);
        String distrito = infoDireccion.get(2);
        String provincia = infoDireccion.get(3);
        int distancia = Integer.parseInt(infoDireccion.get(4));

        if(edad >= 15){
            boolean err = gestorUsuarios.registrarCliente(clave, correoElectronico, nombreUsuarrio, nombreCompleto, fechaNacimiento, edad, genero, telefono, identificacion, direccionExacta, canton, distrito, provincia, distancia);

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

//  funcionalidades que pueden ser movidas al perfil view ya que es ahi donde se mostraran estsa opciones
    public static void registrarCupon() {
        if(gestorCupones.obtenerInfoCupones().size() !=0){
            System.err.println("Hay cupones que no se han utilizado, debe esperar a que se acaben");
        }else{
            in.nextLine();
            System.out.println("Inrese la fecha de expiracion: (dd/mm/yyyy)");
            String fechaExpiracion = in.nextLine();

            System.out.println("Ingrese el estado: (redimino / no redimino)");
            String estado = in.nextLine();

            System.out.println("Ingrese el descuento: ");
            double descuento = in.nextDouble();

            for(int i=0; i < 50; i++){
                int codigo = Integer.parseInt(String.format("%06d", (rdm.nextInt(999999))));

                boolean err = gestorCupones.registrarCupon(fechaExpiracion, estado, descuento, codigo);

                if(err){
                    System.err.println("El cupon ya esta agregado en el sistema.");
                }else{
                    System.out.println("Cupon agregado con exito.");
                }
            }
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

    public static void registrarProducto() {
        int opcionProducto = 0;

        do{
            System.out.println("¿Que tipo de producto desea registrar?");
            System.out.println("[1]. Sencillo");
            System.out.println("[2]. Combo");
            System.out.println("[0]. Regresar");

            opcionProducto = in.nextInt();

            switch (opcionProducto){
                case 0:
                    int opcionMenu = menu();
                    procesarOpcion(opcionMenu);
                    break;

                case 1: registrarProductoSencillo();
                    break;

                case 2: registrarProductoCombo();
                    break;

                default:
                    System.err.println("Opcion invalida, intente de nuevo");
                    break;
            }
        }while(opcionProducto > 2);
    }

    public static void registrarProductoSencillo() {
        System.out.println("Ingrese un codigo para el producto: ");
        int codigo = in.nextInt();

        in.nextLine();
        System.out.println("Ingrese la descripcion del plato: ");
        String descripcion = in.nextLine();

        System.out.println("Ingrese el precio del platillo: ");
        double precio = in.nextInt();

        int err = gestorProductos.registrarSencillo(codigo, descripcion, precio);

        switch (err){
            case 0:
                System.out.println("Producto agregado con exito.");
                break;

            case 1:
                System.err.println("El producto ya esta agregado en el sistema.");
                break;
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

    public static void registrarProductoCombo() {
        System.out.println("Ingrese un codigo para el producto: ");
        int codigo = in.nextInt();

        in.nextLine();
        System.out.println("Ingrese el nombre del combo: ");
        String nombre = in.nextLine();

        System.out.println("Ingrese el precio total del combo: ");
        int total = in.nextInt();

        System.out.println("Ingrese el codigo del plato que desea agregar:");
        int plato = in.nextInt();

        int err = gestorProductos.registrarCombo(codigo, nombre, total, plato);

        switch (err){
            case 0:
                System.out.println("Combo aregado con exito.");
                break;

            case 1:
                System.out.println("El producto ya esta agregado en el sistema.");
                break;
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

    public static void listarUsuarios() {
        ArrayList<String> listaInfoUsuarios = gestorUsuarios.obtenerInfoUsuarios();

        for(String infoUsuario : listaInfoUsuarios){
            System.out.println(infoUsuario);
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

    public static void listarCupones() {
        ArrayList<String> listaInfoCupones = gestorCupones.obtenerInfoCupones();

        for(String infoCupon : listaInfoCupones){
            System.out.println(infoCupon);
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }

    public static void listarProductos() {
        ArrayList<String> listaInfoProductos = gestorProductos.obtenerInfoProductos();

        for(String infoProducto : listaInfoProductos){
            System.out.println(infoProducto);
        }

        int opcionMenu = menu();
        procesarOpcion(opcionMenu);
    }
}