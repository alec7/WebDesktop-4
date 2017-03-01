package controlador;

import java.util.ArrayList;

//import org.apache.catalina.util.StringParser;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import modeloDAO.OpcionDAO;
import modelo.Opcion;

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
		Session miSession1 = Sessions.getCurrent();
		Session miSession2 = Sessions.getCurrent();
    
	
		
		
	
		
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
			 submenu = dao.buscarHijos("00001", menu.get(i).getCodigo());
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
				 final String tabla = opcionHijo.getTabla();
				 navHijo.addEventListener(Events.ON_OPEN ,new EventListener() {
//					@Override
						public void onEvent(Event event) throws Exception {
							
							if(tabla!=null)
					    	{
					    		miSession1.setAttribute("master",tabla);
								myInclude.setSrc(null);
								myInclude.setSrc(vinculo);
					    	}
					    	else
					    	{
					    		myInclude.setSrc(null);
								myInclude.setSrc(vinculo);
					    	}
						}						 
					 });
					// @Override
//					 public void onEvent(Event event1) throws Exception{
//					    	
//								myInclude.setSrc(null);
//								myInclude.setSrc(vinculo);
//					    	
//					    	
//					    	
//					    	// Executions.sendRedirect(vinculo);
//					    	 //Messagebox.show(opcionHijo.getVinculo());
//					    }
//					});
				 navHijo.setParent(nav);
				 submenuNieto = dao.buscarNietos("00001",  submenu.get(j).getCodigo());
				 //System.out.println(menu.get(j).getId_opcion());
				 // Messagebox.show(opcionHijo.getVinculo());
				
				 for(int k=0; k<submenuNieto.size(); k++)
				 {
					 opcionNieto = submenuNieto.get(k);
					 String subTextoNieto = opcionNieto.getTexto();
					 Navitem navNieto = new Navitem();
					 navNieto.setLabel(subTextoNieto);
					final String vinculoNieto = opcionNieto.getVinculo();
					 final String tabla1 = opcionNieto.getTabla();
					 navNieto.addEventListener(Events.ON_CLICK, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							
							if(tabla1!=null)
					    	{
					    		miSession1.setAttribute("master",tabla1);
								myInclude.setSrc(null);
								myInclude.setSrc(vinculoNieto);
					    	}
					    	else
					    	{
					    		myInclude.setSrc(null);
								myInclude.setSrc(vinculoNieto);
					    	}
						}						 
					 });
					 navNieto.setParent(navHijo);
				 }
				 
			 }
		  }
	   }
		  }
	
	
	

	public void onClick$cerrarSesion()
	{
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
            public void onEvent(ClickEvent event) throws Exception {
                if(Messagebox.Button.YES.equals(event.getButton())) {
            			Session miSession = Sessions.getCurrent();
            			miSession.invalidate();
            			
            			Executions.sendRedirect("login.zul");
                }
            }
        };
        Messagebox.show("¿Realmente desea cerrar la sesión?", "Mensaje de confirmación", new Messagebox.Button[]{
                Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	
}
