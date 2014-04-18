package securite;

public class GenerateurCleControle { //génère une chaîne de caractère qui est également calculée par le client et qui permet de vérifier la validité de la transaction

	public static String getCleControleur(SessionKinect session, int coins, int repetitions, long duree){
		
		String cle = Integer.toString(coins) + Integer.toString(repetitions) + Long.toString(duree) + Integer.toString(session.getNmbExercices());
		
		return Md5.encode(cle);
		
	}
}
