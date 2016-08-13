/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package braunimmobilien.bootstrap.webapp.pages.breadcrumb;
import org.apache.wicket.extensions.breadcrumb.DefaultBreadCrumbsModel;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.model.StringResourceModel;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.bootstrap.webapp.EntityModel;

import org.apache.wicket.spring.injection.annot.SpringBean;
/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class OrtPanel extends BreadCrumbPanel
{@SpringBean
    private  LandManager landManager;
@SpringBean
private  OrteManager orteManager;
	private final class OrtInput extends Form<Orte>
	{
		/** test input string. */
	

		/**
		 * Construct.
		 * 
		 * @param id
		 *            The component id
		 */
		public OrtInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Orte> ort)
		
		{super(id, new CompoundPropertyModel<Orte>(ort));
		
			
		 add(new TextField<String>("ortname"));
		 add(new TextField<String>("ortplz"));
	
			add(new Button("normalButton")
			{
				@Override
				public void onSubmit()
				{
					activate(new IBreadCrumbPanelFactory()
					{
						@Override
						public BreadCrumbPanel create(String componentId,
							IBreadCrumbModel breadCrumbModel)
						{
							
							Orte ort=OrtInput.this.getModelObject();
						    Land land=landManager.get(ort.getLand().getId());
							ort.setLand(land);
							land.addOrt(ort);
							orteManager.save(ort);
							Strassen strasse=new Strassen();
							strasse.setId(null);
							strasse.setOrt(ort);
							
							return new StrassenPanel(componentId,responsepage,pageparameters, breadCrumbModel, new EntityModel<Strassen>(strasse));
						}
					});
				}
			});
			
		}

	
	}

	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 */
	public OrtPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel,IModel<Orte> ort)
	{
		super(id, breadCrumbModel);
		Link pdfLink=new Link("linkToFirst") {
		    public void onClick() {
		   this.setResponsePage(responsepage,pageparameters);

		    }
		    };
		add(pdfLink);
		add(new OrtInput("form",responsepage,pageparameters,ort));
	}
public OrtPanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		
		IBreadCrumbModel newbreadCrumbModel=new DefaultBreadCrumbsModel();
		
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new OrtInput("form",null,null,new EntityModel<Orte>(new Orte())));
	}
	/**
	 * @see org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant#getTitle()
	 */
	@Override
	public IModel<String> getTitle()
	{
		return Model.of((new StringResourceModel("ort.searchshort",this,null)).getObject());
	}
}
