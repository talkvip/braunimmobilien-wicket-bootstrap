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
package braunimmobilien.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "Zuordangobj")
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Angobjzuord extends BaseObject implements Serializable,Identifiable<Long>
{
	static final long serialVersionUID = 3832626162173359411L;
	private Long id;
	private boolean aktuell;
	private Objekte objekte;
	@XmlTransient private Angebot angebot;


	public Angobjzuord()
	{
		aktuell=true;
//		objekte=new Objekte();
	}

	public Angobjzuord(Angobjzuord param){
	id=param.getId();
	aktuell=param.getAktuell();
	objekte=param.getObjekte();
	angebot=param.getAngebot();
		aktuell=true;
		objekte=new Objekte();
	}
	
	public Angobjzuord(final String id)
	{
		setId(new Long(id));
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
@Column(name="ZuordNr",nullable = false, unique = true)
@Field(name="ZuordNr")
	public Long getId()
	{
		return id;
	}
//	@XmlTransient 
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)//(fetch = FetchType.LAZY)
	@JoinColumn(name = "ObjNr", insertable=false, updatable=false, nullable=false)
	public Objekte getObjekte()
	{
		return objekte;
	}
	@XmlTransient 
	@ManyToOne(fetch=FetchType.LAZY)//(fetch = FetchType.LAZY)
	@JoinColumn(name="AngNr", insertable=false, updatable=false, nullable=false)
	public Angebot getAngebot()
	{
		return angebot;
	}

	@Column(name="Aktuell",nullable = false, columnDefinition = "int(11)", unique = false)
	@Field()
	public boolean getAktuell()
	{
		return aktuell;
	}


	public void setId(Long id)
	{
		this.id = id;
	}

	public void setAktuell(boolean param)
	{
		aktuell = param;
	}

	public void setObjekte(Objekte objekte)
	{
		this.objekte = objekte;
	}

	public boolean isAktuell()
	{
		return aktuell;
	}


	public void setAngebot(Angebot angebot)
	{
		this.angebot = angebot;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Angobjzuord))
		{
			return false;
		}

		final Angobjzuord angobjzuord = (Angobjzuord)o;
		if (angebot == null && angobjzuord.getAngebot() != null)
			return false;
		if (angebot != null && angobjzuord.getAngebot() == null)
			return false;
		if (objekte == null && angobjzuord.getObjekte() != null)
			return false;
		if (objekte != null && angobjzuord.getObjekte() == null)
			return false;
		if (angebot == null && angobjzuord.getAngebot() != null)
			return false;
		if (angebot == null && angobjzuord.getAngebot() == null)
		{
			if (objekte == null && angobjzuord.getObjekte() == null)
				return true;
			if (objekte.getId().longValue() != angobjzuord.getObjekte().getId().longValue())
				return false;
			return true;
		}
		if (objekte == null && angobjzuord.getObjekte() == null)
		{
			if (angebot == null && angobjzuord.getAngebot() == null)
				return true;
			if (!angebot.getId().equals(angobjzuord.getAngebot().getId()))
				return false;
			return true;
		}
		if ((!angebot.getId().equals(angobjzuord.getAngebot().getId())) ||
			(objekte.getId().longValue() != angobjzuord.getObjekte().getId().longValue()))
			return false;

		return true;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		if ((angebot != null) && (objekte == null))
			return (angebot.getId() + "/null").hashCode();
		if ((angebot == null) && (objekte != null))
			return ("null/" + objekte.getId()).hashCode();
		if ((angebot == null) && (objekte == null))
			return 0;
		return ("" + angebot.getId() + "/" + objekte).hashCode();

	}


	@Override
	public String toString()
	{

		StringBuilder b = new StringBuilder();
		b.append("[Angobjzuord id = ")
			.append(getId())
			.append(", aktuell = ")
			.append(aktuell)
			.append(", objekte = ")
			.append(objekte)
			.append(", angebot = ")
			.append(angebot)
			.append("]");
		return b.toString();
	}
	public String toReducedString()
	{

		StringBuilder b = new StringBuilder();
		b.append("[Angobjzuord id = ")
			.append(getId())
			.append(", aktuell = ")
			.append(aktuell)
			.append(", objekte = ")
			.append(objekte.toStrassenReducedString())
			.append("]");
		return b.toString();
	}

}
