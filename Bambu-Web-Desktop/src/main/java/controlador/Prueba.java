package controlador;

import org.apache.poi.hssf.record.FnGroupCountRecord;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class Prueba  extends GenericForwardComposer{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	private Include myInclude;
	Session miSession = Sessions.getCurrent();

//	@Listen("onClick =#mensaje")
//	public void mostrarMensaje(MouseEvent event){
//	alert("hola");
//		//Messagebox.show("hola");
//	}
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
	}
	
	public void onClick$dia_laborable(Event e){
		miSession.setAttribute("master","tb_dia_laborable");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	
	public void onClick$estado(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_estado");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	

	public void onClick$orientacion(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_orientacion");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$rol(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_rol");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_acuerdo(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_acuerdo");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_cliente(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_cliente");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_comentario(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_comentario");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_incidencia(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_incidencia");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_noticia(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_noticia");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_notificacion(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_notificacion");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_organizacion(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_organizacion");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_pregunta(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_pregunta");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_red_social(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_red_social");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$tipo_servicio(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_servicio");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$tipo_referencia(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_tipo_referencia");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

	public void onClick$turno(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_turno");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$antecedente(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_antecedente");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$avance(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_avance");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$cubiculo(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_cubiculo");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$equipo(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_equipo");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$habito(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_habito");
		myInclude.setSrc(null);
		myInclude.setSrc("maestro.zul");
	}
	public void onClick$indicador(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_indicador");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$necesidad(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_necesidad");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$ocupacion(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_ocupacion");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}
	public void onClick$preferencia(Event e)throws InterruptedException{
		miSession.setAttribute("master","tb_preferencia");
		myInclude.setSrc(null);
		myInclude.setSrc("maestrico.zul");
	}

}
