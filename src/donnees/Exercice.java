package donnees;

import java.util.Date;

public class Exercice {

	private final Date date;
	private final String type;
	private final long duree;
	private final int repetitions;
	private final int coins;
	
	public Exercice(){
		this.date = null;
		this.type = null;
		this.duree = 0;
		this.repetitions = 0;
		this.coins = 0;
	}
	
	public Exercice(Date date, String type, long duree, int repetitions, int coins) {
		this.date = date;
		this.type = type;
		this.duree = duree;
		this.repetitions = repetitions;
		this.coins = coins;
	}


	public Date getDate() {
		return date;
	}
	public String getType() {
		return type;
	}
	public long getDuree() {
		return duree;
	}
	public int getRepetitions() {
		return repetitions;
	}
	public int getCoins() {
		return coins;
	}
	
	
}
