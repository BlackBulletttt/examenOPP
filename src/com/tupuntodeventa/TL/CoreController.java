package com.tupuntodeventa.TL;

import com.tupuntodeventa.BL.Orden.OrdenBL;
import com.tupuntodeventa.BL.Producto.ProductoBL;
import com.tupuntodeventa.BL.Puesto.PuestoBL;
import com.tupuntodeventa.BL.Usuario.UsuarioBL;

public class CoreController {
	static UsuarioBL logicaUsuarios = new UsuarioBL();
	static PuestoBL logicaPuestos = new PuestoBL();
	static ProductoBL logicaProductos = new ProductoBL();
	static OrdenBL logicaOrdenes = new OrdenBL();
}
