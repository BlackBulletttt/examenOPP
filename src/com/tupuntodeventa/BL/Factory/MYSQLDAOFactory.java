package com.tupuntodeventa.BL.Factory;

import com.tupuntodeventa.BL.Producto.IProductoDAO;
import com.tupuntodeventa.BL.Producto.MYSQLProductoDAO;
import com.tupuntodeventa.BL.Usuario.IUsuarioDAO;
import com.tupuntodeventa.BL.Usuario.MYSQLUsuarioDAO;

public class MYSQLDAOFactory extends DAOFactory {

	public IUsuarioDAO getUsuariosDAO() {
		return new MYSQLUsuarioDAO();
	}

	public IProductoDAO getProductosDAO() {
		return new MYSQLProductoDAO();
	}
}