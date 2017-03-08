package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import modelo.HabitoCliente;
import modelo.Maestrico;
import bean.Conexion;

public class HabitoDAO extends ConexionDAO{
	
	public ArrayList<Maestrico> listaHabito() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_habito where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		ArrayList<Maestrico> arr_habito = new ArrayList<Maestrico>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					i++;
					arr_habito.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_habito;
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

}
