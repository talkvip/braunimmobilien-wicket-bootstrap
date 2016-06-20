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
package braunimmobilien.bootstrap.webapp.pages.auth;


import java.io.Serializable;
import org.apache.wicket.markup.html.border.BoxBorder;
import org.apache.wicket.markup.html.basic.Label;
import braunimmobilien.bootstrap.webapp.pages.auth.SignOutPage;
import braunimmobilien.bootstrap.webapp.pages.WithoutBasePage;
/**
 * Trivial page.
 * 
 * @author Jonathan Locke
 */
public class UserPage  extends WithoutBasePage implements Serializable
{
	
	/**
	 * Constructor
	 */
	public UserPage()
	{  UserPanel subject= new UserPanel("subject",SignOutPage.class);

	add(subject);
	
	}
}