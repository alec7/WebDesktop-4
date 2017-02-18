package controlador;

import java.util.ArrayList;

import org.apache.catalina.util.StringParser;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import modeloDAO.OpcionDAO;
import seguridadFuncional.Opcion;

public class ControladorMenu extends  GenericForwardComposer<Window> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Wire
	    Navitem ficha;
	    @Wire
	    Navbar menuNav;
	
	  OpcionDAO dao = new OpcionDAO();
	  Opcion opcion = new Opcion();
	  Opcion opcionHijo = new Opcion();
	  Opcion opcionNieto = new Opcion();
	  ArrayList<Opcion> menu = new ArrayList<Opcion>();
	  ArrayList<Opcion> submenu = new ArrayList<Opcion>();
	  ArrayList<Opcion> submenuNieto = new ArrayList<Opcion>();
	  
	  private Include myInclude;
		Session miSession = Sessions.getCurrent();
	  
@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onCreate$menu()
	{
		menu= dao.buscarPadre();
		 
		  for(int i=0; i< menu.size(); i++)
		  {
			 opcion = menu.get(i);
			 String texto = opcion.getTexto();
			 String icono = opcion.getIcono();
			 Nav nav = new Nav();
			 nav.setIconSclass(icono);
			 nav.setLabel(texto);
			 nav.setParent(menuNav);
			 //Messagebox.show(texto);
			 submenu = dao.buscarHijos("00001", menu.get(i).getId_opcion());
			// System.out.println(menu.get(i).getId_opcion());
			 //Messagebox.show(String.valueOf(menu.size()));
			// Messagebox.show(String.valueOf(submenu.size()));
			 if(submenu.size()!=0){
			 for(int j=0; j<submenu.size(); j++)
			 {
				 opcionHijo = submenu.get(j);
				 String subTexto = opcionHijo.getTexto();
				 String iconoHijo = opcionHijo.getIcono();
				 Nav navHijo = new Nav();
				 navHijo.setIconSclass(iconoHijo);
				 navHijo.setLabel(subTexto);
				 final String vinculo = opcionHijo.getVinculo();
				 navHijo.addEventListener(Events.ON_OPEN, new EventListener() {
					    public void onEvent(Event event) {
					    	miSession.setAttribute("master","tb_dia_laborable");
							myInclude.setSrc(null);
							myInclude.setSrc(vinculo);
					    	// Executions.sendRedirect(vinculo);
					    	 //Messagebox.show(opcionHijo.getVinculo());
					    }
					});
				 navHijo.setParent(nav);
				 submenuNieto = dao.buscarNietos("00001",  submenu.get(j).getId_opcion());
				 //System.out.println(menu.get(j).getId_opcion());
				 // Messagebox.show(opcionHijo.getVinculo());
				 for(int k=0; k<submenuNieto.size(); k++)
				 {
					 opcionNieto = submenuNieto.get(k);
					 String subTextoNieto = opcionNieto.getTexto();
					 Navitem navNieto = new Navitem();
					 navNieto.setLabel(subTextoNieto);
					 navNieto.setParent(navHijo);
				 }
				 
			 }
		  }
	   }
	}
	
	
	
	
}
