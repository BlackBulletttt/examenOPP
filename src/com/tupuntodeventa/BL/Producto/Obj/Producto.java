package com.tupuntodeventa.BL.Producto.Obj;

public class Producto {
    private int codigo;

    public Producto(int codigo) {
        this.codigo = codigo;
    }

    public String toString() {
        String infoProducto = "Codigo: " + this.codigo;

        return infoProducto;
    }

    public boolean equals(Producto producto){
        boolean err = false;

        if(this.codigo == producto.getCodigo()){
            err = true;
        }
        return  err;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}