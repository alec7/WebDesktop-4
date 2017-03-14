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
import modelo.DisponibilidadEsteticista;
import modelo.Maestro_servicio;
import modelo.Servicio;
import modeloDAO.MaestroDAO;
import modeloDAO.SolicitarCitaDAO;
import controlador.MaestroListService;
import org.postgresql.translation.messages_bg;
import org.zkoss.lang.Strings;
import org.zkoss.web.mesg.MWeb;
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
	Integer indiceComboEsteticista;
	String servicio;
	String sexo;
	List<Bloque> arr_bloquesC = new ArrayList<Bloque>();
	List<DisponibilidadCubiculo> arr_bloquesD = new ArrayList<DisponibilidadCubiculo>();
	List<DisponibilidadEsteticista> arr_bloquesE = new ArrayList<DisponibilidadEsteticista>();
	List<DisponibilidadEsteticista> arr_bloquesF = new ArrayList<DisponibilidadEsteticista>();
	
	
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
	Listbox comboSexo;
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
		List<DisponibilidadEsteticista> arr_bloquesG = new ArrayList<DisponibilidadEsteticista>();
		List<DisponibilidadCubiculo> arr_bloquesFinal = new ArrayList<DisponibilidadCubiculo>();
		List<Cubiculo> cubiculosSegunServicio = new ArrayList<>();
		GregorianCalendar fe = new GregorianCalendar();
		fe.setTime(fecha.getValue());
		diaSemana= ObtenerDia(fe.get(Calendar.DAY_OF_WEEK));
		diaEspecifico =  fecha.getText();
		servicio = comboServicio.getSelectedItem().getLabel();
		
		sexo = comboSexo.getSelectedItem().getLabel();
		indiceComboEsteticista=comboEsteticista.getSelectedIndex();
		
		Messagebox.show(String.valueOf(indiceComboEsteticista));
		
		
	if(indiceComboEsteticista!= -1)
	{		
		
	
		esteticista= comboEsteticista.getSelectedItem().getLabel();
		arr_bloquesA= solidao.VerificarEsteticistaDia(esteticista,diaSemana); //Verifica si el esteticista seleccionado trabaja el dia especificado, y devuelve los bloques donde trabaja, en caso contrario retorna 0

		if (arr_bloquesA.size()==0) //en caso de que el esteticista no trabaje el dia de la semana especificado
		{
			servicios.setEmptyMessage("No se encuentra el elemento solicitado");
		}
		
		//arr_bloquesA = Tiene todos los bloques donde trabaja el esteticista especificado el dia de la semana especificado
		//arr_bloquesB = Tiene los bloques donde trabaja (esta ocupado o activo) el esteticista especificando el dia del calendario 
		
		arr_bloquesB = solidao.VerficarEsteticistaDiaEspecifico(diaEspecifico,esteticista);
		
		Messagebox.show(String.valueOf(arr_bloquesA.size()) + String.valueOf(arr_bloquesB.size()) );
		
		RestarElementos(arr_bloquesA,arr_bloquesB);
		
		
		
		//------------------------------------------------------------------------------------------
		
		cubiculosSegunServicio = solidao.BuscarCubiculosSegunServicio(servicio); //trae los cubiculos donde el cliente puede realizarce el servicio
		
		//------------------------------------------------------------------------------------------
		
		solidao.resultante = new ArrayList<DisponibilidadCubiculo>();
		for (int i = 0; i < cubiculosSegunServicio.size(); i++) 
		{
			
			arr_bloquesD= solidao.BloquesDisponiblesDelCubiculo(cubiculosSegunServicio.get(i).getCodigo(),diaEspecifico);
			
		}
		
		//-----------------------Eliminando repetidos-------------------------------------------------------------------
		
		
		Messagebox.show("AQUIIII "+String.valueOf(arr_bloquesD.size()));
		
		for (int i = 0; i < arr_bloquesD.size(); i++) 
		{
			Messagebox.show(arr_bloquesD.get(i).getCodigo_bloque());
			
		}
		
		
		for (int k = 0; k < arr_bloquesD.size(); k++)
		{
			
			for (int l = k+1; l < arr_bloquesD.size(); l++) 
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
		

	
		
		servicios.setModel(new ListModelList<DisponibilidadCubiculo>(arr_bloquesFinal));
		
		

		//------------------------------------------------------------------------------------------
		arr_bloquesC.clear();
		arr_bloquesD.clear();
		

		//------------------------------------------------------------------------------------------
		
	}
	
	else

	{
		
		
		
		arr_bloquesD.clear(); // limpio el arreglo
		
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
			
			for (int l = k+1; l < arr_bloquesD.size(); l++) 
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
		
		
		arr_bloquesE= solidao.VerficarDiaEspecifico(diaSemana); //devuelve todos los bloques posibles donde se puede trabajar el dia especificado
		
		Messagebox.show(String.valueOf("Aquiiiii"+arr_bloquesE.size()));

		solidao.arr_bloquesOcupados.clear();
		for (int i = 0; i < arr_bloquesE.size(); i++)
		{
			arr_bloquesF=solidao.VerificarDisponibilidadBloquesDeTodosLosEsteticistas(arr_bloquesE.get(i),diaEspecifico);
			
		}
		
		Messagebox.show(String.valueOf("nro de ocupados: "+arr_bloquesF.size()));
		
		for (int i = 0; i < arr_bloquesF.size(); i++) 
		{
			Messagebox.show("OCUPADOS: "+arr_bloquesF.get(i).getCodigo_bloque()+" "+arr_bloquesF.get(i).getCodigo_esteticista());
			
		}
		
		
		if(arr_bloquesF.size()!=0)
		{	
		
		for (int i = 0; i < arr_bloquesF.size(); i++) {
			
			for (int k = 0; k < arr_bloquesE.size(); k++) 
			{
				if(arr_bloquesF.get(i).getCodigo_bloque().equals(arr_bloquesE.get(k).getCodigo_bloque()) && arr_bloquesF.get(i).getCodigo_esteticista().equals(arr_bloquesE.get(k).getCodigo_esteticista()))
				{
					arr_bloquesE.remove(k);
							
				}
			}	
		}
		}
		
	
		
		
		for (int i = 0; i < arr_bloquesE.size(); i++) 
		{
			Messagebox.show("Bloques JK: "+arr_bloquesE.get(i).getCodigo_bloque()+" "+arr_bloquesE.get(i).getCodigo_esteticista());
			
		}
		
		
		
		//-----------------------Eliminando repetidos-------------------------------------------------------------------
		
		for (int k = 0; k < arr_bloquesE.size(); k++)
		{
			
			for (int l = k+1; l < arr_bloquesE.size(); l++) 
			{
				if(arr_bloquesE.get(k).getCodigo_bloque().equals( arr_bloquesE.get(l).getCodigo_bloque()))
				{
					Messagebox.show("entro y elimino al bloque: "+arr_bloquesE.get(l).getCodigo_bloque()+"del est: "+arr_bloquesE.get(l).getCodigo_esteticista());
					Messagebox.show("codigo del bloque E: "+arr_bloquesE.get(k).getCodigo_bloque() +"="+arr_bloquesE.get(l).getCodigo_bloque()+"?");
					arr_bloquesE.remove(l);
					
				}
			}
	
		}
		
		Messagebox.show(String.valueOf(arr_bloquesE.size()));
		
		//------------------------------------------------------------------------------------------
		

		servicios.setModel(new ListModelList<DisponibilidadEsteticista>(arr_bloquesE));
		

		//--------------------------------------------------------------------------------------------
		
		
		
		
		
		
	}
		
		
		
		
		
		
		
	
		
		
		
	
		
		
		
	
		
		
		
		
		
		
		
		
		
		
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
		
		arr_bloquesC.clear();
			for (int i = 0; i < arr_bloquesA.size(); i++) {
				
			if(arr_bloquesB.size()==0)
			{
				Messagebox.show("entro");
				arr_bloquesC = arr_bloquesA;
				Messagebox.show(String.valueOf(arr_bloquesC.size()));
			}
			
			else
			{
				for (int k = 0; k < arr_bloquesB.size(); k++) 
				{
					if(!arr_bloquesA.get(i).getCodigo().equals(arr_bloquesB.get(k).getCodigo()))
					{
						arr_bloquesC.add(arr_bloquesA.get(i));
								
					}
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
