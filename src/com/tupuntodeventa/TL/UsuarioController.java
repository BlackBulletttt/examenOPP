package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Factory.DAOFactory;
import com.tupuntodeventa.BL.Puesto.Obj.Puesto;
import com.tupuntodeventa.BL.Usuario.IUsuarioDAO;
import com.tupuntodeventa.BL.Usuario.Obj.*;

import java.util.ArrayList;

public class UsuarioController extends CoreController{
    private DAOFactory factory = DAOFactory.getDAOFactory(1);
    private IUsuarioDAO usuarioDAO = factory.getUsuariosDAO();

    private ArrayList<Usuario> listaUsuarios;

//    constructor que se encarga de obtener TODOS lo usuarios de la base de datos, cada vez que el controlador es instanciado
    public UsuarioController() throws Exception {
        listaUsuarios = usuarioDAO.obtenerUsuarios();
    }

    public boolean registrarAdmin(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) throws Exception {
        boolean err = false;

        Admin nuevoAdmin = new Admin(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);

        for(Usuario usuario : listaUsuarios){
            if(usuario.getIdentificacion() == nuevoAdmin.getIdentificacion()){
                err = true;
            }
        }

        if(!err){
            usuarioDAO.registrarAdmin(nuevoAdmin);
        }

        return err;
    }

    public boolean registrarCliente(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, String direccionExacta, String canton, String distrito, String provincia, int distancia) throws Exception {
        Cliente nuevoCliente = new Cliente(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, direccionExacta, canton, distrito, provincia, distancia);
        boolean err = false;

        for(Usuario usuario : listaUsuarios){
            if(usuario.getIdentificacion() == nuevoCliente.getIdentificacion()){
                err = true;
            }
        }

        if(!err){
            usuarioDAO.registrarCliente(nuevoCliente);
        }

        return err;
    }

    public int registrarEmpleado(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, String nombrePuesto) throws Exception {
        Puesto puesto = logicaPuestos.obtenerPuesto(nombrePuesto);
        int err = 0;

        if(puesto != null){
            Empleado nuevoEmpleado = new Empleado(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, puesto);
            boolean errRegistro = false;

            for(Usuario usuario : listaUsuarios){
                if(usuario.getIdentificacion() == nuevoEmpleado.getIdentificacion()){
                    errRegistro = true;
                }
            }

            if(!errRegistro){
                usuarioDAO.registrarEmpleado(nuevoEmpleado);
                err = 1;
            }
        }else{
            err = 2;
        }

        return err;
    }

    public ArrayList<String> obtenerInfoUsuarios() throws Exception {
        ArrayList<Usuario> listaUsuarios = usuarioDAO.obtenerUsuarios();
        ArrayList<String> listaInfoUsuarios = new ArrayList<>();

        for(Usuario usuario : listaUsuarios){
            listaInfoUsuarios.add(usuario.toString());
        }

        return  listaInfoUsuarios;
    }

	public ArrayList<String> obtenerInfoLoginUsuarios() throws Exception {
	    ArrayList<Usuario> listaUsuarios = usuarioDAO.obtenerUsuarios();
        ArrayList<String> listaInfoLogin = new ArrayList<>();

        for(Usuario usuario : listaUsuarios){
            listaInfoLogin.add(usuario.getInfoLogin());
        }

        return listaInfoLogin;
    }
}
