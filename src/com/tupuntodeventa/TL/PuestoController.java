package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Puesto.Obj.Puesto;
import com.tupuntodeventa.BL.Puesto.PuestoBL;

import java.util.ArrayList;

public class PuestoController {
	PuestoBL logicaPuesto = new PuestoBL();

	public boolean registrarPuesto(String nombrePuesto, int salarioBase, int bonos, int salarioNeto, String fechaContratacion) {
		Puesto nuevoPuesto = new Puesto(nombrePuesto, salarioBase, bonos, salarioNeto, fechaContratacion);

		boolean err = logicaPuesto.registrarPuesto(nuevoPuesto);

		return err;
	}

	public ArrayList<String> obtenerListaInfoPuestos() {
		ArrayList<Puesto> listaPuestos = logicaPuesto.obtenerPuestos();
		ArrayList<String> listaInfoPuestos = new ArrayList<>();

		for(Puesto puesto : listaPuestos){
			listaInfoPuestos.add(puesto.toString());
		}

		return listaInfoPuestos;
	}
}
