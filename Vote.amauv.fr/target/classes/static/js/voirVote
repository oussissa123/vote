var adrInit = "http://localhost:8080/";
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
alert("commence")
/*
function voirLesVotes(){
	alert("hello")
	//console.log("hello");
	fetch(candidatesAdr()).then(e => e.json()).then(e => {
					//alert("c'est bon")
					var res = "";
					for(var i=0; i<e.length; i++){
						var value = e[i].prenom+" "+(e[i].nom).toUpperCase();
						var id = e[i].matricule;
						var src = candidatesImg(e[i].img);	
						var vote = e[i].vote;
						res += "<div class='row' style = 'background: white; border: 1px solid black; padding: 5px; margin-top:0.5em;'>" + 
								"<div class='col-md-5 col-sm-5 col-xs-5'><img src='"+src+"' height='150' width='150'/> </div>" + 
								"<div class='col-md-6 col-sm-6 col-xs-6' style='font-size:2em; color:blue; ' > " + value + "</div>"+
								"<div class='col-md-6 col-sm-6 col-xs-6' style='background:rgba(0, 0, 0, 0.1); color: green; font-size: 2.5em;' >"+vote+"</div>"+
								"</div>";
					}
					document.getElementById("contenu").innerHTML = res;
	}).catch(e => {alert(e)});
}
setTimeout(voirLesVotes(), 1000);
setInterval(voirLesVotes(), 5000); */