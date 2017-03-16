package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

import bean.Conexion;

public class RespuestaFormularioDAO extends ConexionDAO{
	
public void registraRespuestaFormulario(String codigo,String pregunta,String respuesta,String codigo_cliente){
		
		String tiraSQL= "INSERT INTO  tb_respuesta_formulario_web (codigo,pregunta,respuesta,codigo_cliente) "
				+ "VALUES ('"+codigo+"'"+",'"+ pregunta+"','"+ respuesta+"','"+ codigo_cliente+"')";
		Conexion.ejecutar(tiraSQL);
		
	}
public String TotalRegistros( ){
	String tiraSQL = "SELECT * FROM tb_respuesta_formulario_web";
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

}
