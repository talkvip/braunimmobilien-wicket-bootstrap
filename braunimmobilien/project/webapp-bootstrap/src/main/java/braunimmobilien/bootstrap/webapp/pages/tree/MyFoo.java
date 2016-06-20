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
import org.apache.wicket.markup.html.link.Link;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.WebPage;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
/**
 * @author Sven Meier
 */
public   class  MyFoo extends WebPage
{	
	private static final long serialVersionUID = 1L;

	private String treeid;

	private String bar;
	
	private Page page;

	public void setPage(Page page) {
		this.page = page;
	}

	private String baz;

	private boolean quux;

	private boolean loaded;

	private MyFoo treeparent;

	private List<MyFoo> foos = new ArrayList<MyFoo>();

	public MyFoo(String id)
	{	
		this.treeid=id;
		bar = id.toLowerCase() + "Bar";
		baz = id.toLowerCase() + "Baz";
	}

	public MyFoo(String id,Page page)
	{
		this.treeid=id;
		bar = id.toLowerCase() + "Bar";
		baz = id.toLowerCase() + "Baz";
		this.page=page;
	}
	
	public MyFoo(MyFoo parent, String name)
	{
		this.treeid=name;

		this.treeparent = parent;
		this.treeparent.foos.add(this);
	}

	public MyFoo(MyFoo parent, String name,Page page)
	{
		this(name);

		this.treeparent = parent;
		this.treeparent.foos.add(this);
		this.page=page;
	}
	
	public MyFoo getTreeParent()
	{
		return this.treeparent;
	}

	public String getTreeid()
	{
		return this.treeid;
	}

	public String getBar()
	{
		return bar;
	}

	public String getBaz()
	{
		return baz;
	}

	public void setBar(String bar)
	{
		this.bar = bar;
	}

	public void setBaz(String baz)
	{
		this.baz = baz;
	}

	public void setQuux(boolean quux)
	{
		this.quux = quux;

		if (quux)
		{
			// set quux on all descendants
			for (MyFoo foo : foos)
			{
				foo.setQuux(true);
			}
		}
		else
		{
			// clear quux on all ancestors
			if (treeparent != null)
			{
				treeparent.setQuux(false);
			}
		}
	}

	public boolean getQuux()
	{
		return quux;
	}

	public List<MyFoo> getFoos()
	{
		return Collections.unmodifiableList(foos);
	}

	@Override
	public String toString()
	{
		return this.getTreeid();
	}

	public boolean isLoaded()
	{
		return loaded;
	}

	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}
	
	
	

public Link<Void> createContent(String id){
	
	return new Link<Void>(id){
		public void onClick() {
			setResponsePage(page);  
	}	
};
}		


	
}
