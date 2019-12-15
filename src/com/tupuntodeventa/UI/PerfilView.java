package com.tupuntodeventa.UI;

public class PerfilView extends MainView {

	public void perfilMenu(){
		int opcionMenu = 0;
		do{
			System.out.println("\n=== Bienvenido a <Su perfil> " +usuarioActivo.split("_")[0]+" ===\n");
			switch (Integer.parseInt(usuarioActivo.split("_")[2])){
				case 0:
					System.out.println("Admin");
					System.out.println("[3]. Registrar cupones");
					System.out.println("[4]. Registrar productos");
					System.out.println("[5]. Listar usuarios");
					System.out.println("[6]. Listar cupones");
					System.out.println("[7]. Listar productos");
					break;

				case 1:
					System.out.println("Cliente");
					break;
			}
			System.out.println("[0]. Cerrar sesión");

			opcionMenu = in.nextInt();

			procesarOpcionPerfil(opcionMenu);
		}while(opcionMenu != 0);
	}

	public void procesarOpcionPerfil(int opcion){
		switch (opcion){
			case 0:
				int opcionMenu = MainView.menu();
				MainView.procesarOpcion(opcionMenu);
				break;
		}
	}
}
