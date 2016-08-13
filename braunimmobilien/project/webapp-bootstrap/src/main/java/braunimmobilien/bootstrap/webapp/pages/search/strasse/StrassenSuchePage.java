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
package braunimmobilien.bootstrap.webapp.pages.search.strasse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.string.Strings;

import java.util.Collections;

import org.apache.wicket.event.IEvent;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import braunimmobilien.service.LandManager;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Strassen;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.AngebotManager;

import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.IModel;

import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.StrassenSuchePanel;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.StrassenPanel;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.ObjektPanel;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.LandPanel;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.OrtPanel;

/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class StrassenSuchePage extends BasePage
{
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 */
	@SpringBean
    private  AngobjzuordManager angobjzuordManager;
	@SpringBean
    private  AngebotManager angebotManager;
	@SpringBean
    private  ObjektManager objektManager;
	@SpringBean
    private  LandManager landManager;
	private Class responsepage;
	private PageParameters pageparameters;
	private Angobjzuord angobjzuord;
	private IModel<Angobjzuord> angobjzuordmodel;
	
	

	
	public StrassenSuchePage(final PageParameters pageparameters)
	{ 
		super(pageparameters);
		StrassenSucheForm form=new StrassenSucheForm("form");
			
		add(form);
	}
	
	private  class StrassenSucheForm extends  braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSucheForm{
		StrassenSucheForm(String id){super(id);}
		
		@Override
		public void onCancel(){
			
		
		}
		@Override
		public void onBack(){
			 
			   }
	
		@Override
		public void onNext(){
			
			
		}
		
		
	}
	
	
	
}
