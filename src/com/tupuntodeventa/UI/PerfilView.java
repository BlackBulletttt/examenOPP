package com.tupuntodeventa.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PerfilView extends MainView {

	public void perfilMenu() throws Exception {
		int opcionMenu = 0;
		do{
			System.out.println("\n=== Bienvenido a <Su perfil> " +usuarioActivo.split("_")[2]+" ===\n");

//			brindar las opciones segun el tipo de usuario activo
			switch (usuarioActivo.split("_")[3]){
				case "0":
					System.out.println("[1]. Registrar puestos");
					System.out.println("[2]. Listar puestos");
					System.out.println("[3]. Registrar empleados");
					System.out.println("[4]. Registrar cupones");
					System.out.println("[5]. Registrar productos");
					System.out.println("[6]. Listar usuarios");
					System.out.println("[7]. Listar cupones");
					System.out.println("[8]. Listar productos");
					System.out.println("[9]. Listar ordenes");
					System.out.println("[0]. Cerrar sesión");

					opcionMenu = in.nextInt();
					procesarOpcionAdmin(opcionMenu);
					break;

				case "1":
					System.out.println("[1]. Lista productos");
					System.out.println("[2]. Registrar órden");
					System.out.println("[3]. Listar órdenes");
					System.out.println("[0]. Cerrar sesión");

					opcionMenu = in.nextInt();
					procesarOpcionCliente(opcionMenu);
					break;
			}
		}while(opcionMenu != 0);
	}

	public void procesarOpcionAdmin(int opcion) throws Exception {
		switch (opcion){
			case 0:
				int opcionMenu = MainView.menu();
				MainView.procesarOpcion(opcionMenu);
				break;

			case 1:
				registrarPuesto();
				break;

			case 2:
				listarPuestos();
				break;

			case 3:
				registrarEmpleado();
				break;

			case 4:
				registrarCupon();
				break;

			case 5:
				registrarProducto();
				break;

			case 6:
				listarUsuarios();
				break;

			case 7:
				listarCupones();
				break;

			case 8:
				listarProductos();
				break;

			case 9:
				listarOrdenes();
				break;
		}
	}

	public void procesarOpcionCliente(int opcion) throws Exception {
		switch (opcion){
			case 0:
				int opcionMenu = MainView.menu();
				MainView.procesarOpcion(opcionMenu);
				break;

			case 1:
				listarProductos();
				break;

			case 2:
				registrarOrdenRemoto();
				break;

			case 3:
				listarOrdenes();
				break;
		}

	}

	public void registrarPuesto(){
		in.nextLine();

		System.out.println("Ingrese el nombre del puesto");
		String nombrePuesto = in.nextLine();

		System.out.println("Ingrese el salario base del puesto");
		int salarioBase = in.nextInt();

		System.out.println("Ingrese los bonos del puesto");
		int bonos = in.nextInt();

		System.out.println("Ingrese el salario neto del puesto");
		int salarioNeto = in.nextInt();

		in.nextLine();
		System.out.println("Ingrese la fecha de contratacion del puesto");
		String fechaContratacion = in.nextLine();

		boolean err = gestorPuestos.registrarPuesto(nombrePuesto, salarioBase, bonos, salarioNeto, fechaContratacion);

		if(err){
			System.err.println("Hubo un problema al registrar el puesto, intente de nuevo");
		}else{
			System.out.println("El puesto fue registrado correctamente");
		}
	}

	public static ArrayList<String> obtenerInfoPuesto(String nombrePuesto){
		ArrayList<String> infoPuesto = gestorPuestos.obtenerInfoPuesto(nombrePuesto);

		return infoPuesto;
	}

	public void listarPuestos(){
		ArrayList<String> listaInfoPestos = gestorPuestos.obtenerListaInfoPuestos();

		for(String infoPuesto : listaInfoPestos){
			System.out.println(infoPuesto);
		}
	}

	public static void registrarEmpleado() throws Exception {
		ArrayList<String> infoUsuario = MainView.solicitarInfoUsuario();

		int identificacion = Integer.parseInt(infoUsuario.get(0));
		int clave = Integer.parseInt(infoUsuario.get(1));
		String correoElectronico = infoUsuario.get(2);
		String nombreUsuario = infoUsuario.get(3);
		String contrasena = infoUsuario.get(4);
		String nombreCompleto = infoUsuario.get(5);
		String fechaNacimiento = infoUsuario.get(6);
		int edad = Integer.parseInt(infoUsuario.get(7));
		String genero = infoUsuario.get(8);
		int telefono = Integer.parseInt(infoUsuario.get(9));

		System.out.println("Ingrese el nombre del puesto que desea agregar al empleado");
		String nombrePuesto = in.nextLine();

			int err = gestorUsuarios.registrarEmpleado(identificacion, clave, correoElectronico, nombreUsuario, contrasena, nombreCompleto, fechaNacimiento, edad, genero, telefono, nombrePuesto);

			switch (err){
				case 1:
					System.err.println("Ya existe un usuario con esa identificacion.");
					break;

				case 2:
					System.err.println("No existe un puesto con ese nombre.");
					break;

				case 0:
					System.out.println("El empleado se ha registrado correctamente.");
			}
	}

	public void registrarOrdenRemoto(){
		in.nextLine();
		String nombreCompleto = usuarioActivo.split("_")[2];

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fecha_hora = (formato.format(LocalDateTime.now()));

		ArrayList<Integer> listaCodigoProductos = new ArrayList<>();

		int opcion;
		do{
			listarProductos();
			System.out.println("Ingrese el CODIGO de los productos que dese agregar a su orden");

			int codigoProductos = in.nextInt();
			listaCodigoProductos.add(codigoProductos);

			System.out.println("[1]. Registrar nuevo producto");
			System.out.println("[0]. Dejar de ordenar");

			opcion = in.nextInt();
		}while(opcion != 0);

		in.nextLine();
		System.out.println("Ingrese algun detalle para la orden");
		String detalle = in.nextLine();

		boolean err = gestorOrdenes.registrarOrden(nombreCompleto, fecha_hora, detalle, listaCodigoProductos);

		if(err){
			System.err.println("Hubo un error en el proceso, recuerde que no puede mezclar productos sencillos y combos en una misma orden");
		}else{
			System.out.println("Su orden se ha registrado correctamente");
		}

	}

	public void listarOrdenes(){
		String nombreCompletoUsuario;

//		si el tipo de usuario es `0` es admin, asi se filtran TODAS las ordenes o las del usuario activo
		if(usuarioActivo.split("_")[3].equals("0")){
			nombreCompletoUsuario = "adm";
		}else{
			nombreCompletoUsuario = usuarioActivo.split("_")[2];
		}

		ArrayList<String> listaInfoOrdenes = gestorOrdenes.obtenerInfoOrdenes(nombreCompletoUsuario);

		for(String infoOrden : listaInfoOrdenes){
			System.out.println(infoOrden);
		}
	}

	public void registrarCupon() {
		if(gestorCupones.obtenerInfoCupones().size() !=0){
			System.err.println("Hay cupones que no se han utilizado, debe esperar a que se acaben");
		}else{
			in.nextLine();
			System.out.println("Inrese la fecha de expiracion: (dd/mm/yyyy)");
			String fechaExpiracion = in.nextLine();

			System.out.println("Ingrese el estado: (redimino / no redimino)");
			String estado = in.nextLine();

			System.out.println("Ingrese el descuento: ");
			double descuento = in.nextDouble();

			for(int i=0; i < 50; i++){
				int codigo = Integer.parseInt(String.format("%06d", (rdm.nextInt(999999))));

				boolean err = gestorCupones.registrarCupon(fechaExpiracion, estado, descuento, codigo);

				if(err){
					System.err.println("El cupon ya esta agregado en el sistema.");
				}else{
					System.out.println("Cupon agregado con exito.");
				}
			}
		}
	}

	public void registrarProducto() throws Exception {
		int opcionProducto = 0;

		do{
			System.out.println("¿Que tipo de producto desea registrar?");
			System.out.println("[1]. Sencillo");
			System.out.println("[2]. Combo");
			System.out.println("[0]. Regresar");

			opcionProducto = in.nextInt();

			switch (opcionProducto){
				case 0:
					perfilMenu();
					break;

				case 1: registrarProductoSencillo();
					break;

				case 2: registrarProductoCombo();
					break;

				default:
					System.err.println("Opcion invalida, intente de nuevo");
					break;
			}
		}while(opcionProducto > 2);
	}

	public void registrarProductoSencillo() {
		System.out.println("Ingrese un codigo para el producto: ");
		int codigo = in.nextInt();

		in.nextLine();
		System.out.println("Ingrese la descripcion del plato: ");
		String descripcion = in.nextLine();

		System.out.println("Ingrese el precio del platillo: ");
		double precio = in.nextInt();

		int err = gestorProductos.registrarSencillo(codigo, descripcion, precio);

		switch (err){
			case 0:
				System.out.println("Producto agregado con exito.");
				break;

			case 1:
				System.err.println("El producto ya esta agregado en el sistema.");
				break;
		}
	}

	public void registrarProductoCombo() {
		System.out.println("Ingrese un codigo para el producto: ");
		int codigo = in.nextInt();

		in.nextLine();
		System.out.println("Ingrese el nombre del combo: ");
		String nombre = in.nextLine();

		System.out.println("Ingrese el precio total del combo: ");
		int total = in.nextInt();

		System.out.println("Ingrese el codigo del plato que desea agregar:");
		int plato = in.nextInt();

		int err = gestorProductos.registrarCombo(codigo, nombre, total, plato);

		switch (err){
			case 0:
				System.out.println("Combo aregado con exito.");
				break;

			case 1:
				System.out.println("El producto ya esta agregado en el sistema.");
				break;
		}
	}

	public void listarUsuarios() throws Exception {
		ArrayList<String> listaInfoUsuarios = gestorUsuarios.obtenerInfoUsuarios();

		for(String infoUsuario : listaInfoUsuarios){
			System.out.println(infoUsuario);
		}
	}

	public void listarCupones() {
		ArrayList<String> listaInfoCupones = gestorCupones.obtenerInfoCupones();

		for(String infoCupon : listaInfoCupones){
			System.out.println(infoCupon);
		}
	}

	public void listarProductos() {
		ArrayList<String> listaInfoProductos = gestorProductos.obtenerInfoProductos();

		for(String infoProducto : listaInfoProductos){
			System.out.println(infoProducto);
			System.out.println("============");
		}
	}
}