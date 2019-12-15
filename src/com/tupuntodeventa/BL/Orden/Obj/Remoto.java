package com.tupuntodeventa.BL.Orden.Obj;

import com.tupuntodeventa.BL.Cupon.Obj.Cupon;
import com.tupuntodeventa.BL.Producto.Obj.Producto;

import java.util.ArrayList;

public class Remoto extends Orden {
	private Cupon cupon;

	public Remoto(String nombreCliente, String fecha_hora, String detalle, double precioTotal, ArrayList<Producto> productos, Cupon cupon) {
		super(nombreCliente, fecha_hora, detalle, precioTotal, productos);
		this.cupon = cupon;
	}

	@Override
	public String toString() {
		String infoRemoto = super.toString() + "informacion del cupon: " + this.cupon.toString();

		return infoRemoto;
	}

	public Cupon getCupon() {
		return cupon;
	}

	public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}
}
