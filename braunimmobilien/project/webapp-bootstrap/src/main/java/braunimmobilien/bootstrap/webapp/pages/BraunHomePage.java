package braunimmobilien.bootstrap.webapp.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import braunimmobilien.bootstrap.webapp.components.site.Footer;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;


/**
 * The {@code HomePage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/", alt = "/home")
public class BraunHomePage extends BasePage {
	
	/**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public BraunHomePage() {
        super();
       
        String version = getProperties().getProperty("bootstrap.fix.version");
      //  add(new Footer("footer"));
        
      
       
        
    }
    public BraunHomePage(PageParameters parameters) {
        super(parameters);
       
        String version = getProperties().getProperty("bootstrap.fix.version");
      //  add(new Footer("footer"));
        
      
       
        
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
    	super.renderHead(response);
      StringBuilder b = new StringBuilder();
  	b.append("function buildDomTree() {\n")
	.append("var data = [];\n")
	.append(" function walk(nodes, data) {\n")
	.append(" if (!nodes) { return; }\n")
	.append("$.each(nodes, function (id, node) {\n")
	.append("var obj = {\n")
	.append("id: id,\n")
	.append("text: node.nodeName + ' - ' + (node.innerText ? node.innerText : ''),\n")
	.append("tags: [node.childElementCount > 0 ? node.childElementCount + ' child elements' : '']\n")
  	.append(" };\n")
	.append("if (node.childElementCount > 0) {\n")
	.append("obj.nodes = [];\n")
	.append("walk(node.children, obj.nodes);\n")
	.append(" }\n")
  	.append(" data.push(obj);\n")
	.append("});\n")
	.append("}\n")
	.append(" walk($('html')[0].children, data);\n")
	.append(" return data;")
  	.append("}")
  	.append("	$(function() {\n")
	.append("var options = {\n")		
	.append("bootstrap2: false, \n")
	.append("showTags: true,\n")
	.append("levels: 5,\n")
	.append("data: buildDomTree()\n")
	.append("};\n")
	.append("$('#treeview').treeview(options);\n")
	.append("});\n");
		
      response.render(OnDomReadyHeaderItem.forScript(b)) ;
  }
    }

