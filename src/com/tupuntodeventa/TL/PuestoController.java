package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Puesto.Obj.Puesto;

import java.util.ArrayList;

public class PuestoController extends CoreController{

	public boolean registrarPuesto(String nombrePuesto, int salarioBase, int bonos, int salarioNeto, String fechaContratacion) {
		Puesto nuevoPuesto = new Puesto(nombrePuesto, salarioBase, bonos, salarioNeto, fechaContratacion);

		boolean err = logicaPuestos.registrarPuesto(nuevoPuesto);

		return err;
	}

	public ArrayList<String> obtenerListaInfoPuestos() {
		ArrayList<Puesto> listaPuestos = logicaPuestos.obtenerPuestos();
		ArrayList<String> listaInfoPuestos = new ArrayList<>();

		for(Puesto puesto : listaPuestos){
			listaInfoPuestos.add(puesto.toString());
		}

		return listaInfoPuestos;
	}

	public ArrayList<String> obtenerInfoPuesto(String nombrePuesto) {
		ArrayList<String> infoPuesto = new ArrayList<>();
		Puesto puesto = logicaPuestos.obtenerPuesto(nombrePuesto);

		if(puesto != null){
			infoPuesto.add(puesto.getNombre());
			infoPuesto.add(String.valueOf(puesto.getSalarioBase()));
			infoPuesto.add(String.valueOf(puesto.getBonos()));
			infoPuesto.add(String.valueOf(puesto.getSalarioNeto()));
			infoPuesto.add(String.valueOf(puesto.getFechaContratacion()));
		}

		return infoPuesto;
	}
}