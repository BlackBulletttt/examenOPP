package com.tupuntodeventa.BL.Usuario;

import com.tupuntodeventa.BL.Usuario.Obj.Admin;
import com.tupuntodeventa.BL.Usuario.Obj.Cliente;
import com.tupuntodeventa.BL.Usuario.Obj.Empleado;
import com.tupuntodeventa.BL.Usuario.Obj.Usuario;

import java.util.ArrayList;

public interface IUsuarioDAO {
	boolean registrarAdmin(Admin nuevoAdmin) throws Exception;
	boolean registrarEmpleado(Empleado nuevoEmpleado) throws Exception;
	boolean registrarCliente(Cliente nuevoCliente) throws Exception;
	ArrayList<Usuario> obtenerUsuarios() throws Exception;
}