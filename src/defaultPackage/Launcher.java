package defaultPackage;

import java.awt.Panel;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Launcher {
	public static void main(String arg[]) throws TwitterException {
		
		String moreLikesTweet=null;
		String moreLikesUser=null;
		int likes=0;
		
		//Inicialización
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("****************")
		  .setOAuthConsumerSecret("****************")
		  .setOAuthAccessToken("****************")
		  .setOAuthAccessTokenSecret("****************");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		
		//Realizar una consulta a través de twitter
//		Query query = new Query("colombia universidad distrital 40");
//		query.setCount(100);
//	    QueryResult result = twitter.search(query);
//	    
//	    for (Status status : result.getTweets()) {
//	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//	    }
		
		//Extraer el timeline de un usuario.
	    List<Status> statuses = twitter.getUserTimeline("dianadelosalpes", new Paging(1, 40));
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	        System.out.println("Likes: "+status.getRetweetCount());
	        if(status.getRetweetCount()>likes) {
	        	moreLikesTweet = status.getText();
				moreLikesUser= status.getUser().getName();
				likes=status.getFavoriteCount();
	        }
	    }

	    //Envío de un correo electrónico
//        Properties propiedad = new Properties();
//        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        propiedad.setProperty("mail.smtp.auth", "true");
//        
//        
//        Session sesion = Session.getDefaultInstance(propiedad);
//        String correoEnvia = "****************";
//        String contrasena = "****************";
//        String receptor = "****************";
//        String asunto = "Correo Desde Java";
//        String mensaje="Hola Cristian el tweet con más likes es: "+moreLikesUser+" : "+moreLikesTweet +" Likes: "+likes;
//        
//        MimeMessage mail = new MimeMessage(sesion);
//        try {
//            mail.setFrom(new InternetAddress (correoEnvia));
//            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
//            mail.setSubject(asunto);
//            mail.setText(mensaje);
//            
//            Transport transportar = sesion.getTransport("smtp");
//            transportar.connect(correoEnvia,contrasena);
//            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
//            transportar.close();
//            
//            JOptionPane.showMessageDialog(null, "Listo, revise su correo");
//            
//            
//        } catch (AddressException ex) {
//        } catch (MessagingException ex) {
//        }
	}
}
