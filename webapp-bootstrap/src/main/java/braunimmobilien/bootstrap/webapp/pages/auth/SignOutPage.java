package braunimmobilien.bootstrap.webapp.pages.auth;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.Page;

public class SignOutPage extends WebPage{
	public static final String REDIRECTPAGE_PARAM = "redirectpage";
	@SuppressWarnings("unchecked")
	public SignOutPage(final PageParameters parameters){
		String page = parameters.get(REDIRECTPAGE_PARAM).toString();
		Class<? extends Page> pageClass;
		if(page != null){
			try {
				pageClass = (Class<? extends Page>) Class.forName(page);
			}catch(ClassNotFoundException e){throw new RuntimeException(e);
		}
		}
		else {pageClass = getApplication().getHomePage();
}
getSession().invalidate();
setResponsePage(pageClass);
			}
}
