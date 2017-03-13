/* 
Autor: Jesus Kahwati
*/
package controlador;



import java.text.DateFormat;
import java.text.SimpleDateFormat;

//--------------------------------------------Imports--------------------------------------------------------------

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import controlador.MaestroListServiceChapter6Impl;
import modelo.Bloque;
import modelo.Cod_Des;
import modelo.Cubiculo;
import modelo.DisponibilidadCubiculo;
import modelo.Maestro_servicio;
import modelo.Servicio;
import modeloDAO.MaestroDAO;
import modeloDAO.SolicitarCitaDAO;
import controlador.MaestroListService;
import org.postgresql.translation.messages_bg;
import org.zkoss.lang.Strings;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

//-----------------------------Declaraciones--------------------------------------------------------------------
public class ControladorSolicitarCita extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	String diaSemana="";
	String diaEspecifico;
	String de;
	String esteticista;
	String servicio;
	List<Bloque> arr_bloquesC = new ArrayList<Bloque>();
	List<DisponibilidadCubiculo> arr_bloquesD = new ArrayList<DisponibilidadCubiculo>();
	
	SolicitarCitaDAO solidao = new SolicitarCitaDAO();
	
	/*
	MaestroDAO mdao = new MaestroDAO();
	
	List<Servicio> listaTodosServicios = new ArrayList<Servicio>();
	List<Cod_Des> listaServiciosAsociados = new ArrayList<Cod_Des>();
	List<Servicio> listaTodosServiciosFiltrado = new ArrayList<Servicio>();
	Session miSession = Sessions.getCurrent();
	String tabla =  miSession.getAttribute("master").toString();
	
	*/

//-------------------------------wire components------------------------------------------------------
	@Wire
	Listbox comboServicio;
	@Wire
	Listbox comboEsteticista;
	@Wire
	Label titulo;
	@Wire
	Button buscarOpciones;
	@Wire
	Listbox servicios;
	@Wire
	Checkbox selectedMaestroCheck;
	@Wire
	Radiogroup selectedMaestroPriority;
	@Wire
	Datebox fecha;


	//-----------------------------------Inicio-----------------------------------------------------
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);

		
		
	}
	
