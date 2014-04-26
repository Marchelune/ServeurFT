package catalogue;

import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import donnees.User;


@Entity
@Cache
public class Purchase { //classe encapsulant un achat. Son constructeur fait toutes les modifications nécessaire sur les users et items

	private Date date;
	private String item;
	@Id private Long id;
	private String user;
	
	Purchase(){};
	
	Purchase(User user, Item item ){};
	
	
	
	
	
	
	
}
