package com.tupuntodeventa.BL.Usuario.Obj;

import com.tupuntodeventa.BL.Direccion.Obj.Direccion;

import java.util.ArrayList;

public class Cliente extends Usuario{
    private ArrayList<Direccion> direcciones = new ArrayList<>();

    public Cliente(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        super(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);
        this.direcciones.add(new Direccion(direccionExacta, canton, distrito, provincia, distancia));
    }

    public Cliente(int identificacion, int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) {
        super(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);
    }

    public String toString() {
        String infoDirecciones = "";

        for(Direccion direccion : direcciones){
            infoDirecciones += direccion.toString();
        }
        String infoCliente = super.toString() + ", informacion de las direcciones: " + infoDirecciones;

        return infoCliente;
    }

    public boolean equals(Cliente nuevoCliente){
        boolean err = super.equals(nuevoCliente);

        return err;
    }

    public String getInfoLogin() {
        String infoUsuarioLogin = super.getInfoLogin() + "_" + 1;

        return infoUsuarioLogin;
    }

    public ArrayList<Direccion> getDirecciones() {
        return this.direcciones;
    }

    public void setDireccion(String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        this.direcciones.add(new Direccion(direccionExacta, canton, distrito, provincia, distancia));
    }
}
