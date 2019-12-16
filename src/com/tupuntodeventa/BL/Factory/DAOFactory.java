package com.tupuntodeventa.BL.Factory;

import com.tupuntodeventa.BL.Producto.IProductoDAO;
import com.tupuntodeventa.BL.Usuario.IUsuarioDAO;

public abstract class DAOFactory {
	public static DAOFactory getDAOFactory(int tipo){
		switch (tipo){
			case 1: return new MYSQLDAOFactory();

			default: return null;
		}
	}

	public abstract IUsuarioDAO getUsuariosDAO();
	public abstract IProductoDAO getProductosDAO();
}