package com.tupuntodeventa.BL.Cupon.Obj;

public class Cupon {
    private String fechaExpiracion;
    private String estado;
    private double descuento;
    private int codigo;

    public Cupon(String fechaExpiracion, String estado, double descuento, int codigo) {
        this.fechaExpiracion = fechaExpiracion;
        this.codigo = codigo;
        this.estado = estado;
        this.descuento = descuento;
    }

    public String toString() {
        String infoCupon = "Fecha de expiracion: " + this.fechaExpiracion + ", codigo: " + this.codigo + ", estado: " + this.estado + ", porcentaje de descuento: " + this.descuento + "%";

        return infoCupon;
    }

    public boolean equals(Cupon cupon){
        boolean err = false;

        if(this.codigo == cupon.getCodigo()){
            err = true;
        }

        return err;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}