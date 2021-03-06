package com.tupuntodeventa.BL.Usuario.Obj;

import com.tupuntodeventa.BL.Puesto.Obj.Puesto;

public class Empleado extends Usuario {
	private Puesto puesto;

	public Empleado(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) {
		super(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);
	}

	public Empleado(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, Puesto puesto) {
		super(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		String infoEmpleado = super.toString();

		return infoEmpleado;
	}

	public boolean equals(Empleado nuevoEmpleado){
		boolean err = super.equals(nuevoEmpleado);

		return err;
	}

	public String getInfoLogin() {
		String infoUsuarioLogin = super.getInfoLogin() + "_" + 2;

		return infoUsuarioLogin;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
}
