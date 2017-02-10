package controlador;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Window;

public class ControladorMenu extends GenericForwardComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Nav padre;
	Navitem hijo;
	
	public void onClick$btn()
	{
		padre.setParent(null);
		hijo.setParent(padre);
	}
	

	
}
