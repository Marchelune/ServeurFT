package catalogue;

import java.util.Date;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;




@Entity
@Cache
public class Purchase { //classe encapsulant un achat. 

	private Date date;
	private String item;
	@Id private Long id;
	private String user;
	private int amount;


	public Purchase(){};

	public Purchase(String user, Item item){
		date = new Date();
		this.item = item.getName();
		this.user = user;
		amount = item.getPrice();
	}

	public Date getDate() {
		return date;
	}

	public String getItem() {
		return item;
	}

	public Long getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public int getAmount() {
		return amount;
	}
	
	
	
	

}

