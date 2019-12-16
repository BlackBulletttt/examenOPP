package com.tupuntodeventa.BL.Producto;

import com.tupuntodeventa.BL.Producto.Obj.Combo;
import com.tupuntodeventa.BL.Producto.Obj.Producto;
import com.tupuntodeventa.BL.Producto.Obj.Sencillo;
import com.tupuntodeventa.DL.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;


public class MYSQLProductoDAO implements IProductoDAO {
	String req;
	Random rdm = new Random();

	public boolean registrarProductoSencillo(Sencillo nuevoSencillo) throws Exception {
		boolean err = false;

		req = "insert into tupuntodeventa.productos_sencillo (codigo, descripcion, precio)values("+nuevoSencillo.getCodigo()+", '"+nuevoSencillo.getDescripcion()+"', "+nuevoSencillo.getPrecio()+")";

		try{
			Connector.getConnector().POST(req);
		}catch (Exception e){
			err = true;
			throw e;
		}

		return err;
	}

	public boolean registrarProductoCombo(Combo nuevoCombo) throws Exception{
		boolean err = false;

			req = "insert into tupuntodeventa.productos_combo (codigo, descripcion, precio)values("+nuevoCombo.getCodigo()+", '"+nuevoCombo.getNombre()+"', "+nuevoCombo.getTotal()+")";
			try{
				Connector.getConnector().POST(req);
			} catch(Exception e){
				err = true;
				throw new Exception(e);
			}

			for(Sencillo sencilloCombo : nuevoCombo.getSencillos()){
				String idRelacion = String.format("%06d", rdm.nextInt(999999));
				req = "insert into tupuntodeventa.productos_combo_sencillo (idRelacion, idCombo, idSencillo)values('"+idRelacion+"', "+nuevoCombo.getCodigo()+", "+sencilloCombo.getCodigo()+")";

				try{
					Connector.getConnector().POST(req);
				}catch(Exception e){
					err = true;
					throw new Exception(e);
				}
			}

		return err;
	}

	public ArrayList<Producto> obtenerProductos() throws Exception {
		ArrayList<Producto> listaProductos = new ArrayList<>();

		req = "select * from tupuntodeventa.productos_sencillo";
		ResultSet resSencillos = Connector.getConnector().GET(req);
		while(resSencillos.next()){
			Sencillo nuevoSencillo = new Sencillo(resSencillos.getInt("codigo"), resSencillos.getString("descripcion"), resSencillos.getInt("precio"));

			listaProductos.add(nuevoSencillo);
		}

		req = "select * from tupuntodeventa.productos_combo";
		ResultSet resCombos = Connector.getConnector().GET(req);
		while(resCombos.next()){
			Combo nuevoCombo = new Combo(resCombos.getInt("codigo"), resCombos.getString("descripcion"), resCombos.getInt("precio"));

			listaProductos.add(nuevoCombo);
		}

		return listaProductos;
	}

	public ArrayList<Sencillo> obtenerSencillosCombo(int codigoCombo) throws Exception{
		ArrayList<Sencillo> listaSencillos = new ArrayList<>();
		ArrayList<Integer> listaCodigosSencillos = new ArrayList<>();

		req = "select * from tupuntodeventa.productos_combo_sencillo where idCombo=("+codigoCombo+")";
		ResultSet resIdSencillos = Connector.getConnector().GET(req);

		while(resIdSencillos.next()){
			listaCodigosSencillos.add(resIdSencillos.getInt("idSencillo"));
		}

		for(Integer codigoSencillo : listaCodigosSencillos){
			req = "select * from tupuntodeventa.productos_sencillo where codigo =("+codigoSencillo+")";

			ResultSet resSencillos = Connector.getConnector().GET(req);
			while(resSencillos.next()){
				Sencillo nuevoSencillo = new Sencillo(resSencillos.getInt("codigo"), resSencillos.getString("descripcion"), resSencillos.getInt("precio"));
				listaSencillos.add(nuevoSencillo);
			}
		}

		return listaSencillos;
	}

}
