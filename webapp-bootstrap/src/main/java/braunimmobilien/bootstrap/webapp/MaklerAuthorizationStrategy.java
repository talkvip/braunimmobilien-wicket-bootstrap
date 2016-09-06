package braunimmobilien.bootstrap.webapp;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
public class MaklerAuthorizationStrategy extends SimplePageAuthorizationStrategy{
	public MaklerAuthorizationStrategy(Class classtocheck, Class signin){
		super(classtocheck,signin);
		
	} 
	protected boolean isAuthorized()
	  {		if(MaklerSession.get().getNutzer()!=null)               
	    return true;
	  else return false;
	  }
}
