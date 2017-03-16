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
import modelo.Cita;
import modelo.Esteticista;
import modelo.Maestro_servicio;
import modelo.Promocion;
import modelo.Servicio;
import modeloDAO.EsteticistaDAO;
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
	List<Cita> arr_bloquesC = new ArrayList<Cita>();
	List<Cita> arr_bloquesD = new ArrayList<Cita>();
	//List<Cita> arr_bloquesE = new ArrayList<Cita>();
	List<Cita> arr_bloquesF = new ArrayList<Cita>();
	List<Cita> arr_bloquesFinal = new ArrayList<Cita>();
	List<Cita> arr_bloquesEst = new ArrayList<Cita>();
	
	
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
	@Wire
	Button guardar;
	
	
	ListModelList<Esteticista> esteticistaListModel;
	List<Esteticista> listEsteticista= new ArrayList<Esteticista>();
	EsteticistaDAO  estDao=  new EsteticistaDAO();

	//-----------------------------------Inicio-----------------------------------------------------
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);

		
		
	}
	@Listen("onSelect = #comboSexo")
	public void cargarEsteticistas()
	{
		listEsteticista= new ArrayList<Esteticista>();
		if(comboSexo.getSelectedItem().getLabel().equalsIgnoreCase("Masculino")){
			comboEsteticista.setDisabled(false);
			listEsteticista = estDao.buscarEsteticistaSexo("Masculino");
			esteticistaListModel = new ListModelList<Esteticista>(listEsteticista);
			comboEsteticista.setModel(esteticistaListModel);
		}
		if(comboSexo.getSelectedItem().getLabel().equalsIgnoreCase("Femenino")){
			comboEsteticista.setDisabled(false);
			listEsteticista = estDao.buscarEsteticistaSexo("Femenino");
			esteticistaListModel = new ListModelList<Esteticista>(listEsteticista);
			comboEsteticista.setModel(esteticistaListModel);
		}
		if(comboSexo.getSelectedItem().getLabel().equalsIgnoreCase("Me es indiferente")){
			comboEsteticista.setDisabled(true);
			comboEsteticista.clearSelection();
		}
		
	}
	
