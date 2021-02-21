package vote.amauv.fr.services;

public interface Service {
	public int thisCodeExists(String code) throws Exception;
	public int voter(String code, String matricule) throws Exception;
	public String getCandiates() throws Exception;
	public String ajouterUnVotant(String nom, String prenom, String telephone) throws Exception;
}
