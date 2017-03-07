package controlador;

import modelo.Cliente;
import modelo.Cod_Des;
import modeloDAO.ClienteDAO;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

public class ControladorFichaCliente extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	@Wire
	Textbox cedula;
	@Wire
	Textbox apellido;
	@Wire
	Textbox nombre;
	
	ClienteDAO cdao = new ClienteDAO();
	Cliente c;
	
	@Listen("onClick = #buscarCliente")
	public void buscar()
	{
		c = cdao.buscarCliente(cedula.getText());
		if(c!=null){
			nombre.setText(c.getNombre());
			apellido.setText(c.getApellido());
		}
		else{
			Messagebox.show("Cliente no Registrado ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	
	
}