//-----------------------------------------------------------------------------------------------------

	//Cuando el usuario hace clic en el Boton BUSCAR (Opciones)
	@Listen("onClick = #buscarOpciones")
	public void doBuscarOpciones()
	{
		List<Cita> arr_bloquesA = new ArrayList<Cita>();
		List<Cita> arr_bloquesB = new ArrayList<Cita>();
		List<Cita> arr_bloquesG = new ArrayList<Cita>();
		
		List<Cita> cubiculosSegunServicio = new ArrayList<>();
		GregorianCalendar fe = new GregorianCalendar();
		fe.setTime(fecha.getValue());
		diaSemana= ObtenerDia(fe.get(Calendar.DAY_OF_WEEK));
		diaEspecifico =  fecha.getText();
		servicio = comboServicio.getSelectedItem().getLabel();
		
		sexo = comboSexo.getSelectedItem().getLabel();
		indiceComboEsteticista=comboEsteticista.getSelectedIndex();
		
		Messagebox.show(String.valueOf(indiceComboEsteticista));
		
		arr_bloquesFinal.clear();
		
		
	if(indiceComboEsteticista!= -1)
	{		
		
	
		
		esteticista= comboEsteticista.getSelectedItem().getLabel();
		arr_bloquesA= solidao.VerificarEsteticistaDia(esteticista,diaSemana); //Verifica si el esteticista seleccionado trabaja el dia especificado, y devuelve los bloques donde trabaja, en caso contrario retorna 0

		if (arr_bloquesA.size()==0) //en caso de que el esteticista no trabaje el dia de la semana especificado
		{
			servicios.setEmptyMessage("No se encuentra el elemento soliCitado");
		}
		
		//arr_bloquesA = Tiene todos los bloques donde trabaja el esteticista especificado el dia de la semana especificado
		//arr_bloquesB = Tiene los bloques donde trabaja (esta ocupado o activo) el esteticista especificando el dia del calendario 
		
		arr_bloquesB = solidao.VerficarEsteticistaDiaEspecifico(diaEspecifico,esteticista);
		
		Messagebox.show(String.valueOf(arr_bloquesA.size()) + String.valueOf(arr_bloquesB.size()) );
		
		RestarElementos(arr_bloquesA,arr_bloquesB);
		
		
		
		//------------------------------------------------------------------------------------------
		
		cubiculosSegunServicio = solidao.BuscarCubiculosSegunServicio(servicio); //trae los cubiculos donde el cliente puede realizarce el servicio
		
		//------------------------------------------------------------------------------------------
		
		solidao.resultante = new ArrayList<Cita>();
		for (int i = 0; i < cubiculosSegunServicio.size(); i++) 
		{
			
			arr_bloquesD= solidao.BloquesDisponiblesDelCubiculo(cubiculosSegunServicio.get(i).getCodigo_cubiculo(),diaEspecifico);
			
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
		
		arr_bloquesFinal=Intersectar(arr_bloquesC,arr_bloquesD); //bloque C tiene est y bloque y bloque D tiene cub y bloque
		
		Messagebox.show(String.valueOf("Nro Total de Bloques Disponibles"+arr_bloquesFinal.size()));
		

		
		
		servicios.setModel(new ListModelList<Cita>(arr_bloquesFinal));
		

		

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
		
		solidao.resultante = new ArrayList<Cita>();
		for (int i = 0; i < cubiculosSegunServicio.size(); i++) 
		{
			//AQUI TIENE LOS CODIGOS
			arr_bloquesD= solidao.BloquesDisponiblesDelCubiculo(cubiculosSegunServicio.get(i).getCodigo_cubiculo(),diaEspecifico);
			
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
			Messagebox.show("Bloque D: "+ arr_bloquesD.get(d).getCodigo_bloque() +"AQUIII"+ arr_bloquesD.get(d).getCodigo_cubiculo());
		}
		
		//------------------------------------------------------------------------------------------
		
		
		arr_bloquesEst= solidao.VerficarDiaEspecifico(diaSemana); //devuelve todos los bloques posibles donde se puede trabajar el dia especificado
		//System.out.println(arr_bloquesEst.get(0).getCodigo_cubiculo());
		Messagebox.show(String.valueOf("Aquiiiii"+arr_bloquesEst.size()));
		solidao.arr_bloquesOcupados.clear();
		for (int i = 0; i < arr_bloquesEst.size(); i++)
		{
			
			
			arr_bloquesF=solidao.VerificarDisponibilidadBloquesDeTodosLosEsteticistas(arr_bloquesEst.get(i),diaEspecifico);
			
		}
		
		Messagebox.show(String.valueOf("nro de ocupados: "+arr_bloquesF.size()));
		
		for (int i = 0; i < arr_bloquesF.size(); i++) 
		{
			Messagebox.show("OCUPADOS: "+arr_bloquesF.get(i).getCodigo_bloque()+" "+arr_bloquesF.get(i).getCodigo_esteticista());
			
		}

		if(arr_bloquesF.size()!=0)
		{	
			
		for (int i = 0; i < arr_bloquesF.size(); i++) {
			
			for (int k = 0; k < arr_bloquesEst.size(); k++) 
			{
				if(arr_bloquesF.get(i).getCodigo_bloque().equals(arr_bloquesEst.get(k).getCodigo_bloque()) && arr_bloquesF.get(i).getCodigo_esteticista().equals(arr_bloquesEst.get(k).getCodigo_esteticista()))
				{
					arr_bloquesEst.remove(k);
					
				}
			}	
		}
		}
		
	
		
		
		for (int i = 0; i < arr_bloquesEst.size(); i++) 
		{
			Messagebox.show("Bloques JK: "+arr_bloquesEst.get(i).getCodigo_bloque()+" "+arr_bloquesEst.get(i).getCodigo_esteticista());
			
		}
		
		
		
		//-----------------------Eliminando repetidos-------------------------------------------------------------------
		
		for (int k = 0; k < arr_bloquesEst.size(); k++)
		{
			
			for (int l = k+1; l < arr_bloquesEst.size(); l++) 
			{
				if(arr_bloquesEst.get(k).getCodigo_bloque().equals( arr_bloquesEst.get(l).getCodigo_bloque()))
				{
					
					Messagebox.show("entro y elimino al bloque: "+arr_bloquesEst.get(l).getCodigo_bloque()+"del est: "+arr_bloquesEst.get(l).getCodigo_esteticista());
					Messagebox.show("codigo del bloque E: "+arr_bloquesEst.get(k).getCodigo_bloque() +"="+arr_bloquesEst.get(l).getCodigo_bloque()+"?");
					arr_bloquesEst.remove(l);
					
				}
			}
	
		}
		
		Messagebox.show(String.valueOf(arr_bloquesEst.size()));
		
		arr_bloquesFinal.clear();
		
		arr_bloquesFinal=Intersectar(arr_bloquesEst, arr_bloquesD);
		
		//------------------------------------------------------------------------------------------
		
		
		servicios.setModel(new ListModelList<Cita>(arr_bloquesFinal));
		
		
		//--------------------------------------------------------------------------------------------`
		
		
		
		
		
		
		
		
		
		
	}
	
	

		
		
		
		
		
		
		
	
		
		
		
	
		
		
		
	
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	//Cuando el usuario hace clic en el Boton Guardar 
	@Listen("onClick = #guardar")
	public void doGuardarOpcion()
	{
		
		/*Messagebox.show(String.valueOf(servicios.getSelectedItem().getIndex()));
		
		for (int i = 0; i < arr_bloquesFinal.size(); i++) 
		{
			Messagebox.show("Posicion"+i+" "+arr_bloquesFinal.get(i).getCodigo_bloque());
			
		}*/
		
		int posicion= servicios.getSelectedIndex();
		
	
		
		String codigo= solidao.TotalRegistros("tb_cita");
		Messagebox.show(arr_bloquesFinal.get(posicion).getCodigo_cubiculo());
		
		
		Cita ci = new Cita(codigo, null, arr_bloquesFinal.get(posicion).getCodigo_cubiculo(), "Activo", null,fecha.getText() , arr_bloquesFinal.get(posicion).getCodigo_esteticista(), comboServicio.getSelectedItem().getLabel(), arr_bloquesFinal.get(posicion).getCodigo_bloque());
		
		
		solidao.InsetarCita(ci);
		
		
		
	
	}
	
	
	



	

	private List<Cita> Intersectar(List<Cita> a1,List<Cita> a2)  //a1->est+bloque     a2->cub+bloque
	{
		List<Cita> af = new ArrayList<Cita>();
	
			
			for (int i = 0; i < a1.size(); i++) 
			{
				
				for (int k = 0; k < a2.size(); k++) 
				{
					if(a1.get(i).getCodigo_bloque().equals(a2.get(k).getCodigo_bloque()))
					{
						af.add(new Cita(null, null, a2.get(k).getCodigo_cubiculo(), null, null, null, a1.get(i).getCodigo_esteticista(), null, a1.get(i).getCodigo_bloque()))	;
					}
				}	
			}
			
			
			return af;
	}
	

	private void RestarElementos(List<Cita> arr_bloquesA, List<Cita> arr_bloquesB) 
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
					if(!arr_bloquesA.get(i).getCodigo_bloque().equals(arr_bloquesB.get(k).getCodigo_bloque()))
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
