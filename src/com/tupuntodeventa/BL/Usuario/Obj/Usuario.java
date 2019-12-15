package com.tupuntodeventa.BL.Usuario.Obj;

public class Usuario {
    private int clave;
    private String correoElectronico;
    private String nombreUsuario;
    private String contrasenna;
    private String nombreCompleto;
    private String fechaNacimiento;
    private int edad;
    private String genero;
    private int telefono;

    public Usuario(int clave, String correoElectronico, String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento, int edad, String genero, int telefono) {
        this.clave = clave;
        this.correoElectronico = correoElectronico;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;

    }

    public String toString() {
        String infoUsuario =  "\nClave: " + this.clave + ", correo electronico: " + this.correoElectronico + ", nombre de usuario: " + this.nombreUsuario + ", nombre completo: " + this.nombreCompleto + ", fecha de nacimiento: " + this.fechaNacimiento + ", edad: " + this.edad + ", genero: " + this.genero + ", telefono: " + this.telefono;

        return infoUsuario;
    }

    public String getInfoLogin() {
        String infoUsuarioLogin = this.nombreUsuario + "_" + this.contrasenna;

        return infoUsuarioLogin;
    }

    public boolean equals(Usuario usuario){
        boolean err = false;

        if(this.clave == usuario.getClave()){
            err = true;
        }

        return err;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
