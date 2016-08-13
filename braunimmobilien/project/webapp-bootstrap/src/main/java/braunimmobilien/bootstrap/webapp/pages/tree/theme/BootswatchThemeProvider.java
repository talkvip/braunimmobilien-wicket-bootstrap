package braunimmobilien.bootstrap.webapp.pages.tree.theme;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.HumanTheme;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
public class BootswatchThemeProvider  {
	Map<String,Behavior> map = new HashMap<String,Behavior>();
  
	public BootswatchThemeProvider()
	{
		 map.put("windows", new WindowsTheme());
		 map.put("human", new HumanTheme());
		 map.put("flatly", new FlatlyTheme());
	}

	
	
	public Behavior getTheme(String name){	
		
		if(map.containsKey(name))
		return (Behavior) map.get(name);
		else return (Behavior) map.get("windows");
	}

	
}
