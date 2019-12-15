package com.tupuntodeventa.BL.Orden;

import com.tupuntodeventa.BL.Orden.Obj.Orden;

import java.util.ArrayList;

public class OrdenBL {
	ArrayList<Orden> listaOrdenes = new ArrayList<>();

	public void registrarOrden(Orden nuevaOrden) {
		listaOrdenes.add(nuevaOrden);
	}

	public ArrayList<Orden> obtenerOrdenesUsuario(String nombreCompletoUsuario) {
		ArrayList<Orden> listaOrdenesUsuario = new ArrayList<>();

		for(Orden orden : listaOrdenes){
			if(nombreCompletoUsuario.equals("adm")){
				listaOrdenesUsuario.add(orden);
			}else if(orden.getNombreCliente().equals(nombreCompletoUsuario)){
				listaOrdenesUsuario.add(orden);
			}
		}

		return listaOrdenesUsuario;
	}
}
