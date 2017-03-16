package modeloDAO;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.tomcat.util.net.URL;



public class EmailHTMLDao {
	
	HtmlEmail email;
	
	
	public  EmailHTMLDao() {
		email = new HtmlEmail();
		configuracion();
	}
	
	private void configuracion(){
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setDebug(true);
		email.setAuthentication("richard0227@gmail.com", "vcdytzpoviintvzo");
		email.setSSLOnConnect(true);
	}
	
	public void enviarEmail(String from, String suject, String to, String ced, String nom, String ape){	
	try{
	email.setFrom(from);
	email.setSubject(suject);
	email.addTo(to);
	email.addReplyTo(from);
	email.setHtmlMsg("<html><body><img src='http://s2.subirimagenes.com/imagen/previo/thump_9706634headercorreo.png'><div> <font face='Candara' size='4'>Estimad@ "+nom+" "+ape+", le informamos que su registro<br>se realizo con éxito. <br><br>Usuario: "+to+"<br>Contraseña: "+ced+"<br><br><br> Inicie sesion haciendo click  <a href='http://localhost:8084/Bambu-Web-Desktop/WebContent/login.zul' >AQUI</a></font></div><img src='http://s2.subirimagenes.com/imagen/previo/thump_9706636correofooter.png'></body></html>");
	//email.setHtmlMsg("<html><body><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' valign='top' bgcolor='#DDDDDD' style='background-color:#EEEEEE;'><br><br><table width='600' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' valign='top' style='padding-left:13px; padding-right:13px; background-color:#ffffff;background-color: #fff;padding: 19px;margin-bottom: 20px;-webkit-box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);border-radius: 2px;border: 0;'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td><table width='84' border='0' cellspacing='0' cellpadding='0'><tr><td height='80' align='center' valign='middle' bgcolor='#009688' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#ffffff;'><div style='font-size:15px;color: white;'><b><div>PRIME</div></b></div></td></tr></table></td></tr><tr><td align='center' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:48px;'><i>titulo</i></td></tr><tr><td align='center' valign='middle' style='padding-top:7px;'><table width='240' border='0' cellspacing='0' cellpadding='0'><tr><td height='31' align='center' valign='middle' bgcolor='#009688' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:19px; color: white;'><div style='color:white;'>22 de febrero de 2017</div></td></tr></table></td></tr><tr>//<td align='center' valign='middle' style='padding-top:15px;'><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/2011_Hyundai_Sonata_Limited_4_--_02-13-2010.jpg/1200px-2011_Hyundai_Sonata_Limited_4_--_02-13-2010.jpg' width='573' height='243' style='display:block;'></td></tr><tr><td align='center' valign='middle' style='padding-bottom:15px; padding-top:15px;'><img src='http://oi67.tinypic.com/zjfndd.jpg' width='573' height='28'></td></tr><tr><td align='center' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#000000; font-size:24px; padding-bottom:5px;'><i>subTitulo</i><tr><td align='left' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#000000; font-size:15px;'>mensaje</td><tr><td align='center' valign='middle' style='padding-bottom:15px; padding-top:15px;'><img src='http://oi67.tinypic.com/zjfndd.jpg' width='573' height='28'></td></tr><tr><td align='left' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:12px; color:#000000;'><div style='font-size:15px;'><b>Contactanos</b></div><div><br>consectetur adipiscing elit. Vestibulum magna enim, volutpat nec imperdiet id, tempor venenatis iaculis. Aenean hendrerit<br><br></div><div><table width='120' border='0' cellspacing='0' cellpadding='0'><tr><td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/829750285618683905/tSS3tIit_400x400.jpg' width='24' height='24'></td><td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/3513354941/24aaffa670e634a7da9a087bfa83abe6_400x400.png' width='24' height='24'></td><td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/786681705981673472/T5OKNZ1-_400x400.jpg' width='24' height='24'></td></tr></table></div><div><br>Company Address<br>123 James Street,   <br>Suite100,<br>Long Beach CA, 90000<br>(000) 123  4567 <br><br><br></div></td></tr></table></td></tr></table><br><br></td></tr><tr><td align='center' valign='top'>&nbsp;</td></tr></table></body></html>");
	email.setTextMsg("Your email client does not support HTML messages");
	email.send();
	}catch (EmailException ex) {
		Logger.getLogger(EmailHTMLDao.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	}
	
	public void recuperarContrasenna(String from, String suject, String to, String ced){	
		try{
		email.setFrom(from);
		email.setSubject(suject);
		email.addTo(to);
		email.addReplyTo(from);
		email.setHtmlMsg("<html><body><img src='http://s2.subirimagenes.com/imagen/previo/thump_9706634headercorreo.png'><br><br><div> <font face='Candara' size='4'>Estimado Cliente, le informamos que su cambio de Contraseña<br>se realizo con éxito. <br><br>Nueva Contraseña: "+ced+"</font></div><br><br> <img src='http://s2.subirimagenes.com/imagen/previo/thump_9706636correofooter.png'></body></html>");
		//email.setHtmlMsg("<html><body><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' valign='top' bgcolor='#DDDDDD' style='background-color:#EEEEEE;'><br><br><table width='600' border='0' cellspacing='0' cellpadding='0'><tr><td align='center' valign='top' style='padding-left:13px; padding-right:13px; background-color:#ffffff;background-color: #fff;padding: 19px;margin-bottom: 20px;-webkit-box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);border-radius: 2px;border: 0;'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td><table width='84' border='0' cellspacing='0' cellpadding='0'><tr><td height='80' align='center' valign='middle' bgcolor='#009688' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#ffffff;'><div style='font-size:15px;color: white;'><b><div>PRIME</div></b></div></td></tr></table></td></tr><tr><td align='center' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:48px;'><i>titulo</i></td></tr><tr><td align='center' valign='middle' style='padding-top:7px;'><table width='240' border='0' cellspacing='0' cellpadding='0'><tr><td height='31' align='center' valign='middle' bgcolor='#009688' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:19px; color: white;'><div style='color:white;'>22 de febrero de 2017</div></td></tr></table></td></tr><tr>//<td align='center' valign='middle' style='padding-top:15px;'><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/2011_Hyundai_Sonata_Limited_4_--_02-13-2010.jpg/1200px-2011_Hyundai_Sonata_Limited_4_--_02-13-2010.jpg' width='573' height='243' style='display:block;'></td></tr><tr><td align='center' valign='middle' style='padding-bottom:15px; padding-top:15px;'><img src='http://oi67.tinypic.com/zjfndd.jpg' width='573' height='28'></td></tr><tr><td align='center' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#000000; font-size:24px; padding-bottom:5px;'><i>subTitulo</i><tr><td align='left' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; color:#000000; font-size:15px;'>mensaje</td><tr><td align='center' valign='middle' style='padding-bottom:15px; padding-top:15px;'><img src='http://oi67.tinypic.com/zjfndd.jpg' width='573' height='28'></td></tr><tr><td align='left' valign='middle' style='font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif; font-size:12px; color:#000000;'><div style='font-size:15px;'><b>Contactanos</b></div><div><br>consectetur adipiscing elit. Vestibulum magna enim, volutpat nec imperdiet id, tempor venenatis iaculis. Aenean hendrerit<br><br></div><div><table width='120' border='0' cellspacing='0' cellpadding='0'><tr><td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/829750285618683905/tSS3tIit_400x400.jpg' width='24' height='24'></td><td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/3513354941/24aaffa670e634a7da9a087bfa83abe6_400x400.png' width='24' height='24'></td><td width='40' align='left' valign='middle'><img valign='middle' src='https://pbs.twimg.com/profile_images/786681705981673472/T5OKNZ1-_400x400.jpg' width='24' height='24'></td></tr></table></div><div><br>Company Address<br>123 James Street,   <br>Suite100,<br>Long Beach CA, 90000<br>(000) 123  4567 <br><br><br></div></td></tr></table></td></tr></table><br><br></td></tr><tr><td align='center' valign='top'>&nbsp;</td></tr></table></body></html>");
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();
		}catch (EmailException ex) {
			Logger.getLogger(EmailHTMLDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		}
	 

}
