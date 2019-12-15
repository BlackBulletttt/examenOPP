package com.tupuntodeventa.BL.Producto;

import java.util.ArrayList;

import com.tupuntodeventa.BL.Producto.Obj.*;

public class ProductoBL {
    ArrayList<Producto> listaProductos = new ArrayList<>();

    public int registrarProducto(Producto nuevoSencillo) {
        int err = 0;

        if(listaProductos.size() != 0){
            for(Producto producto : listaProductos){
                if(producto.equals(nuevoSencillo)){
                    err = 1;
                }
            }
        }

        if(err == 0){
            listaProductos.add(nuevoSencillo);
        }

        return err;
    }

    public ArrayList<String> obtenerInfoProductos(){
        ArrayList<String> listaInfoProductos = new ArrayList<>();

        for(Producto producto :listaProductos){
            listaInfoProductos.add(producto.toString());
        }

        return listaInfoProductos;
    }

    public Sencillo obtenerSencilloById(int plato) {
        Sencillo sencilloEncontrado = null;

        for(Producto producto : listaProductos){
            if(producto instanceof Sencillo){
                if(((Sencillo)producto).getCodigo() == plato){
                    sencilloEncontrado = ((Sencillo)producto);
                }
            }
        }

        return sencilloEncontrado;
    }
}
