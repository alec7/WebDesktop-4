package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import bean.Conexion;
import modelo.DetalleAvance;
import modelo.IndicadorServicio;
import modelo.Maestrico;
import modelo.Sesion;
import modelo.Avance;


public class SesionDAO extends ConexionDAO{
	
	
	public ArrayList<Sesion> ObtenerSesion(String cedula){
		ArrayList<Sesion> servicios = new ArrayList<Sesion>();
		String tiraSQL = " SELECT tb_detalle_sesion.ejecucion_servicio, tb_detalle_sesion.codigo, select1.titulo, select1.nombre,select1.codigoserv, select1.cedula "
				+ " FROM tb_detalle_sesion,(SELECT tb_servicio.titulo, tb_servicio.codigo as codigoserv, tb_cliente.nombre,tb_cita.codigo, tb_cliente.cedula   "
				+ " FROM tb_cliente,tb_solicitud,tb_detalle_solicitud,tb_cita,tb_servicio "
				+ " WHERE tb_cliente.cedula = tb_solicitud.codigo_cliente "
				+ " AND   tb_solicitud.codigo = tb_detalle_solicitud.codigo_solicitud "
				+ " AND   tb_detalle_solicitud.codigo = tb_cita.codigo_detalle_solicitud "
				+ " AND   tb_cita.codigo_servicio = tb_servicio.codigo) as select1 "
				+ " WHERE tb_detalle_sesion.codigo_cita = select1.codigo "
		        + " AND    select1.cedula='"+cedula+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String codigoserv = resultSet.getString("codigoserv");
				String codigo = resultSet.getString("codigo");
				String descripcion = resultSet.getString("titulo");
				Boolean ejecucion = resultSet.getBoolean("ejecucion_servicio");
				Sesion sesion = new Sesion(codigo,descripcion, ejecucion, codigoserv);
				servicios.add(sesion);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	
	
	public ArrayList<IndicadorServicio> ObtenerIndicadores(String codigoServicio){
		ArrayList<IndicadorServicio> indicadores = new ArrayList<IndicadorServicio>();
		String tiraSQL = "select a.codigo, a.descripcion from tb_avance a, tb_avance_servicio b where a.codigo=b.codigo_maestro and codigo_servicio='"+codigoServicio+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String codigo = resultSet.getString("codigo");
				String descripcion = resultSet.getString("descripcion");
				IndicadorServicio indicador = new IndicadorServicio(codigo, descripcion);
				indicadores.add(indicador);
				
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indicadores;
		
	}
	
	public void actualizarSesion(String codigo)
	{
		String tiraSQL= "UPDATE tb_detalle_sesion SET ejecucion_servicio='true' WHERE codigo='"+codigo+"' ";
		Conexion.ejecutar(tiraSQL);
	}
	
	
public void agregarDetalleAvance(DetalleAvance detalle) {
		
		String tiraSQL= "INSERT INTO tb_detalle_avance (codigo,codigo_detalle_sesion,codigo_avance, numero_sesion,fecha,valor) "
				+ "VALUES ('"+detalle.getCodigo()+"','"+detalle.getCodigo_detalle_sesion()+"','"+detalle.getCodigo_avance()+"','"+detalle.getNumero_sesion()+"','"+detalle.getFecha()+"','"+detalle.getValor()+"')";
		Conexion.ejecutar(tiraSQL);
	}

public int NumeroSesion(String cedula, String codigoServ){
	String tiraSQL = "SELECT  select1.cedula, select1.codigoserv "
		+ " FROM tb_detalle_sesion,(SELECT tb_servicio.titulo, tb_servicio.codigo as codigoserv, tb_cliente.cedula ,tb_cliente.nombre,tb_cita.codigo "	
		+ " FROM tb_cliente,tb_solicitud,tb_detalle_solicitud,tb_cita,tb_servicio "
		+ " WHERE tb_cliente.cedula = tb_solicitud.codigo_cliente "
		+ " AND   tb_solicitud.codigo = tb_detalle_solicitud.codigo_solicitud "
		+ " AND   tb_detalle_solicitud.codigo = tb_cita.codigo_detalle_solicitud "
		+ " AND   tb_cita.codigo_servicio = tb_servicio.codigo) as select1 "
		+ " WHERE tb_detalle_sesion.codigo_cita = select1.codigo "
		+ " AND   select1.cedula='"+cedula+"'"
		+ " AND   select1.codigoserv='"+codigoServ+"'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	int numero=0;
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				numero++;
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
	} 
    return numero;
}


public ArrayList<Avance> filtrarIndicadoresXservicio(String codigoServicio)
{
	ArrayList<Avance> avances = new ArrayList<Avance>();
	String tiraSQL = "select a.codigo, a.descripcion, a.status, a.unidad_medida from tb_avance a, tb_avance_servicio b, tb_servicio c"
    + " where a.codigo = b.codigo_maestro and b.codigo_servicio = c.codigo and c.codigo ='"+codigoServicio+"'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	try {
		while(resultSet.next()){
			String codigo = resultSet.getString("codigo");
			String descripcion = resultSet.getString("descripcion");
			String status = resultSet.getString("status");
			String unidad_medida = resultSet.getString("unidad_medida");
			Avance avance = new Avance(codigo, descripcion, status, unidad_medida);
			avances.add(avance);
			
		}
		resultSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return avances;
	
}


public ArrayList<DetalleAvance> filtrarAvancesXCliente(String cedula ,String codigoAvance, String codigoServicio)
{
	ArrayList<DetalleAvance> avances = new ArrayList<DetalleAvance>();
	String tiraSQL = "SELECT tb_detalle_sesion.ejecucion_servicio, select1.titulo, select1.nombre,select1.codigoserv,tb_detalle_avance.valor,tb_avance.codigo as codigoavance, tb_avance.descripcion,tb_detalle_avance.fecha,tb_detalle_avance.numero_sesion, select1.cedula"
			+ " FROM tb_detalle_sesion,tb_detalle_avance,tb_avance,(SELECT tb_servicio.titulo, tb_servicio.codigo as codigoserv,tb_cliente.cedula, tb_cliente.nombre,tb_cita.codigo "
			+ " FROM tb_cliente,tb_solicitud,tb_detalle_solicitud,tb_cita,tb_servicio "
	        + " WHERE tb_cliente.cedula = tb_solicitud.codigo_cliente "
			+ " AND   tb_solicitud.codigo = tb_detalle_solicitud.codigo_solicitud "
	        + " AND   tb_detalle_solicitud.codigo = tb_cita.codigo_detalle_solicitud "
			+ " AND   tb_cita.codigo_servicio = tb_servicio.codigo) as select1 "
	        + " WHERE select1.codigo = tb_detalle_sesion.codigo_cita "
			+ " AND tb_detalle_sesion.codigo = tb_detalle_avance.codigo_detalle_sesion "
	        + " AND tb_detalle_avance.codigo_avance = tb_avance.codigo and tb_avance.codigo ='"+codigoAvance+"' and select1.cedula ='"+cedula+"' and select1.codigoserv='"+codigoServicio+"' "
			+ " AND codigoserv <>'00001'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	
	
	try {
		while(resultSet.next()){
			int numeroSesion = resultSet.getInt("numero_sesion");
			Date fecha = resultSet.getDate("fecha");
			String valor  = resultSet.getString("valor");
			
			
			DetalleAvance detalle = new DetalleAvance(numeroSesion,  fecha, valor);
			avances.add(detalle);
			
		}
		resultSet.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return avances;
	
}


public String TotalRegistros(){
	String tiraSQL = "SELECT * FROM  tb_detalle_avance";
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

public String buscarCorreo(String usuario) {
    String contrasenna="";
    String tiraSQL = "select usuario from tb_usuario WHERE usuario = '"+usuario+"'";
    ResultSet resultSet = Conexion.consultar(tiraSQL);
    try{
        while(resultSet.next()){
            usuario = resultSet.getString("usuario");
        }
        resultSet.close();
    }catch (SQLException e) {
        e.printStackTrace();
    }    
    
    return usuario;
}



public void recuperarContrasennaUsuario( String contrasenna,String usuario) {
    String tiraSQL = "UPDATE tb_usuario SET contrasenna = '"+contrasenna+"' WHERE usuario = '"+usuario+"'";
    Conexion.ejecutar(tiraSQL);
        
}



}
