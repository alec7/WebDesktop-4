package modeloDAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Slider;

public class SliderDAO extends ConexionDAO{

public void agregarImagen(Slider slider) {
		
		String tiraSQL= "INSERT INTO tb_slider (codigo,titulo,leer_mas,status,subtitulo,imagen) "
				+ "VALUES ('"+slider.getCodigo()+"'"+",'"+ slider.getTitulo()+"','"+slider.getLeer_mas()+"','"+slider.getStatus()+"','"+slider.getSubtitulo()+"','"+slider.getImagen()+"')";
		Conexion.ejecutar(tiraSQL);
	}




public List<Slider> obtenerImagenSlider()
{
	List<Slider> sliders = new ArrayList<Slider>();
	String tiraSQL = "SELECT * from tb_slider where status='Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);	
	try {
		while (resultSet.next()) {
			
			String codigo = resultSet.getString("codigo");
			String titulo = resultSet.getString("titulo");
			String leer_mas = resultSet.getString("leer_mas");
		    String status =  resultSet.getString("status");
		    String subtitulo = resultSet.getString("subtitulo");
		    String imagen = resultSet.getString("imagen");
		    Slider slider = new Slider(codigo, titulo, leer_mas, status, subtitulo, imagen);
			sliders.add(slider);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sliders;


 }

public String TotalRegistros(){
	String tiraSQL = "SELECT * FROM  tb_slider";
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


public void eliminarSlider(String codigo){
	
	String tiraSQL= "UPDATE tb_slider SET  status= 'Inactivo' WHERE codigo='"+codigo+"' ";
	Conexion.ejecutar(tiraSQL);

}


public void guardar()
{
	/*File file = new File("myimage.gif");
	FileInputStream fis = new FileInputStream(file);
    PreparedStatement ps = conn.prepareStatement("INSERT INTO images VALUES (?, ?)");
	ps.setString(1, file.getName());
	ps.setBinaryStream(2, fis, (int)file.length());
	ps.executeUpdate();
	ps.close();
	fis.close();*/
}
	
	
}
