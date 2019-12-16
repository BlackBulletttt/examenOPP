package com.tupuntodeventa.BL.Producto.Obj;

import java.util.ArrayList;

public class Combo extends Producto {
    private String nombre;
    private double total;
    private ArrayList<Sencillo> sencillos = new ArrayList<>();

    public Combo(int codigo, String nombre, double total) {
        super(codigo);
        this.nombre = nombre;
        this.total = total;
    }

    public Combo(int codigo, String nombre, double total, Sencillo sencillo) {
        super(codigo);
        this.nombre = nombre;
        this.total = total;
        this.sencillos.add(sencillo);
    }

    public String toString() {
        String infoPlatos = "";

        for(Sencillo sencillo : sencillos){
            infoPlatos += sencillo.toString();
        }

        String infoCombo = super.toString() + ", nombre: " + this.nombre + ", total: " + this.total + ", sencillos que lo componen: " + infoPlatos;

        return infoCombo;
    }

    public boolean equals(Producto producto) {
        boolean err = super.equals(producto);

        return  err;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Sencillo> getSencillos() {
        return sencillos;
    }

    public void setSencillos(Sencillo sencillos) {
        this.sencillos.add(sencillos);
    }
}
