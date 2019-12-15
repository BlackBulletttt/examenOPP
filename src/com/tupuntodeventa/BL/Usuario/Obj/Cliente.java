package com.tupuntodeventa.BL.Usuario.Obj;

import com.tupuntodeventa.BL.Direccion.Obj.Direccion;

import java.util.ArrayList;

public class Cliente extends Usuario{
    private int identificacion;
    private ArrayList<Direccion> direcciones = new ArrayList<>();

    public Cliente(int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono, int identificacion, String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        super(clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono);
        this.identificacion = identificacion;
        this.direcciones.add(new Direccion(direccionExacta, canton, distrito, provincia, distancia));
    }

    public String toString() {
        String infoDirecciones = "";

        for(Direccion direccion : direcciones){
            infoDirecciones += direccion.toString();
        }
        String infoCliente = super.toString() + ", identificacion: " + this.identificacion + ", informacion de las direcciones: " + infoDirecciones;

        return infoCliente;
    }

    public String getInfoLogin() {
        String infoUsuarioLogin = super.getInfoLogin() + "_" + 1;

        return infoUsuarioLogin;
    }

    public boolean equals(Cliente cliente){
        boolean err = false;

        if(this.identificacion == cliente.getIdentificacion()){
            err = true;
        }

        return err;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public ArrayList<Direccion> getDirecciones() {
        return this.direcciones;
    }

    public void setDireccion(String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        this.direcciones.add(new Direccion(direccionExacta, canton, distrito, provincia, distancia));
    }
}
