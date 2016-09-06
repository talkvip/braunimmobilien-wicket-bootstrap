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
package braunimmobilien.bootstrap.webapp.pages.wizard;

import braunimmobilien.bootstrap.webapp.pages.WicketExamplePage;



import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import braunimmobilien.model.Land;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;

/**
 * Index page for the wizard example.
 * 
 * @author Eelco Hillenius
 */
public class Index extends WicketExamplePage
{Class responsepage;
PageParameters pageparameters;
	/**
	 * Link to the wizard. It's an internal link instead of a bookmarkable page to help us with
	 * backbutton surpression. Wizards by default do not partipcate in versioning, which has the
	 * effect that whenever a button is clicked in the wizard, it will never result in a change of
	 * the redirection url. However, though that'll work just fine when you are already in the
	 * wizard, there is still the first access to the wizard. But if you link to the page that
	 * renders it using and internal link, you'll circumvent that.
	 */
	private static final class WizardLink extends Link
	{
		private final Class<? extends Wizard> wizardClass;

		/**
		 * Construct.
		 * 
		 * @param <C>
		 * 
		 * @param id
		 *            Component id
		 * @param wizardClass
		 *            Class of the wizard to instantiate
		 */
		public <C extends Wizard> WizardLink(String id, Class<C> wizardClass)
		{
			super(id);
			this.wizardClass = wizardClass;
		}

		/**
		 * @see org.apache.wicket.markup.html.link.Link#onClick()
		 */
		@Override
		public void onClick()
		{this.
			setResponsePage(new WizardPage(wizardClass));
		}
	}

	/**
	 * Construct.
	 */
	public Index(Class responsepage,PageParameters pageparameters)
	{super(pageparameters);
	//	add(new WizardLink("staticWizardLink", StaticWizard.class));
	//	add(new WizardLink("staticWizardWithPanelsLink", StaticWizardWithPanels.class));
		add(new WizardLink("newLandWizardLink", NewLandWizard.class));
		   Form<Void> form = new Form<Void>("ConvertForm") {
	             @Override
	             protected void onComponentTag(ComponentTag tag) {
	                 super.onComponentTag(tag);
	                 tag.put("target", "_blank");
	             }
	             @Override
	             public void onSubmit()
	             {setResponsePage(Index.class);

	             }
	         }; 
	         add(form);
	        
	         form.add(new Button("saveButton"));
	        
	}
	public Index()
	{super(new PageParameters());
	//	add(new WizardLink("staticWizardLink", StaticWizard.class));
	//	add(new WizardLink("staticWizardWithPanelsLink", StaticWizardWithPanels.class));
		add(new WizardLink("newLandWizardLink", NewLandWizard.class));
		   Form<Void> form = new Form<Void>("ConvertForm") {
	             @Override
	             protected void onComponentTag(ComponentTag tag) {
	                 super.onComponentTag(tag);
	                 tag.put("target", "_blank");
	             }
	             @Override
	             public void onSubmit()
	             {setResponsePage(Index.class);

	             }
	         }; 
	         add(form);
	        
	         form.add(new Button("saveButton"));
	        
	}
	
}
