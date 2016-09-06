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
package braunimmobilien.bootstrap.webapp.pages.tree;
import braunimmobilien.bootstrap.webapp.pages.angebot.*;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.provider.*;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.service.AngebotManager;
import org.apache.wicket.markup.html.link.Link;

public class MyBeginnersTreePage extends BasePage
{
	public List<MyFoo> foos = new ArrayList<MyFoo>();
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private AngebotManager angebotManager;
	 
	public MyBeginnersTreePage()
	{   super(new PageParameters());
		PageParameters parameters = new PageParameters();
		WicketApplication.get().foos.clear();
		List<Angebot> list=angebotManager.getAngebote();
		
		Iterator it=list.iterator();
				while(it.hasNext()){
		Angebot angebot=(Angebot)it.next();	
		
		 parameters.add("angnr", angebot.getId());   
		MyFoo fooA = new MyFoo(angebot.getId(),new IndexPage());   
	WicketApplication.get().foos.add(fooA);
//		foos.add(fooA);
	Iterator iterator=angebot.getAngobjzuords().iterator();
	
				while(iterator.hasNext()){
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		MyFoo fooAB=new MyFoo(fooA, angobjzuord.getObjekte().getObjektart().getObjartname(),new IndexPage());
		
		Iterator iterator1=angobjzuord.getObjekte().getObjperszuords().iterator();
		while(iterator1.hasNext()){
			Objperszuord objperszuord=(Objperszuord)iterator1.next();
			MyFoo fooAAB =new MyFoo(fooAB, objperszuord.getPersonen().getEigtName(),new IndexPage());	
		}
	}
				}
				add(new MyNestedTree("tree", new MyFooProvider()));
	}	
	
	
	
	public MyBeginnersTreePage(PageParameters pageparameters)
	{   
		
		 super(pageparameters);
		if(pageparameters.getPosition("angnr")>-1){
		WicketApplication.get().foos.clear();
		Angebot angebot=angebotManager.get(pageparameters.get("angnr").toString());	
		PageParameters parameters = new PageParameters();
		 parameters.add("angnr", angebot.getId());   
		MyFoo fooA = new MyFoo(angebot.getId(),new AngebotPage(new AngebotPage(new AngebotList(),angebot)));   
	WicketApplication.get().foos.add(fooA);
//		foos.add(fooA);
	Iterator iterator=angebot.getAngobjzuords().iterator();
	new MyFoo(fooA, "weiteres Objekt",new AngobjzuordForm(new AngebotList()));
				while(iterator.hasNext()){
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		MyFoo fooAB=new MyFoo(fooA, angobjzuord.getObjekte().getObjektart().getObjartname(),new AngobjzuordForm(new AngebotList(),angobjzuord));
		new MyFoo(fooAB, "weitere Person",new IndexPage());
		Iterator iterator1=angobjzuord.getObjekte().getObjperszuords().iterator();
		while(iterator1.hasNext()){
			Objperszuord objperszuord=(Objperszuord)iterator1.next();
			MyFoo fooAAB =new MyFoo(fooAB, objperszuord.getPersonen().getEigtName(),new IndexPage());	
		}
	}
				
				add(new MyNestedTree("tree", new MyFooProvider()));}
	}	
	
	}
	
