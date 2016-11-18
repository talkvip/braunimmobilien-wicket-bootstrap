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

import java.io.File;

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
import org.apache.wicket.model.StringResourceModel;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Land;
import braunimmobilien.bootstrap.webapp.EntityModel;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.spring.injection.annot.SpringBean;
import braunimmobilien.model.Orte;
import braunimmobilien.service.LandManager;
/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class LandPanel extends BreadCrumbPanel
{	@SpringBean
    private  LandManager landManager;
	/** Test form. */
	@SuppressWarnings("serial")
	private final class LandInput extends Form<Land>
	{
		/** test input string. */
		

		/**
		 * Construct.
		 * 
		 * @param id
		 *            The component id
		 */
		
		public LandInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Land> land)
		
		{super(id, new CompoundPropertyModel<Land>(land));
			
		
		 add(new TextField<String>("landname"));
		
		 add(new TextField<String>("kennzeichen"));
		 
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
							
							Land land=LandInput.this.getModelObject();
							landManager.save(land);
							Orte ort=new Orte();
							ort.setId(null);
							ort.setLand(land);
							
							
							return new OrtPanel(componentId, responsepage,pageparameters,breadCrumbModel,new EntityModel<Orte>(ort));
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
/*	public LandPanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);


		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));

		add(new LandInput("form",null,null,new EntityModel<Land>(new Land())));
	}
	public LandPanel(final String id, final IBreadCrumbModel breadCrumbModel,IModel<Land> land)
	{
		super(id, breadCrumbModel);


		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));

		add(new LandInput("form",null,null,land));
	}*/
	
	public LandPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel,IModel<Land> land)
	{
		super(id, breadCrumbModel);

		Link pdfLink=new Link("linkToFirst") {
		    public void onClick() {
		   this.setResponsePage(responsepage,pageparameters);

		    }
		    };
		add(pdfLink);

	

		add(new LandInput("form",responsepage,pageparameters,land));
	}
	
	/**
	 * @see org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant#getTitle()
	 */
	@Override
	public IModel<String> getTitle()
	{
		return Model.of((new StringResourceModel("land.searchshort",this,null)).getObject());
	}
}
