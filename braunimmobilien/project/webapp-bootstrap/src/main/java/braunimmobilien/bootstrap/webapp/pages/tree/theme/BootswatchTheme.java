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
package braunimmobilien.bootstrap.webapp.pages.tree.theme;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * @author svenmeier
 */
public class BootswatchTheme extends Behavior
{
	private static final long serialVersionUID = 1L;

	private static ResourceReference CSS = new CssResourceReference(BootswatchTheme.class,
		"windows/theme.css");

	private static  String name="";
	
	public BootswatchTheme(String name)
	{
			
			this.CSS = new CssResourceReference(BootswatchThemeProvider.class,"windows/theme.css");
			this.name=name;
	}
	
	@Override
	public void onComponentTag(Component component, ComponentTag tag)
	{
		tag.append("class", "tree-theme-windows", " ");
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		response.render(CssHeaderItem.forReference(CSS));
	}
}
