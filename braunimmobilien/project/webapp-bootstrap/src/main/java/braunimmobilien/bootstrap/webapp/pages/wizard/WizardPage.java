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

import java.lang.reflect.Constructor;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import braunimmobilien.bootstrap.webapp.pages.WicketExamplePage;
import org.apache.wicket.extensions.wizard.Wizard;


/**
 * Page for displaying a wizard.
 * 
 * @author Eelco Hillenius
 */
public class WizardPage extends WicketExamplePage
{
	/**
	 * Construct.
	 * 
	 * @param <C>
	 * 
	 * @param wizardClass
	 *            class of the wizard component
	 */
	public <C extends Wizard> WizardPage(Class<C> wizardClass)
	{	
		if (wizardClass == null)
		{
			throw new IllegalArgumentException("argument wizardClass must be not null");
		}
		try
		{
			Constructor<? extends Wizard> ctor = wizardClass.getConstructor(String.class);
			Wizard wizard = ctor.newInstance("wizard");
			add(wizard);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	public <C extends Wizard> WizardPage(Class<C> wizardClass,Class responsepage,PageParameters pageparameters)
	{
		if (wizardClass == null)
		{
			throw new IllegalArgumentException("argument wizardClass must be not null");
		}
		try
		{
			Constructor<? extends Wizard> ctor = wizardClass.getConstructor(String.class,Class.class,PageParameters.class);
			Wizard wizard = ctor.newInstance("wizard",responsepage,pageparameters);
			add(wizard);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
