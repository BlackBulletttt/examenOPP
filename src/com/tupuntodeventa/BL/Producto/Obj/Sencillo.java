package com.tupuntodeventa.BL.Producto.Obj;

public class Sencillo extends Producto {
    private String descripcion;
    private  double precio;

    public Sencillo(int codigo, String descripcion, double precio) {
        super(codigo);
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String toString() {
        String infoSencillo = "\n" + super.toString() + ", descripcion: " + this.descripcion + ", precio: " + this.precio;

        return infoSencillo;
    }

    public boolean equals(Sencillo sencillo){
        boolean err = super.equals(sencillo);

        return err;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}