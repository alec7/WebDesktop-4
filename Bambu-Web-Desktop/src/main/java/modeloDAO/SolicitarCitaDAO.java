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

import modelo.Cita;
import modelo.Maestro;
import modelo.Maestro_servicio;
import modelo.Servicio;
import modelo.Usuario;

public class SolicitarCitaDAO extends ConexionDAO
{
	public List<Cita> resultante = new ArrayList<Cita>();
	public List<Cita> arr_bloquesOcupados = new ArrayList<Cita>();
	
	public SolicitarCitaDAO() 
	{		
		super();
		
	
	
	
	}



	public  List<Cita> VerificarEsteticistaDia(String esteticista, String diaSemana)  //select de codigo de bloque disponible del esteticista
	{
		String tiraSQL = "select  dl.descripcion , codigo_bloque, nombre||' '||apellido  nombre, cedula cedula from tb_horario h, tb_esteticista e, tb_horario_esteticista he, tb_dia_laborable dl where e.cedula = ( SELECT cedula from tb_esteticista where nombre||' '||apellido = '"+esteticista+"') and h.codigo = he.codigo_horario and he.codigo_esteticista = e.cedula and dl.codigo = h.codigo_dia_laborable and dl.descripcion = '"+diaSemana+"' and he.status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cita> arr_bloques = new ArrayList<Cita>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Cita(null,null,null,null,null,null,resultSet.getString("cedula"),null,resultSet.getString("codigo_bloque")));
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

	public List<Cita> VerficarEsteticistaDiaEspecifico(String fecha, String esteticista) {
		String tiraSQL = "SELECT codigo_bloque, codigo_esteticista FROM tb_diponibilidad_esteticista de where fecha = '"+fecha+"' AND codigo_esteticista = (SELECT cedula from tb_esteticista where nombre||' '||apellido = '"+esteticista+"') AND Status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cita> arr_bloques = new ArrayList<Cita>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Cita(null,null,null,null,null,null,resultSet.getString("codigo_esteticista"),null,resultSet.getString("codigo_bloque")));
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

	public List<Cita> BuscarCubiculosSegunServicio(String servicio) //retorna los cobiculos segun el servicio seleccionado
	{
		
		String tiraSQL = "SELECT codigo_cubiculo FROM tb_cubiculo_servicio cs WHERE cs.codigo_servicio = (SELECT codigo FROM tb_servicio s WHERE s.titulo = '"+servicio+"')";
		
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cita> cubiculosSegunServicio = new ArrayList<Cita>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					cubiculosSegunServicio.add(new Cita(null,null,resultSet.getString("codigo_cubiculo"),null,null,null,null,null,null));
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

	public List<Cita> BloquesDisponiblesDelCubiculo(String codigo_cubiculo, String fecha) //me trae codigo del bloque donde esta OCUPADO dado el cubiculo y la fecha
	{
		
		List<Cita> TodosLosBloques = new ArrayList<Cita>();
		List<Cita> cubiculosSegunServicio = new ArrayList<Cita>();
		String tiraSQL = "SELECT codigo_bloque, codigo_cubiculo FROM tb_disponibilidad_cubiculo dc WHERE dc.fecha = '"+fecha+"' AND dc.codigo_cubiculo = '"+codigo_cubiculo+"' AND dc.status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					cubiculosSegunServicio.add(new Cita(null,null,codigo_cubiculo,null,null,null,null,null,resultSet.getString("codigo_bloque")));
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
				if(TodosLosBloques.get(i).getCodigo_bloque().equals(cubiculosSegunServicio.get(j).getCodigo_bloque()))
				{
					cont++;
					
				
				}
				
			}
			
			if(cont==0)
			{
				resultante.add(new Cita(null, null, codigo_cubiculo, null, null, null, null, null, TodosLosBloques.get(i).getCodigo_bloque()));
			}
			
		}
		
		
		Messagebox.show("Resultante es : "+String.valueOf(resultante.size()));
		
		
		
		
		
		return resultante;
		
		
		
	

		
		
		
	}
	
	
	private List<Cita>  CargarTodosLosBLoques() 
	{
		
		String tiraSQL = "SELECT codigo from tb_bloque";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cita> arr_bloques = new ArrayList<Cita>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Cita(null,null,null,null,null,null,null,null,resultSet.getString("codigo")));
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
	
	
	
	//-----------------------------------------------------------------------------------------------------------------
	
	public List<Cita> VerficarDiaEspecifico(String fecha) {
		String tiraSQL = "SELECT codigo_bloque codigo_bloque, dl.descripcion , nombre||' '||apellido nombre, cedula cedula from tb_horario h, tb_esteticista e, tb_horario_esteticista he, tb_dia_laborable dl where h.codigo = he.codigo_horario and he.codigo_esteticista = e.cedula and dl.codigo = h.codigo_dia_laborable and dl.descripcion = '"+fecha+"' and he.status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cita> arr_bloques = new ArrayList<Cita>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloques.add(new Cita(null, null, null, null, null, null, resultSet.getString("cedula"), null, resultSet.getString("codigo_bloque")));
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



	public List<Cita> VerificarDisponibilidadBloquesDeTodosLosEsteticistas( Cita Cita, String dia) 
	{
	
		String tiraSQL = "SELECT codigo_bloque, codigo_esteticista FROM tb_diponibilidad_esteticista de where fecha = '"+dia+"' AND codigo_esteticista = '"+Cita.getCodigo_esteticista()+"' AND Status = 'Activo' AND codigo_bloque= '"+Cita.getCodigo_bloque()+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
	
		
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloquesOcupados.add(new Cita(null, null,null, null, null, null, resultSet.getString("codigo_esteticista"), null, resultSet.getString("codigo_bloque")));
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
	
	public String TotalRegistros(String tabla ){
		String tiraSQL = "SELECT * FROM "+tabla;
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		int numero=1;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					numero++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		} 
		Formatter fmt = new Formatter();
		fmt.format("%05d", numero);
		return String.valueOf(fmt);
	}



	public void InsetarCita(Cita ci) 
	{
		
	

		
		String tiraSQL1= "INSERT INTO tb_cita (codigo, codigo_detalle_solicitud, codigo_cubiculo, status, codigo_motivo_cancelacion, fecha, codigo_esteticista, codigo_servicio, codigo_bloque) VALUES ('"+ci.getCodigo()+"','"+ci.getCodigo_detalle_solicitud()+"','"+ci.getCodigo_cubiculo()+"','"+ci.getStatus()+"','"+ci.getCodigo_motivo_cancelacion()+"','"+ci.getFecha()+"','"+ci.getCodigo_esteticista()+"','"+ci.getCodigo_servicio()+"','"+ci.getCodigo_bloque()+"')";

		Conexion.ejecutar(tiraSQL1);
		
		Messagebox.show(tiraSQL1);
		
		String codigo1= TotalRegistros("tb_diponibilidad_esteticista");
		String tiraSQL2= "INSERT INTO tb_diponibilidad_esteticista (codigo, fecha, codigo_esteticista,status, codigo_bloque) VALUES ('"+codigo1+"','"+ci.getFecha()+"','"+ci.getCodigo_esteticista()+"','"+ci.getStatus()+"','"+ci.getCodigo_bloque()+"')";

		Conexion.ejecutar(tiraSQL2);  
		
		Messagebox.show(tiraSQL2);
		
		
		String codigo2= TotalRegistros("tb_disponibilidad_cubiculo");
		String tiraSQL3= "INSERT INTO tb_disponibilidad_cubiculo (codigo, fecha, codigo_cubiculo,status, codigo_bloque) VALUES ('"+codigo2+"','"+ci.getFecha()+"','"+ci.getCodigo_cubiculo()+"','"+ci.getStatus()+"','"+ci.getCodigo_bloque()+"')";
		Conexion.ejecutar(tiraSQL3);
		
		Messagebox.show(tiraSQL3);
		
		
		

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





	

	
	
	
	
