package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;

import bean.Conexion;
import modelo.Horario;
import modelo.Horario_Esteticista;
import modelo.Maestrico;
import modelo.Organizacion;

public class HorarioDAO extends ConexionDAO {

	
public void registrarHorario(Horario h){
		
		String tiraSQL= "INSERT INTO  tb_horario (codigo,codigo_dia_laborable,codigo_bloque,status) "
				+ "VALUES ('"+h.getCodigo()+"'"+",'"+ h.getCodigo_dia_laborable()+"','"+h.getCodigo_bloque()+"','"+ h.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
		
	}

public String TotalRegistros(){
	String tiraSQL = "SELECT * FROM tb_horario ";
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
public String TotalRegistrosHorarioEsteticista(){
	String tiraSQL = "SELECT * FROM tb_horario_esteticista ";
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
public String codigoAgenda(String codigo_dia){
	String tiraSQL = "SELECT * FROM tb_agenda WHERE codigo_dia='"+codigo_dia+"' ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	String codigo="";
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				codigo= resultSet.getString("codigo");
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
	} 
	return codigo;
}
public boolean buscarHorarioEst(String codigo_horario){
	String tiraSQL = "SELECT * FROM tb_horario_esteticista WHERE codigo_horario='"+codigo_horario+"'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	boolean encontro=false;
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				encontro=true;
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
	} 
	return encontro;
}
public Horario buscarHorario(String codigo_horario){
	String tiraSQL = "SELECT * FROM tb_horario WHERE codigo='"+codigo_horario+"'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	Horario h =null;
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				h = new Horario(resultSet.getString("codigo"), resultSet.getString("codigo_dia_laborable"), resultSet.getString("codigo_bloque"), resultSet.getString("status"));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
	} 
	return h;
}
public ArrayList<Horario_Esteticista> listaHorariosEsteticista(String cedulaEst) { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_horario_esteticista where status = 'Activo' and codigo_esteticista='"+cedulaEst+"' ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	ArrayList<Horario_Esteticista> arr_codigo_horario = new ArrayList<Horario_Esteticista>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				arr_codigo_horario.add(new Horario_Esteticista(resultSet.getString("codigo"), resultSet.getString("codigo_horario"), resultSet.getString("codigo_esteticista"), resultSet.getString("status"), resultSet.getString("codigo_agenda")));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_codigo_horario;
}
public ArrayList<Horario> listaHorarios() { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_horario where status = 'Activo'  ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	ArrayList<Horario> arr_codigo_horario = new ArrayList<Horario>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				arr_codigo_horario.add(new Horario(resultSet.getString("codigo"), resultSet.getString("codigo_dia_laborable"), resultSet.getString("codigo_bloque"), resultSet.getString("status")));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_codigo_horario;
}
public Horario horariosPorEstetictas(Horario h) { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_horario where codigo='"+h.getCodigo()+"' order by codigo ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	Horario ho=null;
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				ho =new Horario(resultSet.getString("codigo"), resultSet.getString("codigo_dia_laborable"), resultSet.getString("codigo_bloque"), resultSet.getString("status"));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ho;
}


public Horario validarHorario(String dia, String codBloque) { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_horario where codigo_dia_laborable='"+dia+"' and codigo_bloque='"+codBloque+"'  ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	Horario ho=null;
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				ho =new Horario(resultSet.getString("codigo"), resultSet.getString("codigo_dia_laborable"), resultSet.getString("codigo_bloque"), resultSet.getString("status"));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ho;
}
public ArrayList<Horario> listaHorario(String codigo_dia) { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_horario where status = 'Activo' and dia_laborable='"+codigo_dia+"'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	ArrayList<Horario> arr_codigo_horario = new ArrayList<Horario>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				arr_codigo_horario.add(new Horario(resultSet.getString("codigo"), resultSet.getString("codigo_dia_laborable"), resultSet.getString("codigo_bloque"), resultSet.getString("status")));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_codigo_horario;
}
public void actualizarHorario(Horario h) {
	String tiraSQL = "UPDATE tb_horario SET status = 'Inactivo' WHERE codigo = '"+h.getCodigo()+"'";
	Conexion.ejecutar(tiraSQL);
	
	
}
public void actualizarHorarioEsteticista(Horario_Esteticista h) {
	String tiraSQL = "UPDATE tb_horario_esteticista SET status = 'Inactivo' WHERE codigo = '"+h.getCodigo()+"'";
	Conexion.ejecutar(tiraSQL);
	
	
}

}
