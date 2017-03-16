package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import modelo.Antecedente;
import modelo.AntecedenteCliente;
import modelo.Habito;
import modelo.HabitoCliente;
import modelo.Maestrico;
import bean.Conexion;

public class AntecedenteDAO extends ConexionDAO{
	
	public List<Antecedente> listaAntecedentes() { //para listar en el grid la informacion
		//int i=0;
		String tiraSQL = "SELECT * FROM tb_antecedente WHERE status = 'false' ORDER BY codigo";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		ArrayList<Antecedente> arr_antecedentes = new ArrayList<Antecedente>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
				//	i++;
					
					arr_antecedentes.add(new Antecedente(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getBoolean("status")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_antecedentes;
	}
	
	
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_Habito_cliente";
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
	
	
public void agregarHabitoACliente(HabitoCliente habito) {
		
		String tiraSQL= "INSERT INTO tb_Habito_cliente (codigo_habito,codigo_cliente,codigo,status) "
				+ "VALUES ('"+habito.getCodigo_habito()+"'"+",'"+habito.getCodigo_cliente()+"','"+habito.getCodigo()+"','"+habito.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
		
		
	}

public ArrayList<HabitoCliente> buscarHabitosXCliente(String cedula){
	ArrayList<HabitoCliente> habitos = new ArrayList<HabitoCliente>();
	String tiraSQL = "select codigo_habito,codigo_cliente, codigo, status from tb_habito_cliente where codigo_cliente = '"+cedula+"'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	try {
		while(resultSet.next()){
			String codigo_habito = resultSet.getString("codigo_habito");
			String codigo_cliente = resultSet.getString("codigo_cliente");
			String codigo = resultSet.getString("codigo");
			Boolean status = resultSet.getBoolean("status");
			
			HabitoCliente habito = new HabitoCliente(codigo_habito, codigo_cliente , codigo, status);
			habitos.add(habito);
		}
		resultSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return habitos;
			

}

}
