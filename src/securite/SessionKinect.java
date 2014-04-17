package securite;

import java.util.Calendar;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

import donnees.User;

@EntitySubclass
public class SessionKinect extends Session { //type de session spécifique aux stations kinect
	
	private String idMachine; //identifiant de la station kinect ayant demandé l'ouverture de la session (permettra un contrôle de sécurité lors de l'ajout des sportcoins)
	private int nmbExercices; //nombre d'exercices réalisés sur une session (permettra un contrôle de sécurité lors de l'ajout des sportcoins)
	private Date dateCreation; //date de creation de la session Kinect
	
	public String getIdMachine() {
		return idMachine;
	}


	public Date getDateExp() { //retourne la date d'expiration de la session 
		Calendar cal = Calendar.getInstance();
        cal.setTime(dateCreation);
        cal.add(Calendar.DATE, 1); //les sessions spécifiques aux stations kinect ne peuvent durer plus d'une journée
        return cal.getTime();
	}

	public SessionKinect(){} //Constructeur vide nécessaire à la gestion de la base de données du Datastore
	
	public SessionKinect(Key<User> userKey, String idMachine) {
		super( userKey);
		this.idMachine = idMachine;
		dateCreation = new Date();
		nmbExercices = 0;
	}


	public int getNmbExercices() {
		return nmbExercices;
	}


	public void incrementNmbExercices() {
		nmbExercices += nmbExercices;
	}
	
}
