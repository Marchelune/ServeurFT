package donnees;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Exercice {
	@Id Long id;
	private Date date;
	private String type;
	private long duree;
	private int repetitions;
	private int coins;
	
	public Long getId() {
		return id;
	}

	public Exercice(){}
	
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
