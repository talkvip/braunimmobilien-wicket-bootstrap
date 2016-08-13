package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import org.apache.wicket.ajax.AjaxRequestTarget;
import java.util.List;
import java.util.ArrayList;
import braunimmobilien.model.Angobjzuord;
public class MyBreadCrumbLinkOff
{
	
private final AjaxRequestTarget target;
private Angobjzuord angobjzuord;
	/**
	 * Constructor
	 * 
	 * @param target
	 */
	public MyBreadCrumbLinkOff(AjaxRequestTarget target,Angobjzuord angobjzuord)
	{   this.angobjzuord=angobjzuord;
		this.target = target;
		System.err.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH "+angobjzuord);
	}

	/** @return ajax request target */
	public AjaxRequestTarget getTarget()
	{
		return target;
	}
	public Angobjzuord getAngobjzuord(){return angobjzuord;}
}