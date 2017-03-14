package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;

import bean.Conexion;
import modelo.Bloque;
import modelo.Cod_Des;
import modelo.Cubiculo;
import modelo.DisponibilidadCubiculo;
import modelo.DisponibilidadEsteticista;
import modelo.Maestro;
import modelo.Maestro_servicio;
import modelo.Servicio;
import modelo.Usuario;

public class SolicitarCitaDAO extends ConexionDAO
{
	public List<DisponibilidadCubiculo> resultante = new ArrayList<DisponibilidadCubiculo>();
	public List<DisponibilidadEsteticista> arr_bloquesOcupados = new ArrayList<DisponibilidadEsteticista>();
	
	public SolicitarCitaDAO() 
	{		
		super();
		
	
	
	
	}



	public  List<Bloque> VerificarEsteticistaDia(String esteticista, String diaSemana) 
	{
		String tiraSQL = "select  dl.descripcion , codigo_bloque, nombre||' '||apellido  nombre, cedula cedula from tb_horario h, tb_esteticista e, tb_horario_esteticista he, tb_dia_laborable dl where e.cedula = ( SELECT cedula from tb_esteticista where nombre||' '||apellido = '"+esteticista+"') and h.codigo = he.codigo_horario and he.codigo_esteticista = e.cedula and dl.codigo = h.codigo_dia_laborable and dl.descripcion = '"+diaSemana+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Bloque> arr_bloques = new ArrayList<Bloque>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Bloque(resultSet.getString("codigo_bloque"),null,null,null,null));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		Messagebox.show(tiraSQL);
		return arr_bloques;
	
	}

	public List<Bloque> VerficarEsteticistaDiaEspecifico(String fecha, String esteticista) {
		String tiraSQL = "SELECT codigo_bloque FROM tb_diponibilidad_esteticista de where fecha = '"+fecha+"' AND codigo_esteticista = (SELECT cedula from tb_esteticista where nombre||' '||apellido = '"+esteticista+"') AND Status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Bloque> arr_bloques = new ArrayList<Bloque>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Bloque(resultSet.getString("codigo_bloque"),null,null,null,null));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		Messagebox.show(tiraSQL);
		return arr_bloques;
	
		
	}

	public List<Cubiculo> BuscarCubiculosSegunServicio(String servicio) //retorna los cobiculos segun el servicio seleccionado
	{
		
		String tiraSQL = "SELECT codigo_cubiculo FROM tb_cubiculo_servicio cs WHERE cs.codigo_servicio = (SELECT codigo FROM tb_servicio s WHERE s.titulo = '"+servicio+"')";
		
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cubiculo> cubiculosSegunServicio = new ArrayList<Cubiculo>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					cubiculosSegunServicio.add(new Cubiculo(resultSet.getString("codigo_cubiculo"),null,null));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		Messagebox.show(tiraSQL);
		return cubiculosSegunServicio;
		
	}

	public List<DisponibilidadCubiculo> BloquesDisponiblesDelCubiculo(String codigo_cubiculo, String fecha) //me trae codigo del bloque donde esta OCUPADO dado el cubiculo y la fecha
	{
		
		List<Bloque> TodosLosBloques = new ArrayList<Bloque>();
		List<DisponibilidadCubiculo> cubiculosSegunServicio = new ArrayList<DisponibilidadCubiculo>();
		String tiraSQL = "SELECT codigo_bloque FROM tb_disponibilidad_cubiculo dc WHERE dc.fecha = '"+fecha+"' AND dc.codigo_cubiculo = '"+codigo_cubiculo+"' AND dc.status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					cubiculosSegunServicio.add(new DisponibilidadCubiculo(null,codigo_cubiculo,null,resultSet.getString("codigo_bloque")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		Messagebox.show(tiraSQL);
		
		TodosLosBloques = CargarTodosLosBLoques(); //cargo todos los bloques registrados en el sistema
		
	
		
		for (int i = 0; i < TodosLosBloques.size(); i++) //Todos menos los que esten ocupados para el dia en especifico
		{
			int cont=0;
			
			for (int j = 0; j < cubiculosSegunServicio.size(); j++)
			{
				if(TodosLosBloques.get(i).getCodigo().equals(cubiculosSegunServicio.get(j).getCodigo_bloque()))
				{
					cont++;
					
				
				}
				
			}
			
			if(cont==0)
			{
				resultante.add(new DisponibilidadCubiculo(null,codigo_cubiculo,null,TodosLosBloques.get(i).getCodigo()));
			}
			
		}
		
		
		Messagebox.show("Resultante es : "+String.valueOf(resultante.size()));
		
		
		
		
		
		return resultante;
		
		
		
	

		
		
		
	}
	
	
	private List<Bloque>  CargarTodosLosBLoques() 
	{
		
		String tiraSQL = "SELECT codigo from tb_bloque";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Bloque> arr_bloques = new ArrayList<Bloque>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Bloque(resultSet.getString("codigo"),null,null,null,null));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		//Messagebox.show(tiraSQL);
		return arr_bloques;
		
		
	}
	
	
	
	//---------------------------------------------------------------------------------------------
	
	public List<DisponibilidadEsteticista> VerficarDiaEspecifico(String fecha) {
		String tiraSQL = "SELECT codigo_bloque codigo_bloque, dl.descripcion , nombre||' '||apellido nombre, cedula cedula from tb_horario h, tb_esteticista e, tb_horario_esteticista he, tb_dia_laborable dl where h.codigo = he.codigo_horario and he.codigo_esteticista = e.cedula and dl.codigo = h.codigo_dia_laborable and dl.descripcion = '"+fecha+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<DisponibilidadEsteticista> arr_bloques = new ArrayList<DisponibilidadEsteticista>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new DisponibilidadEsteticista(null,resultSet.getString("cedula"),null,resultSet.getString("codigo_bloque")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		Messagebox.show(tiraSQL);
		return arr_bloques;
	
		
	}



	public List<DisponibilidadEsteticista> VerificarDisponibilidadBloquesDeTodosLosEsteticistas( DisponibilidadEsteticista disponibilidadEsteticista, String dia) 
	{
	
		String tiraSQL = "SELECT codigo_bloque, codigo_esteticista FROM tb_diponibilidad_esteticista de where fecha = '"+dia+"' AND codigo_esteticista = '"+disponibilidadEsteticista.getCodigo_esteticista()+"' AND Status = 'Activo' AND codigo_bloque= '"+disponibilidadEsteticista.getCodigo_bloque()+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
	
		
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloquesOcupados.add(new DisponibilidadEsteticista(null,disponibilidadEsteticista.getCodigo_esteticista(),null,disponibilidadEsteticista.getCodigo_bloque()));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		
		Messagebox.show(tiraSQL);

		return arr_bloquesOcupados;

	}



	/*public List<DisponibilidadCubiculo> BloquesDisponiblesDeEsteticistasEnUnDia(String fecha, String esteticista) {
		List<Bloque> TodosLosBloques = new ArrayList<Bloque>();
		List<DisponibilidadCubiculo> cubiculosSegunServicio = new ArrayList<DisponibilidadCubiculo>();
		String tiraSQL = "SELECT codigo_bloque FROM tb_disponibilidad_esteticista de WHERE de.fecha = '"+fecha+"' AND de.codigo_esteticista = '"+esteticista+"' AND dc.status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					cubiculosSegunServicio.add(new DisponibilidadCubiculo(null,codigo_cubiculo,null,resultSet.getString("codigo_bloque")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Messagebox.show(tiraSQL);
			e.printStackTrace();
		}
		Messagebox.show("Todos los que estan ocupados:"+tiraSQL);
		
		TodosLosBloques = CargarTodosLosBLoques(); //cargo todos los bloques registrados en el sistema
		
	
		
		for (int i = 0; i < TodosLosBloques.size(); i++) //Todos menos los que esten ocupados para el dia en especifico
		{
			int cont=0;
			
			for (int j = 0; j < cubiculosSegunServicio.size(); j++)
			{
				if(TodosLosBloques.get(i).getCodigo().equals(cubiculosSegunServicio.get(j).getCodigo_bloque()))
				{
					cont++;
					
				
				}
				
			}
			
			if(cont==0)
			{
				resultante.add(new DisponibilidadCubiculo(null,codigo_cubiculo,null,TodosLosBloques.get(i).getCodigo()));
			}
			
		}
		
		
		Messagebox.show("Resultante es : "+String.valueOf(resultante.size()));
		
		
		
		
		
		return resultante;
		
		
		
	}*/
	
	
	
	
	
	


	
	

	
}





	

	
	
	
	
