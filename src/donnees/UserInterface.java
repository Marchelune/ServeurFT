package donnees;


public interface UserInterface {
	public String getNom();
	public void setNom(String nom);
	public String getPrenom();
	public void setPrenom(String prenom);
	public String getId();
	public int getCoins();
	public void setCoins(int coins);
	public void addCoins(int coins);
	public void addFriend(String id);
	public void deleteFriend(String id);
}
