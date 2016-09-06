package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class MyBreadCrumbLinkOn {

	private final AjaxRequestTarget target;
private int node =0;
	/**
	 * Constructor
	 * 
	 * @param target
	 */
	public MyBreadCrumbLinkOn(AjaxRequestTarget target,int node)
	{
		this.target = target;
		node=node;
	}

	/** @return ajax request target */
	public AjaxRequestTarget getTarget()
	{
		return target;
	}
public int getNode(){return node;}
}
