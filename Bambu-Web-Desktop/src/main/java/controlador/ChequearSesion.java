package controlador;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import modelo.Usuario;

public class ChequearSesion implements Initiator {

	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		// TODO Auto-generated method stub
		 Usuario datos = new ControladorDatos().obtenerDatos();
			if(datos == null)
				Executions.sendRedirect("login.zul");
			return;
	}

	

}
