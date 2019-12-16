package com.tupuntodeventa.TL;

import java.util.ArrayList;

import com.tupuntodeventa.BL.Factory.DAOFactory;
import com.tupuntodeventa.BL.Producto.IProductoDAO;
import com.tupuntodeventa.BL.Producto.Obj.*;
import com.tupuntodeventa.BL.Producto.ProductoBL;

public class ProductoController extends CoreController{
    DAOFactory factory = DAOFactory.getDAOFactory(1);
    IProductoDAO productoDAO = factory.getProductosDAO();

    public int registrarSencillo(int codigo, String descripcion, double precio) {
        Sencillo nuevoSencillo = new Sencillo(codigo, descripcion, precio);

        int err = logicaProductos.registrarProducto(nuevoSencillo);

        return err;
    }

    public ArrayList<String> obtenerInfoProductos(){
        ArrayList<String> listaInfoProductos = logicaProductos.obtenerInfoProductos();

        return listaInfoProductos;
    }

    public int registrarCombo(int codigo, String nombre, int total, int plato) {
        int err = 0;

        Sencillo sencillo = logicaProductos.obtenerSencilloById(plato);
        Combo nuevoCombo = new Combo(codigo, nombre, total, sencillo);

        err = logicaProductos.registrarProducto(nuevoCombo);

        return err;
    }
}