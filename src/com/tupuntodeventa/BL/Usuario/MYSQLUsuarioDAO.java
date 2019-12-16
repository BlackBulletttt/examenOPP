package com.tupuntodeventa.BL.Usuario;

import com.tupuntodeventa.BL.Usuario.Obj.Admin;
import com.tupuntodeventa.BL.Usuario.Obj.Cliente;
import com.tupuntodeventa.BL.Usuario.Obj.Empleado;
import com.tupuntodeventa.BL.Usuario.Obj.Usuario;
import com.tupuntodeventa.DL.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MYSQLUsuarioDAO implements IUsuarioDAO {
	String req;

	public boolean registrarAdmin(Admin nuevoAdmin) throws Exception{
		boolean err = false;

		req = "insert into tupuntodeventa.usuario_admin(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono)values("+nuevoAdmin.getIdentificacion()+", "+nuevoAdmin.getClave()+", '"+nuevoAdmin.getCorreoElectronico()+"', '"+nuevoAdmin.getNombreUsuario()+"', '"+nuevoAdmin.getContrasenna()+"', '"+nuevoAdmin.getNombreCompleto()+"', '"+nuevoAdmin.getFechaNacimiento()+"', "+nuevoAdmin.getEdad()+", '"+nuevoAdmin.getGenero()+"', "+nuevoAdmin.getTelefono()+")";
		try{
			Connector.getConnector().POST(req);
		}catch (Exception ex){
			err = true;
			throw new Exception("Error con la consulta => " + ex.getMessage());
		}

		return err;
	}

	public boolean registrarEmpleado(Empleado nuevoEmpleado) throws Exception{
		boolean err = false;

		req = "insert into tupuntodeventa.usuario_empleado(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, nombrePuesto)values("+ nuevoEmpleado.getIdentificacion()+", "+ nuevoEmpleado.getClave()+", '"+ nuevoEmpleado.getCorreoElectronico()+"', '"+ nuevoEmpleado.getNombreUsuario()+"', '"+ nuevoEmpleado.getContrasenna()+"', '"+ nuevoEmpleado.getNombreCompleto()+"', '"+ nuevoEmpleado.getFechaNacimiento()+"', "+ nuevoEmpleado.getEdad()+", '"+ nuevoEmpleado.getGenero()+"', "+ nuevoEmpleado.getTelefono()+", '"+nuevoEmpleado.getPuesto().getNombre()+"')";
		try{
			Connector.getConnector().POST(req);
		}catch (Exception ex){
			err = true;
			throw new Exception("Error con la consulta => " + ex.getMessage());
		}

		return err;
	}

	public boolean registrarCliente(Cliente nuevoCliente) throws Exception{
		boolean err = false;

		req = "insert into tupuntodeventa.usuario_cliente(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono)values("+ nuevoCliente.getIdentificacion()+", "+ nuevoCliente.getClave()+", '"+ nuevoCliente.getCorreoElectronico()+"', '"+ nuevoCliente.getNombreUsuario()+"', '"+ nuevoCliente.getContrasenna()+"', '"+ nuevoCliente.getNombreCompleto()+"', '"+ nuevoCliente.getFechaNacimiento()+"', "+ nuevoCliente.getEdad()+", '"+ nuevoCliente.getGenero()+"', "+ nuevoCliente.getTelefono()+")";
		try{
			Connector.getConnector().POST(req);
		}catch (Exception ex){
			err = true;
			throw new Exception("Error con la consulta => " + ex.getMessage());
		}

		return err;
	}

	public ArrayList<Usuario> obtenerUsuarios() throws Exception {
		boolean err = false;
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();

//		1. Obtengo todos los usuarios en la tabla de administrador, instancio objetos tipo administradores con el `response` de la BD
		req = "select * from tupuntodeventa.usuario_admin";
		ResultSet resAdmin = Connector.getConnector().GET(req);
		while (resAdmin.next()){
			Admin nuevoAdmin = new Admin(resAdmin.getInt("identificacion"), resAdmin.getInt("clave"), resAdmin.getString("correoElectronico"), resAdmin.getString("nombreUsuario"), resAdmin.getString("contrasena"), resAdmin.getString("nombreCompleto"), resAdmin.getString("fechaNacimiento"), resAdmin.getInt("edad"), resAdmin.getString("genero"), resAdmin.getInt("telefono"));

			listaUsuarios.add(nuevoAdmin);
		}

//		2. Obtener los usuarios de la tabla de empleados, instanciar objetos tipo Empleado con el `response` de la BD
		req = "select * from tupuntodeventa.usuario_empleado";
		ResultSet resEmpleado = Connector.getConnector().GET(req);
		while(resEmpleado.next()){
			Empleado nuevoEmpleado = new Empleado(resEmpleado.getInt("identificacion"), resEmpleado.getInt("clave"), resEmpleado.getString("correoElectronico"), resEmpleado.getString("nombreUsuario"), resEmpleado.getString("contrasena"), resEmpleado.getString("nombreCompleto"), resEmpleado.getString("fechaNacimiento"), resEmpleado.getInt("edad"), resEmpleado.getString("genero"), resEmpleado.getInt("telefono"));

			listaUsuarios.add(nuevoEmpleado);
		}

//		3. Obtener los usuarios de la tabla de clientes, instanciar objetos tipo Cliente con el `response` de la BD
		req = "select * from tupuntodeventa.usuario_cliente";
		ResultSet resCliente = Connector.getConnector().GET(req);
		while(resCliente.next()){
			Cliente nuevoCliente = new Cliente(resCliente.getInt("identificacion"), resCliente.getInt("clave"), resCliente.getString("correoElectronico"), resCliente.getString("nombreUsuario"), resCliente.getString("contrasena"), resCliente.getString("nombreCompleto"), resCliente.getString("fechaNacimiento"), resCliente.getInt("edad"), resCliente.getString("genero"), resCliente.getInt("telefono"));

			listaUsuarios.add(nuevoCliente);
		}

		return listaUsuarios;
	}
}
