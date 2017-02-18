package controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;

public class ControladorInclude extends GenericForwardComposer {
	
	private Include myInclude;
	private boolean isInitial=true;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
	}
	
	public void onClick$dia_laborable(Event e)throws InterruptedException{
		myInclude.setSrc("vista/cliente/consultaDiagnostico.zul");
	}

}
