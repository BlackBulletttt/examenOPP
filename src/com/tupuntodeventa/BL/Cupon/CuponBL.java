package com.tupuntodeventa.BL.Cupon;

import java.util.ArrayList;

import com.tupuntodeventa.BL.Cupon.Obj.*;

public class CuponBL {
    ArrayList<Cupon> listaCupones = new ArrayList<>();

    public boolean registrarCupon(Cupon nuevoCupon) {
        boolean err = false;

        if(listaCupones.size() != 0){
            for(Cupon cupon : listaCupones){
                if(cupon.equals(nuevoCupon)){
                    err = true;
                }
            }
        }

        if(!err){
            listaCupones.add(nuevoCupon);
        }

        return err;
    }

    public ArrayList<String> obtenerInfoCupones(){
        ArrayList<String> listaInfoCupones = new ArrayList<>();

        for(Cupon cupon : listaCupones){
            listaInfoCupones.add(cupon.toString());
        }

        return listaInfoCupones;
    }
}
