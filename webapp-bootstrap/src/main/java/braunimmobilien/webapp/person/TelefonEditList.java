package braunimmobilien.webapp.person;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import java.util.Iterator;
import java.util.List;
import java.io.StringWriter;
import java.util.Collections;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jibx.runtime.JiBXException;
import org.apache.wicket.AttributeModifier;


public final class TelefonEditList extends Panel {
	
  private TelefonListModel telefone;
private String eigttel;
  public TelefonEditList(String id,TelefonListModel telefone) {
    super(id);
    this.telefone=telefone;
    Form form = new Form("form");
    add(form);
    form.add(new Button("newButton") {
      @Override
      public void onSubmit() {
        TelefonEditList.this.replaceWith(new NewTelefonForm(
            TelefonEditList.this.getId(),TelefonEditList.this.telefone));
      }
    });
   
    form.add(new FeedbackPanel("feedback"));
   
    RefreshingView telefoneView = new RefreshingView("telefone") {

      @Override
      protected Iterator getItemModels() {
    	  
        return new ModelIteratorAdapter(Collections.unmodifiableList(TelefonEditList.this.telefone.getTelefonemodel().getTelefonList()).iterator()) {
          @Override
          protected IModel model(Object object) {
            return EqualsDecorator
                .decorate(new CompoundPropertyModel((TelefoneModel.Telefon) object));
          }
        };
      }

      @Override
      protected void populateItem(Item item) {
        item.add(new Label("art"));
        item.add(new RequiredTextField("telefon"));
     

        final TelefoneModel.Telefon telefon = (TelefoneModel.Telefon) item.getModelObject();
        final Link removeLink = new Link("remove") {
          @Override
          public void onClick() {
        	  TelefonEditList.this.telefone.getTelefonemodel().getTelefonList().remove(telefon);
        	  TelefonPanel telefonPanel = (TelefonPanel) TelefonEditList.this
        	            .getParent();
        	        telefonPanel.info("removed telefon " + telefon);
          }
        };
        item.add(removeLink);
        removeLink.add(AttributeModifier.replace("onclick",
            "if(!confirm('remove telefon for "
                + telefon.getArt()+" "+telefon.getTelefon()
                + " ?')) return false;"));
      }
    };
    telefoneView.setItemReuseStrategy(ReuseIfModelsEqualStrategy
        .getInstance());
    form.add(telefoneView);
  }
}
