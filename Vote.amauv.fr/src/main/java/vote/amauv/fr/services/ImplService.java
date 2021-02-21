package vote.amauv.fr.services;

import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import vote.amauv.fr.config.Config;

@Component
public class ImplService implements Service{

	public int thisCodeExists(String code) throws Exception{
		code = code.replaceAll(" ", "");
		String q1 = "SELECT * FROM votants WHERE code = '"+code+"'";
		String q2 = "SELECT * FROM votes WHERE code = '"+code+"'";
		
		ResultSet res1 = Config.getCon().createStatement().executeQuery(q1);
		if (res1.next())
			return 2;
		res1.close();
		
		ResultSet res2 = Config.getCon().createStatement().executeQuery(q2);
		if (res2.next())
			return 1;		
		res2.close();
		
		return 0;
	}


	public int voter(String code, String matricule) throws Exception {
		code = code.replaceAll(" ", "");
		String vote = "INSERT  INTO votes SELECT * FROM votants WHERE code='"+code+"'";
		String del = "DELETE FROM votants WHERE code='"+code+"'";
		String up = "UPDATE candidates SET vote = vote + 1 WHERE matricule = '"+matricule+"'";
		int res = 0;
		try {
			Config.getCon().setAutoCommit(false);
			ResultSet r = Config.getCon().createStatement().executeQuery("Select 1 FROM votes WHERE code = '"+code+"'");
			if (r.next()) {
				res = 1;
				throw new Exception("Deja consommé");
			}
			r.close();

			ResultSet r1 = Config.getCon().createStatement().executeQuery("Select 1 FROM votants WHERE code = '"+code+"'");
			if (!r1.next()) {
				res = 0;
				throw new Exception("ce code n'existe pas");
			}
			r1.close();
			
			Config.getCon().createStatement().executeUpdate(vote);
			Config.getCon().createStatement().executeUpdate(del);
			int count1 = Config.getCon().createStatement().executeUpdate(up);
			if (count1<=0) {
				throw new Exception("Probleme de matricule du candidat");
			}
			Config.getCon().commit();
			Config.getCon().setAutoCommit(true);
			res = 2;
		}catch(Exception e) {
			Config.getCon().rollback();
			Config.getCon().setAutoCommit(true);
		} 
		return res;
	}


	public String getCandiates() throws Exception {
		String query = "SELECT matricule, nom, prenom, img, vote FROM candidates";
		JSONArray res = new JSONArray();
		
		ResultSet queryResult = Config.getCon().createStatement().executeQuery(query); 
		while(queryResult.next()) {
			JSONObject candidate = new JSONObject();
			candidate.put("matricule", queryResult.getString("matricule"));
			candidate.put("nom", queryResult.getString("nom"));
			candidate.put("prenom", queryResult.getString("prenom"));
			candidate.put("img", queryResult.getString("img"));
			candidate.put("vote", queryResult.getInt("vote"));
			res.put(candidate);  
		}  
		queryResult.close();
		return res.toString();
	}


	public String ajouterUnVotant(String nom, String prenom, String telephone) throws Exception {
		JSONObject result = new JSONObject();
		
		ResultSet tempRes = Config.getCon().createStatement().executeQuery("SELECT 1 FROM votants WHERE telephone='"+telephone+"'");
		
		if (tempRes.next()) {
			result.put("etat", 2);
			tempRes.close();
			return result.toString();
		}
		tempRes.close();
		
		//Date date = new Date(System.currentTimeMillis());
		//String code = nom+":"+prenom+telephone+":"+date.toString();
		ResultSet resu = Config.getCon().createStatement().executeQuery("Select count(*) from votants");
		resu.next();
		String code = ((Math.random()>=0.5)?"-":"+")+ ((Math.random()>=0.5)?"@":"#") +resu.getInt(1)+"-"+((Math.random()>=0.5)?"Mali":"France");//telephone.substring(0, 6)+code.hashCode()+telephone.substring(6);
		resu.close();
		telephone = telephone.replace(" ", "");
		int r = Config.getCon().createStatement().executeUpdate("INSERT INTO votants (code, nom, prenom, telephone) values ('"+code+"', '"+nom+"', '"+prenom+"', '"+telephone+"')");
		if (r<=0) {
			result.put("etat", 2);
			tempRes.close();
			return result.toString();
		}
		result.put("etat", 0);
		result.put("code", code); 
		return result.toString();
	} 
	
	
}
