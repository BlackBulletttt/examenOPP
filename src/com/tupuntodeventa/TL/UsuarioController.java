package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Usuario.UsuarioBL;
import com.tupuntodeventa.BL.Usuario.Obj.*;

import java.util.ArrayList;

public class UsuarioController {
    UsuarioBL logicaUsuarios = new UsuarioBL();

    public boolean registrarAdmin(int clave, String correoElectronico, String nombreUsuarrio, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) {
        boolean err = false;

        Admin nuevoAdmin = new Admin(clave, correoElectronico, nombreUsuarrio, nombreCompleto, fechaNacimiento, edad, genero, telefono);

        err = logicaUsuarios.registrarUsuario(nuevoAdmin);

        return err;
    }

    public boolean registrarCliente(int clave, String correoElectronico, String nombreUsuario, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, int identificacion, String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        Cliente nuevoCliente = new Cliente(clave, correoElectronico, nombreUsuario, nombreCompleto, fechaNacimiento, edad, genero, telefono, identificacion, direccionExacta, canton, distrito, provincia, distancia);

        boolean err = logicaUsuarios.registrarUsuario(nuevoCliente);

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