//-----------------------------------------------------------------------------------------------------

	//Cuando el usuario hace clic en el Boton BUSCAR (Opciones)
	@Listen("onClick = #buscarOpciones")
	public void doBuscarOpciones()
	{
		List<Bloque> arr_bloquesA = new ArrayList<Bloque>();
		List<Bloque> arr_bloquesB = new ArrayList<Bloque>();
		List<DisponibilidadCubiculo> vacio = new ArrayList<DisponibilidadCubiculo>();
		List<DisponibilidadCubiculo> arr_bloquesFinal = new ArrayList<DisponibilidadCubiculo>();
		List<Cubiculo> cubiculosSegunServicio = new ArrayList<>();

		
	
		
	
		GregorianCalendar fe = new GregorianCalendar();
		fe.setTime(fecha.getValue());
		diaSemana= ObtenerDia(fe.get(Calendar.DAY_OF_WEEK));
		diaEspecifico =  fecha.getText();
		servicio = comboServicio.getSelectedItem().getLabel();
		esteticista = comboEsteticista.getSelectedItem().getLabel();
		
		
		
		//de=DateFormat.getDateInstance().format(diaEspecifico);
		
		arr_bloquesA= solidao.VerificarEsteticistaDia(esteticista,diaSemana);

		//Messagebox.show(String.valueOf(arr_bloquesA.size()));
		
		if (arr_bloquesA.size()==0) //en caso de que el esteticista no trabaje el dia de la semana especificado
		{
			servicios.setEmptyMessage("No se encuentra el elemento solicitado");
		}
		
	
		
		
		arr_bloquesB = solidao.VerficarEsteticistaDiaEspecifico(diaEspecifico,esteticista);
		
		Messagebox.show(String.valueOf(arr_bloquesA.size()) + String.valueOf(arr_bloquesB.size()) );
		
		RestarElementos(arr_bloquesA,arr_bloquesB);
		
		//Messagebox.show(arr_bloquesC.get(0).getCodigo());
		
		//------------------------------------------------------------------------------------------
		
		cubiculosSegunServicio = solidao.BuscarCubiculosSegunServicio(servicio); //trae los cubiculos donde el cliente puede realizarce el servicio
		
		//------------------------------------------------------------------------------------------
		
		solidao.resultante = new ArrayList<DisponibilidadCubiculo>();
		for (int i = 0; i < cubiculosSegunServicio.size(); i++) 
		{
			
			arr_bloquesD= solidao.BloquesDisponiblesDelCubiculo(cubiculosSegunServicio.get(i).getCodigo(),diaEspecifico);
			
		}
		
		//-----------------------Eliminando repetidos-------------------------------------------------------------------
		
		for (int k = 0; k < arr_bloquesD.size(); k++)
		{
			
			for (int l = 1; l < arr_bloquesD.size(); l++) 
			{
				if(arr_bloquesD.get(k).getCodigo_bloque().equals( arr_bloquesD.get(l).getCodigo_bloque()))
				{
					arr_bloquesD.remove(l);
				}
			}
	
		}
		
		//------------------------------------------------------------------------------------------
		
		Messagebox.show("Bloque D tiene: "+String.valueOf(arr_bloquesD.size()));
		
		
		for (int d = 0; d < arr_bloquesD.size(); d++)
		{
			Messagebox.show("Bloque D: "+ arr_bloquesD.get(d).getCodigo_bloque());
		}
		
		//------------------------------------------------------------------------------------------
		
		arr_bloquesFinal=Intersectar(arr_bloquesC,arr_bloquesD);
		
		Messagebox.show(String.valueOf("Nro Total de Bloques Disponibles"+arr_bloquesFinal.size()));
		

		servicios.setModel(new ListModelList<DisponibilidadCubiculo>(vacio));
		
		servicios.setModel(new ListModelList<DisponibilidadCubiculo>(arr_bloquesFinal));
		
		
		
		
	
		
		
		//------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
	
		
		
		
	
		
		
		
	
		
		
		
		
		
		
		
		
		
		
	}



	

	private List<DisponibilidadCubiculo> Intersectar(List<Bloque> a1,List<DisponibilidadCubiculo> a2) 
	{
		List<DisponibilidadCubiculo> af = new ArrayList<DisponibilidadCubiculo>();
	
			
			for (int i = 0; i < a1.size(); i++) 
			{
				
				for (int k = 0; k < a2.size(); k++) 
				{
					if(a1.get(i).getCodigo().equals(a2.get(k).getCodigo_bloque()))
					{
						af.add(new DisponibilidadCubiculo(null, a2.get(k).getCodigo_cubiculo(), null, a1.get(i).getCodigo()))	;	
					}
				}	
			}
			
			
			return af;
	}
	

	private void RestarElementos(List<Bloque> arr_bloquesA, List<Bloque> arr_bloquesB) 
	{
		
			
			for (int i = 0; i < arr_bloquesA.size(); i++) {
				
				for (int k = 0; k < arr_bloquesB.size(); k++) 
				{
					if(!arr_bloquesA.get(i).getCodigo().equals(arr_bloquesB.get(k).getCodigo()))
					{
						arr_bloquesC.add(arr_bloquesA.get(i));
								
					}
				}	
			}
		
		
	}

	private String ObtenerDia(int dia)  //Metodo que retorna el dia dado el numero de dia
	
	{ 
		String d= "";
	
		if(dia ==1)
			d="Domingo";
		if(dia ==2)
			d="Lunes";
		if(dia ==3)
			d="Martes";
		if(dia ==4)
			d="Miercoles";
		if(dia ==5)
			d="Jueves";
		if(dia ==6)
			d="Viernes";
		if(dia ==7)
			d="Sabado";
		
		
		return d;
		
	}
	

}
