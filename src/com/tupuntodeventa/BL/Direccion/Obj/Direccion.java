package com.tupuntodeventa.BL.Direccion.Obj;

public class Direccion {
    private String direccionExacta;
    private String canton;
    private String distrito;
    private String provincia;
    private int distancia;

    public Direccion(String direccionExacta, String canton, String distrito, String provincia, int distancia) {
        this.direccionExacta = direccionExacta;
        this.canton = canton;
        this.distrito = distrito;
        this.provincia = provincia;
        this.distancia = distancia;
    }

    public String toString() {
        String infoDireccion = "\nDireccion exacta: " + this.direccionExacta + ", canton: " + this.canton + ", distrito: " + this.distrito + ", provincia: " + this.provincia + ", distancia: " + this.distancia;

        return infoDireccion;
    }

    public boolean equals(Direccion direccion){
        boolean err = false;

        if(this.direccionExacta.equals(direccion.getDireccionExacta())){
            err = true;
        }

        return err;
    }

    public String getDireccionExacta() {
        return direccionExacta;
    }

    public void setDireccionExacta(String direccionExacta) {
        this.direccionExacta = direccionExacta;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}