package controlador;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import modelo.Bloque;
import modeloDAO.BloqueDAO;

public class ControladorBloque extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox bloque;
	
	@Wire
	private Timebox horaInicio;
	@Wire
	private Timebox horaFin;
	
	
	
	BloqueDAO bloqueDao = new BloqueDAO(); 
	
	@Listen("onClick = #guardar")
	public void guardarBloque(){
		
		String codigo = bloqueDao.TotalRegistros();
		String horaI = horaInicio.getText();
		String horaF = horaFin.getText();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy a hh:mm");
		String fechaStr = "22-03-2017 "+horaI+"";  
		String fechaStr1 = "22-03-2017 "+horaF+"";
		Date fechaNueva = null;
		Date fechaNueva1 = null;
		Timestamp sq = null;
		Timestamp sq1 = null;
		try {
			fechaNueva = format.parse(fechaStr);
			fechaNueva1 = format.parse(fechaStr1);
			sq = new java.sql.Timestamp(fechaNueva.getTime());
			sq1 = new java.sql.Timestamp(fechaNueva1.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bloque b = new Bloque(codigo, bloque.getText(), "Activo", sq, sq1);
		bloqueDao.agregarBloque(b);
		bloque.setText("");
		Messagebox.show("Bloque Registrado Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
//		Window mensaje = (Window)Executions.createComponents("vista/mensajes/mensaje.zul", null, null);
//		mensaje.doModal();
		
		
	}
	@Listen("onClick = #cancelar")
	public void cancelar(){
		bloque.setText("");
	}
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/registrarBloque.html");
	}
	

}
