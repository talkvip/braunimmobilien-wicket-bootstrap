package braunimmobilien.bootstrap.webapp.pages.auth;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import braunimmobilien.bootstrap.webapp.pages.einstellungen.EinstellungenPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.Page;
import braunimmobilien.bootstrap.webapp.MaklerSession;
import org.apache.wicket.RestartResponseAtInterceptPageException;
public class UserPanel extends Panel{
	public UserPanel(String id, Class<? extends Page> logoutPageClass){
	super(id);
	add(new Label("fullname",new PropertyModel(this,"session.nutzer.description")));
	PageParameters parameters = new PageParameters();
	parameters.add(SignOutPage.REDIRECTPAGE_PARAM, logoutPageClass.getName());
	add(new BookmarkablePageLink("signout",SignOutPage.class,parameters){
		@Override
		public boolean isVisible(){
			return MaklerSession.get().isAuthenticated();
		}
	});
	add(new Link("signin"){
		@Override
		public void onClick(){
			throw new RestartResponseAtInterceptPageException(SignInPage.class);
		}
		@Override
		public boolean isVisible(){
			return !MaklerSession.get().isAuthenticated();
		}
	});
	
	}

}
