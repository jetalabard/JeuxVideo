package fr.dataloader;

import java.util.List;

import fr.jeuxvideo.about.About;
import fr.metier.Choice;
import fr.metier.Maj;


public interface Requete {

	About loadAbout();

	List<Choice> loadChoices();

	Maj loadMaj();
	
}
