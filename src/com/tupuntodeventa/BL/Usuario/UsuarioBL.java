package com.tupuntodeventa.BL.Usuario;

import java.util.ArrayList;

import com.tupuntodeventa.BL.Usuario.Obj.*;

public class UsuarioBL {
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public boolean registrarUsuario(Usuario nuevoUsuario) {
        boolean err = false;

        if(listaUsuarios.size() != 0) {
            for (Usuario usuario : listaUsuarios) {
//                if(nuevoUsuario instanceof Cliente){
//                    if(((Cliente)usuario).equals(nuevoUsuario)){
//                        err = true;
//                    }
//                }else{
                    if (usuario.equals(nuevoUsuario)) {
                        err = true;
                    }
//                }
            }
        }

        if(!err){
            listaUsuarios.add(nuevoUsuario);
        }

        return err;
    }

    public ArrayList<String> obtenerInfoUsuarios() {
        ArrayList<String> listaInfoUsuarios = new ArrayList<>();

        for(Usuario usuario : listaUsuarios){
            listaInfoUsuarios.add(usuario.toString());
        }

        return listaInfoUsuarios;
    }

	public ArrayList<String> obtenerInfoLoginUsuarios() {
	    ArrayList<String> listaInfoLogin = new ArrayList<>();

	    for(Usuario usuario : listaUsuarios){
            listaInfoLogin.add(usuario.getInfoLogin());
        }

	    return listaInfoLogin;
    }
}
