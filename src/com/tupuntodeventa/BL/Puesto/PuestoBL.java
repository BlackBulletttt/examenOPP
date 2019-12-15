package com.tupuntodeventa.BL.Puesto;

import com.tupuntodeventa.BL.Puesto.Obj.Puesto;

import java.util.ArrayList;

public class PuestoBL{
	ArrayList<Puesto> listaPuestos = new ArrayList<>();

	public boolean registrarPuesto(Puesto nuevoPuesto) {
		boolean err = false;
		if(listaPuestos.size() != 0){
			for(Puesto puesto : listaPuestos){
				if(puesto.equals(nuevoPuesto)){
					err = true;
				}
			}
		}

		if(!err){
			listaPuestos.add(nuevoPuesto);
		}

		return err;
	}

	public ArrayList<Puesto> obtenerPuestos() {
		return listaPuestos;
	}

	public Puesto obtenerPuesto(String nombrePuesto) {
		Puesto puestoEncontrado = null;

		for(Puesto puesto : listaPuestos){
			if(puesto.getNombre().equals(nombrePuesto)){
				puestoEncontrado = puesto;
			}
		}

		return puestoEncontrado;
	}
}
