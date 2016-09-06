package braunimmobilien;

import org.springframework.beans.factory.annotation.Autowired;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ScoutManager;

public class ReIndexEigentuemer {
	@Autowired
	private  PersonManager personManager;
public PersonManager getPersonManager() {
		return personManager;
	}
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}
void reindex(){
	
	personManager.reindex();
}	
	
	
}
