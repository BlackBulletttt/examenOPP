package com.tupuntodeventa.TL;

import java.util.ArrayList;

import com.tupuntodeventa.BL.Factory.DAOFactory;
import com.tupuntodeventa.BL.Producto.IProductoDAO;
import com.tupuntodeventa.BL.Producto.Obj.*;

public class ProductoController extends CoreController{
    DAOFactory factory = DAOFactory.getDAOFactory(1);
    IProductoDAO productoDAO = factory.getProductosDAO();
    ArrayList<Producto> listaProductos = new ArrayList<>();

    public ProductoController() throws Exception {
        ArrayList<Producto> listaProductosTemp = productoDAO.obtenerProductos();

        for(Producto producto : listaProductosTemp){
            if(producto instanceof Combo){
                int codigoCombo = producto.getCodigo();

                ArrayList<Sencillo> listaSencillos = productoDAO.obtenerSencillosCombo(codigoCombo);
                for(Sencillo sencillo : listaSencillos){
                    ((Combo) producto).setSencillos(sencillo);
                }
            }

            listaProductos.add(producto);
        }
    }

    public boolean registrarSencillo(int codigo, String descripcion, double precio) throws Exception {
        Sencillo nuevoSencillo = new Sencillo(codigo, descripcion, precio);
        boolean err = false;

        for(Producto producto : listaProductos){
            if(producto.getCodigo() == nuevoSencillo.getCodigo()){
                err = true;
            }
        }

        if(!err){
            productoDAO.registrarProductoSencillo(nuevoSencillo);
        }

        return err;
    }

    public int registrarCombo(int codigo, String nombre, int total, int plato) throws Exception {
        int err = 0;

        Sencillo sencilloCombo = obtenerSencilloById(plato);

        if(sencilloCombo != null){
            for(Producto producto : listaProductos){
                if(producto.getCodigo() == codigo) {
                    err = 1;
                }
            }
        }else{
            err = 2;
        }

        if(err == 0){
            Combo nuevoCombo = new Combo(codigo, nombre, total, sencilloCombo);

            productoDAO.registrarProductoCombo(nuevoCombo);
        }

        return err;
    }

    public Sencillo obtenerSencilloById(int codigo){
        Sencillo sencilloEncontrado = null;

        for(Producto producto : listaProductos){
            if(producto.getCodigo() == codigo){
                sencilloEncontrado = (Sencillo)producto;
            }
        }

        return sencilloEncontrado;
    }

    public ArrayList<String> obtenerInfoProductos(){
        ArrayList<String> listaInfoProductos = new ArrayList<>();

        for(Producto producto : listaProductos){
            listaInfoProductos.add(producto.toString());
        }

        return listaInfoProductos;
    }
}