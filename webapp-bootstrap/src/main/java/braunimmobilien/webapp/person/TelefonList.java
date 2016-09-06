package braunimmobilien.webapp.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jibx.runtime.JiBXException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import java.util.Collections;



public class TelefonList extends Panel {
	 private TelefonListModel telefone;
		
  public TelefonList(String id,TelefonListModel telefone) {
    super(id);
   this.telefone=telefone;
    add(new RefreshingView("telefone") {

      @Override
      protected Iterator getItemModels() {

        return new ModelIteratorAdapter(Collections.unmodifiableList(TelefonList.this.telefone.getTelefonemodel().getTelefonList()).iterator()) {
          @Override
          protected IModel model(Object object) {
            return new CompoundPropertyModel((TelefoneModel.Telefon) object);
          }
        };
      }

      @Override
      protected void populateItem(Item item) {
        item.add(new Label("art"));    
        item.add(new Label("telefon"));      
      }
    });
  }
}
