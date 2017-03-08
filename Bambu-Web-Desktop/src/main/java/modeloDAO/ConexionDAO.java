package modeloDAO;

import bean.Conexion;

public class ConexionDAO {
	
	public ConexionDAO() {
		super();	

		Conexion.establecerPropiedadesConexion("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/", "BambuWebservice", "postgres", "12345");

	}	

}
