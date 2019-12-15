package com.tupuntodeventa.BL.Usuario.Obj;

public class Admin extends Usuario{
    public Admin(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) {
        super(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);
    }

    public String toString() {
        return super.toString();
    }

    public String getInfoLogin() {
        String infoUsuarioLogin = super.getInfoLogin() + "_" + 0;

        return infoUsuarioLogin;
    }

    public boolean equals(Admin admin){
        boolean err = super.equals(admin);

        return err;
    }
}
