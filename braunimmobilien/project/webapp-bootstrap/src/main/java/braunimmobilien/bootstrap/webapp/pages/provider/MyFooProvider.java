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
package braunimmobilien.bootstrap.webapp.pages.provider;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
/**
 * A provider of {@link MyFoo}s.
 * 
 * For simplicity all foos are kept as class members, in a real world scenario these would be
 * fetched from a database. If {@link MyFoo}s were {@link Serializable} you could of course just keep
 * references in instance variables.
 * 
 * @see #model(MyFoo)
 * 
 * @author Sven Meier
 */
public class MyFooProvider implements ITreeProvider<MyFoo>
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 */
	public MyFooProvider()
	{
	}
	public MyFooProvider(String angnr)
	{
	}
	/**
	 * Nothing to do.
	 */
	@Override
	public void detach()
	{
	}

	@Override
	public Iterator<MyFoo> getRoots()
	{		
	return WicketApplication.get().foos.iterator();
	}

	@Override
	public boolean hasChildren(MyFoo foo)
	{
		return foo.getTreeParent() == null || !foo.getFoos().isEmpty();
	}

	@Override
	public Iterator<MyFoo> getChildren(final MyFoo foo)
	{
		return foo.getFoos().iterator();
	}

	/**
	 * Creates a {@link FooModel}.
	 */
	@Override
	public IModel<MyFoo> model(MyFoo foo)
	{
		return new FooModel(foo);
	}

	/**
	 * A {@link Model} which uses an id to load its {@link MyFoo}.
	 * 
	 * If {@link MyFoo}s were {@link Serializable} you could just use a standard {@link Model}.
	 * 
	 * @see #equals(Object)
	 * @see #hashCode()
	 */
	private static class FooModel extends LoadableDetachableModel<MyFoo>
	{
		private static final long serialVersionUID = 1L;

		private final String id;

		public FooModel(MyFoo foo)
		{
			super(foo);

			id = foo.getTreeid();
		}

		@Override
		protected MyFoo load()
		{
			return WicketApplication.getFoo(id);
		}

		/**
		 * Important! Models must be identifyable by their contained object.
		 */
		@Override
		public boolean equals(Object obj)
		{
			if (obj instanceof FooModel)
			{
				return ((FooModel)obj).id.equals(id);
			}
			return false;
		}

		/**
		 * Important! Models must be identifyable by their contained object.
		 */
		@Override
		public int hashCode()
		{
			return id.hashCode();
		}
	}
}
