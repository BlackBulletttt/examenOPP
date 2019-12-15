package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Puesto.Obj.Puesto;
import com.tupuntodeventa.BL.Usuario.Obj.*;

import java.util.ArrayList;

public class UsuarioController extends CoreController{

    public boolean registrarAdmin(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) {
        boolean err;

        Admin nuevoAdmin = new Admin(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);

        err = logicaUsuarios.registrarUsuario(nuevoAdmin);

        return err;
    }

    public boolean registrarCliente(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        Cliente nuevoCliente = new Cliente(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, direccionExacta, canton, distrito, provincia, distancia);

        boolean err = logicaUsuarios.registrarUsuario(nuevoCliente);

        return err;
    }

    public int registrarEmpleado(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, String nombrePuesto) {
        Puesto puesto = logicaPuestos.obtenerPuesto(nombrePuesto);
        int err = 0;

        if(puesto != null){
            Empleado nuevoEmpleado = new Empleado(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, puesto);

            if(logicaUsuarios.registrarUsuario(nuevoEmpleado)){
                err = 1;
//              usuario repetido
            }
        }else{
            err = 2;
        }

        return err;
    }

    public ArrayList<String> obtenerInfoUsuarios() {
        ArrayList<String> listaInfoUsuarios = logicaUsuarios.obtenerInfoUsuarios();

        return  listaInfoUsuarios;
    }

	public ArrayList<String> obtenerInfoLoginUsuarios() {
	    ArrayList<String> listaInfoLogin = logicaUsuarios.obtenerInfoLoginUsuarios();

	    return listaInfoLogin;
    }
}
