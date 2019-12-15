package com.tupuntodeventa.BL.Orden.Obj;

import com.tupuntodeventa.BL.Producto.Obj.Producto;

import java.util.ArrayList;

public class Orden {
	private String nombreCliente;
	private String fecha_hora;
	private String detalle;
	private double precioTotal;
	ArrayList<Producto> listaProductos;

	public Orden(String nombreCliente, String fecha_hora, String detalle, double precioTotal, ArrayList<Producto> productos) {
		this.nombreCliente = nombreCliente;
		this.fecha_hora = fecha_hora;
		this.detalle = detalle;
		this.precioTotal = precioTotal;
		this.listaProductos = productos;
	}

	public String toString() {
		String infoOrden = "Nombre del cliente: " + this.nombreCliente + ", fecha y hora de creacion: " + this.fecha_hora + ", detalle: " + this.detalle + ", productos asociados: \n";

		for(Producto producto : listaProductos){
			infoOrden += producto.toString();
		}

		return infoOrden;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
}
