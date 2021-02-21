var adrInit = "http://176.190.24.49/"; //"http://localhost:8080/";
var codeVote = "";

function ajouterVotantAdr(){
	return adrInit+"ajouter/votant/";
}
function finVote(){
	return adrInit+"img/merci.gif";
}
function finVote_code_incorrect(){
	return adrInit+"img/fache.gif";
}
function finVote_deja_voter(){
	return adrInit+"img/deja_utiliser.gif";
}

function voterAdr(){
	return adrInit+"voter/";
}
function candidatesImg(nom){
	return adrInit+"img/"+nom;
}

function candidatesAdr(){
	return adrInit+"candidates/";
}

function existAdr(){
	return adrInit+"exist/";
}
function getVoteAdr(){
	return adrInit+"pages/part/vote.html";
} 
 
function getFinAdr(){
	return adrInit+"pages/part/fin.html";
} 
//images
function imgMali(){
	return adrInit+"img/dMali.gif";
} 
function imgAuvergne(){
	return adrInit+"img/auvergne.jpg";
} 
function imgVote(){
	return adrInit+"img/vote.jpg";
} 

var cands = []

function loadVote(){
	fetch(getVoteAdr()).then(e => e.text()).then(con => {
		var content = document.getElementById('content');
		content.innerHTML = con;
		
		document.getElementById('mali').src = imgMali();
		document.getElementById('auvergne').src = imgAuvergne();
		document.getElementById('vote').src = imgVote();
		
	fetch(candidatesAdr()).then(e => e.json()).then(e => {
				//alert("c'est bon")
				var res = "";
				for(var i=0; i<e.length; i++){
					var value = e[i].prenom+" "+(e[i].nom).toUpperCase();
					var id = e[i].matricule;
					var src = candidatesImg(e[i].img);					
					res += "<div class='row' style = 'background: white; border: 1px solid black; padding: 5px; margin-top:0.5em;'>" + 
							"<div class='col-md-5 col-sm-5 col-xs-5'><img src='"+src+"' height='150' width='150'/> </div>" + 
							"<div class='col-md-6 col-sm-6 col-xs-6' style='font-size:2em; color:blue; ' > " + value + "</div>"+
							"<div class='col-md-1 col-sm-1 col-xs-1'> <input class='form-check-input' type='radio' id='"+id+"' name='cands'></input> <label style='margin-top: 2em;' class='form-check-label' for='" + id + "'>Vote</label> </div>"+
							"</div>";
					cands.push(id);
				}
				res +="<div class='row' style='margin-top: 1em;'> <button onclick='voter()' class='btn btn-primary'>Votez</button> </div>"
				document.getElementById("candidates").innerHTML = res;
		}).catch(e => {alert(e)});		
	
	}).catch(ef => {});
}
	
function voter(){
	
	var voteOk = false;
	
	//alert('Je vote');
	
	for(var i =0; i<cands.length; i++){
		if (document.getElementById(cands[i]).checked){
			var content = "{'matricule':'"+cands[i]+"', 'code':'"+codeVote+"'}";
			var param = {"method":"POST", headers: {
				'Accept': 'application/json', 'Content-Type': 'application/json'},
				"body": content, "Access-Control-Allow-Origin": "*"
			};
			fetch(voterAdr(), param).then(e => e.json()).then(e => {
				if (e.result == 2){
					//alert("Vote effectué")
					var el = "<div style='color:green; background:white; margin-top: 1em;'> Merci pour le vote, votre voix est importante pour l'amélioration du bureau AMAUV </div>";
					el += "<div style='margin-top: 1em;'><img src='"+finVote()+"'/></div>"; 
					document.getElementById("content").innerHTML = el;
				}
				
				if (e.result == 1){
					//alert("Vote deja faite")
					var el = "<div style='color:orange; background:white; margin-top: 1em;'>Oups vous avez deja voté ... :) </div>";
					el += "<div style='margin-top: 1em;'><img src='"+finVote_deja_voter()+"'/></div>"; 
					document.getElementById("content").innerHTML = el;
				}
				
				if (e.result == 0){
					//alert("vote Code de vote incorrect")
					var el = "<div style='color:red; background:white; margin-top: 1em;'> Desolé ce code n'existe pas </div>";
					el += "<div style='margin-top: 1em;'><img src='"+finVote_code_incorrect()+"'/></div>"; 
					document.getElementById("content").innerHTML = el;
				}
			}).catch(e => {alert(e);});
			voteOk = true;
			break;
		}
	}
	if (!voteOk)
		alert("Vous devez selectionner un candidat :)");
}

function valider_code(){
	//alert("valider");
	var code = document.getElementById("code").value;
	var content = '{"code":"'+code+'"}';
	 var param = {"method":"POST", headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        "body": content,
        "Access-Control-Allow-Origin": "*"
    };

    //JSON.stringify(resp)

fetch(existAdr(), param).then(resp => resp.json()).then(resp => {
	if (resp.result == 2){
		//alert("Ce code exist")
		codeVote = code;
		loadVote();
	}else {
		document.getElementById("erreur").style="display:block;color: red; text-align:center; size: 2em;";
		if (resp.result == 1){
			//alert("Ce code est dèja utilisé !")
			document.getElementById("erreur").innerHTML = "Ce code est dèja utilisé";
		}else {
			//alert("Ce code n'existe pas !")
			document.getElementById("erreur").innerHTML = "Ce code n'existe pas";
		}	
	}
}).catch(error => {
//	alert("Erreur inconnue est survenue. Veuillez contacter les admis ...");
	document.getElementById("erreur").style="display:block;color: red; text-align:center; size: 2em;";
	document.getElementById("erreur").innerHTML = "Erreur inconnue est survenue. Veuillez contacter les admis ...";
	//alert(error);
});  
}


function ajouter_votant(){
	
	var pass = document.getElementById("pass").value;
	var nom = document.getElementById("nom").value;
	var prenom = document.getElementById("prenom").value;
	var telephone = document.getElementById("telephone").value;
	var content = '{"nom":"'+nom+'", "prenom":"'+prenom+'", "telephone":"'+telephone+'", "pass":"'+pass+'"}';
	 var param = {"method":"POST", headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        "body": content,
        "Access-Control-Allow-Origin": "*"
    };

    //JSON.stringify(resp)

fetch(ajouterVotantAdr(), param).then(resp => resp.json()).then(resp => {
	var res = resp.etat;
	document.getElementById("message");
	if (res == 0){
		document.getElementById("message").style = "background:white; color: green; text-align:center; font-size: 2em;  padding:0.5em;  height: 3em;";
		document.getElementById("message").innerHTML = "Code: "+resp.code;
	}
	if (res == 1){
		document.getElementById("message").style = "background:white; color: red; text-align:center; font-size: 2em; padding:0.5em; height: 3em;";
		document.getElementById("message").innerHTML = "Mot de passe incorrect";
	}
	if (res == 2){
		document.getElementById("message").style = "background:white; color: orange; text-align:center; font-size: 2em; padding:0.5em; height: 3em;";
		document.getElementById("message").innerHTML = "Cette personne est dèja enregistrée";
	}
}).catch(e => {Alert(e)});	

}


