package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Cupon.CuponBL;
import com.tupuntodeventa.BL.Cupon.Obj.*;

import java.util.ArrayList;

public class CuponController {
    CuponBL logicaCupon = new CuponBL();

    public boolean registrarCupon(String fechaExpiracion, String estado, double descuento, int codigo) {
        Cupon nuevoCupon = new Cupon(fechaExpiracion, estado, descuento, codigo);

        boolean err = logicaCupon.registrarCupon(nuevoCupon);

        return err;
    }

    public ArrayList<String> obtenerInfoCupones(){
        ArrayList<String> listaInfoCupones = logicaCupon.obtenerInfoCupones();

        return listaInfoCupones;
    }
}
