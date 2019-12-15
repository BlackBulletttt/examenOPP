package com.tupuntodeventa.BL.Puesto.Obj;

public class Puesto {
	private String nombre;
	private int salarioBase;
	private int bonos;
	private int salarioNeto;
	private String fechaContratacion;

	public Puesto(String nombre, int salarioBase, int bonos, int salarioNeto, String fechaContratacion) {
		this.nombre = nombre;
		this.salarioBase = salarioBase;
		this.bonos = bonos;
		this.salarioNeto = salarioNeto;
		this.fechaContratacion = fechaContratacion;
	}

	public String toString(){
		String infoPuesto = "Nombre: "+this.nombre+", salario base: "+this.salarioBase+", bonos: "+this.bonos+", salario neto: "+this.salarioNeto+", fecha de contratacion: "+this.fechaContratacion;

		return infoPuesto;
	}

	public boolean equals(Puesto nuevoPuesto){
		boolean err = false;

		if(this.nombre.equals(nuevoPuesto.getNombre())){
			err = true;
		}
		return err;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(int salarioBase) {
		this.salarioBase = salarioBase;
	}

	public int getBonos() {
		return bonos;
	}

	public void setBonos(int bonos) {
		this.bonos = bonos;
	}

	public int getSalarioNeto() {
		return salarioNeto;
	}

	public void setSalarioNeto(int salarioNeto) {
		this.salarioNeto = salarioNeto;
	}

	public String getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(String fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
}