package braunimmobilien.bootstrap.webapp.pages.tree;
import org.apache.wicket.model.IModel;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import de.agilecoders.wicket.core.settings.ITheme;
import java.util.Set;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.link.Link;
import braunimmobilien.bootstrap.webapp.pages.tree.theme.FlatlyTheme;
import braunimmobilien.bootstrap.webapp.pages.tree.theme.HumanTheme;
import braunimmobilien.bootstrap.webapp.pages.tree.theme.WindowsTheme;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.markup.ComponentTag;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import org.apache.wicket.markup.head.IHeaderResponse;
import braunimmobilien.bootstrap.webapp.pages.tree.theme.BootswatchThemeProvider;
import braunimmobilien.bootstrap.webapp.pages.tree.theme.BootswatchTheme;
public class MyNestedTree extends NestedTree<MyFoo> {
   private static final long serialVersionUID = 1L;
  

// @Override
    public Component newContentComponent(String id, final AbstractTree<MyFoo> tree, IModel<MyFoo> model)
    {	
        return new Folder<MyFoo>(id, tree, model)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected MarkupContainer newLinkComponent(String id, IModel<MyFoo> model)
            {
                MyFoo foo = model.getObject();

              /*  if (tree.getProvider().hasChildren(foo))
                {
                    return super.newLinkComponent(id, model);
                }
                else
                {*/
                Link<Void> link=foo.createContent(id);
            
                   // org.apache.wicket.markup.html.link.ExternalLink downloadLink = new org.apache.wicket.markup.html.link.ExternalLink(id, foo.getBar());
                  return   		link;
                 
              //  }
            }
        };
    }



	/**
	 * Construct.
	 * 
	 * @param id
	 *            component id
	 * @param provider
	 *            provider of the tree
	 */
	public MyNestedTree(String id, ITreeProvider<MyFoo> provider)
	{
		this(id, provider, null);
	}

	/**
	 * Construct.
	 * 
	 * @param id
	 *            component id
	 * @param provider
	 *            provider of the tree
	 * @param state
	 *            expansion state
	 */
	public MyNestedTree(String id, ITreeProvider<MyFoo> provider, IModel<Set<MyFoo>> state)
	{
		super(id, provider, state);
		add(new WindowsTheme());
		//add(new BootswatchThemeProvider().getTheme(Bootstrap.getSettings(getApplication()).getActiveThemeProvider().getActiveTheme().name().toLowerCase()));
	
	}
	/**
	 * Creates {@link Folder} for each node.
	 * 
	 * @param id
	 *            component id
	 * @param node
	 *            the node model
	 */
	@Override
	protected Component newContentComponent(String id, IModel<MyFoo> node)
	{
		return newContentComponent(id, this, node);
	}
	
	

  }

