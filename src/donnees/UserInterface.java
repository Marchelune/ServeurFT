package donnees;


public interface UserInterface {
	public String getNom();
	public void setNom(String nom);
	public String getPrenom();
	public void setPrenom(String prenom);
	public int getId();
	public int getCoins();
	public void setCoins(int coins);
	public void addCoins(int coins);
}
