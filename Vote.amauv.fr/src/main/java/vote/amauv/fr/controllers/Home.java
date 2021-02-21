package vote.amauv.fr.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vote.amauv.fr.services.Service;

@RestController
public class Home implements ErrorController{

	@Autowired
	private Service service;
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView m = new ModelAndView("home");
		return m;
	}
    
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView m = new ModelAndView("register");
		return m;
	}
	
	@GetMapping("/voir/vote")
	public ModelAndView voirVote() {
		System.out.println("hello ----------------------------");
		ModelAndView m = new ModelAndView("voir_vote");
		return m;
	}
	
	private static final String errorPath = "/error";
	 
	public String getErrorPath() {
		return errorPath;
	}
	  
	@GetMapping(errorPath)
	public String error() {
		return "<h2 style='text-align: center;'>Oups une erreur ... Veuillez contacter les admis svp <h2>";
	}

	@RequestMapping(value = "/candidates/", method = RequestMethod.GET)
	public String candidates() {
		try {
			//System.out.println("hello :::::::::: ");
			String res = service.getCandiates();
			//System.out.println("res: "+res);
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataLoadingError();
		}
	}  
	 
	@RequestMapping(value = "/exist/", method = RequestMethod.POST)
	public String existVotant(@RequestBody String param) {
		try {
			JSONObject obj = new JSONObject(param);
			int res = service.thisCodeExists(obj.getString("code"));
			
//			System.out.println("res: "+res);
			
			return "{\"result\":"+res+"}";
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataLoadingError();
		}
	} 

	@RequestMapping(value = "/voter/", method = RequestMethod.POST)
	public String voter(@RequestBody String param) {
		try {
			JSONObject obj = new JSONObject(param);
			int res = service.voter(obj.getString("code"), obj.getString("matricule"));
			
			System.out.println("res: "+res);
			
			return "{\"result\":"+res+"}";
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataLoadingError();
		}
	}
	
	@RequestMapping(value = "/ajouter/votant/", method = RequestMethod.POST)
	public String ajoutVotant(@RequestBody String param) {
		try {
			JSONObject obj = new JSONObject(param);
			if (!obj.getString("pass").equals("223.33@AMAUV")) {
				return "{\"etat\":1}";
			}
			String nom = obj.getString("nom");
			String prenom = obj.getString("prenom");
			String telephone = obj.getString("telephone");
			
			String res = service.ajouterUnVotant(nom, prenom, telephone);
			System.out.println(res);			
			return res;  
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataLoadingError();
		} 
	}   
	
}
