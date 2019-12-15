package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Orden.Obj.Orden;
import com.tupuntodeventa.BL.Producto.Obj.Combo;
import com.tupuntodeventa.BL.Producto.Obj.Producto;
import com.tupuntodeventa.BL.Producto.Obj.Sencillo;

import java.util.ArrayList;

public class OrdenController extends CoreController{

	public boolean registrarOrden(String nombreCompleto, String fecha_hora, String detalle, ArrayList<Integer> listaCodigoProductos) {
		boolean err = false;
		ArrayList<Producto> listaProductos = new ArrayList<>();
		int index = 0;
		double precioOrden = 0;

		for(Integer codigoProducto : listaCodigoProductos){
			Producto productoEncontrado = logicaProductos.obtenerProductoBtId(codigoProducto);

//			si el index es mayor a cero, hay otros productos detras de este, por lo tanto
//			verifique si el producto anterior es de la misma instancia que el producto actual, si alguna de las dos propuestas es verdadera, se aregara e producto, sino va a marcar error
			if(index > 0){
				if((listaProductos.get(index-1) instanceof Combo && productoEncontrado instanceof Combo) || (listaProductos.get(index-1) instanceof Sencillo && productoEncontrado instanceof Sencillo)){

				}else{
					err = true;
				}
			}

//			si el error falso se mantuvo, quiere decir que la instancia de los productos coninciden, por lo tanto el producto se agrega a la lista de productos de la orden
			if(!err){
				listaProductos.add(productoEncontrado);

				if(productoEncontrado instanceof Sencillo){
					precioOrden += ((Sencillo)productoEncontrado).getPrecio();
				}else if(productoEncontrado instanceof Combo){
					precioOrden += ((Combo)productoEncontrado).getTotal();
				}
			}

			index++;
		}

		if(!err){
			Orden nuevaOrden = new Orden(nombreCompleto, fecha_hora, detalle, precioOrden, listaProductos);

			logicaOrdenes.registrarOrden(nuevaOrden);
		}

		return err;
	}

	public ArrayList<String> obtenerInfoOrdenes(String nombreCompletoUsuario) {
		ArrayList<String> listaInfoOrdenes = new ArrayList<>();
		ArrayList<Orden> listaOrdenes = logicaOrdenes.obtenerOrdenesUsuario(nombreCompletoUsuario);

		for(Orden orden : listaOrdenes){
			listaInfoOrdenes.add(orden.toString());
		}

		return listaInfoOrdenes;
	}
}