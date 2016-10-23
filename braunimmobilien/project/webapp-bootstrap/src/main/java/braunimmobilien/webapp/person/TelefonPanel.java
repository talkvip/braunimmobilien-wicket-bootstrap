package braunimmobilien.webapp.person;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import java.util.List;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
public class TelefonPanel extends Panel{
private boolean inEditMode = false;
TelefonListModel telefone;
	
public TelefonPanel(String id,TelefonListModel telefone){
	super(id);
	this.telefone=telefone;
	add(new TelefonList("content",telefone));
	
	final Link modeLink = new Link("modeLink"){
		@Override
		public void onClick(){
			
			inEditMode=!inEditMode;
			setContentPanel();
		}
	};
	add(modeLink);
	modeLink.add(new Label("linkLabel",new AbstractReadOnlyModel(){
		@Override
		public Object getObject(){
			return inEditMode ? "[display telefonnumbers]" : "[edit and insert telephone]";	
		}
		
	}));
}
void setContentPanel(){
	if(inEditMode){
		addOrReplace(new TelefonEditList("content",telefone));
		
	}
	else{addOrReplace(new TelefonList("content",telefone));
	}
}

}
