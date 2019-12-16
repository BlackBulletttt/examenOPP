package com.tupuntodeventa.BL.Producto;

import com.tupuntodeventa.BL.Producto.Obj.Combo;
import com.tupuntodeventa.BL.Producto.Obj.Producto;
import com.tupuntodeventa.BL.Producto.Obj.Sencillo;

import java.util.ArrayList;

public interface IProductoDAO {
	boolean registrarProductoSencillo(Sencillo nuevoProducto) throws Exception;
	ArrayList<Producto> obtenerProductos() throws Exception;
	boolean registrarProductoCombo(Combo nuevoCombo) throws Exception;
	ArrayList<Sencillo> obtenerSencillosCombo(int codigoCombo) throws Exception;
}
