package braunimmobilien.webapp.person;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.form.RequiredTextField;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Land;
import braunimmobilien.model.Telefonart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import braunimmobilien.service.LandManager;
import braunimmobilien.service.TelefonartManager;
public final class NewTelefonForm extends Panel{
	@SpringBean
    private TelefonartManager telefonartManager;
	
	TelefonListModel telefone;

       public List<String> arten = new ArrayList(){{
 		  Iterator telefonartiterator=telefonartManager.getTelefonartes().iterator();
 		    while(telefonartiterator.hasNext()){
 		    	Telefonart telefonart=(Telefonart)telefonartiterator.next();
 		    	String telefonartid=telefonart.getEn();
 		    	add(telefonartid);}	
 		
 	}};
  public NewTelefonForm(String id,TelefonListModel telefone) {
    super(id);
    this.telefone=telefone;
    final Form form = new Form("form", new CompoundPropertyModel(
        new TelefoneModel.Telefon()));
    add(form);
  
    form.add(new DropDownChoice<String>("art",  arten));
    form.add(new RequiredTextField("telefon"));
    form.add(new FeedbackPanel("feedback"));

    form.add(new Button("saveButton") {
      @Override
      public void onSubmit() {
        TelefoneModel.Telefon telefon= (TelefoneModel.Telefon) form.getModelObject();
        NewTelefonForm.this.telefone.getTelefonemodel().getTelefonList().add(telefon);
        TelefonPanel telefonPanel = (TelefonPanel) NewTelefonForm.this
            .getParent();
        telefonPanel.info("saved new telefon " + telefon);
        telefonPanel.setContentPanel();
      }
    });

    Button cancelButton = new Button("cancelButton") {
      @Override
      public void onSubmit() {
        ((TelefonPanel) NewTelefonForm.this.getParent())
            .setContentPanel();
      }
    };
    form.add(cancelButton);
    cancelButton.setDefaultFormProcessing(false);
  }
 
}
