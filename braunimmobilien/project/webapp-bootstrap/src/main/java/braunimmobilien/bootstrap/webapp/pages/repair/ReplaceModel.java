package braunimmobilien.bootstrap.webapp.pages.repair;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import braunimmobilien.model.*;
import org.apache.wicket.request.mapper.parameter.PageParameters;
@SuppressWarnings("serial")
public class ReplaceModel implements Serializable {
private String countryoriginal;		
private String countrytoreplace;
			
private String townoriginal;		
private String  towntoreplace;

private String 	streetoriginal;		
private String streettoreplace;

private String 	objectoriginal;		
private String  objecttoreplace;

private String 	personoriginal;		
private String persontoreplace;
private String countryoriginaltext;		
private String countrytoreplacetext;
			
private String townoriginaltext;		
private String  towntoreplacetext;

private String 	streetoriginaltext;		
private String streettoreplacetext;

private String 	objectoriginaltext;		
private String  objecttoreplacetext;

private String 	personoriginaltext;		
private String persontoreplacetext;
public ReplaceModel() {
	countryoriginal="countryoriginal";		
	countrytoreplace="countrytoreplace";
	townoriginal="townoriginal";		
	towntoreplace="towntoreplace";

streetoriginal="streetoriginal";		
	streettoreplace="streettoreplace";

objectoriginal="objectoriginal";		
	objecttoreplace="objecttoreplace";

	personoriginal="personoriginal";		
	persontoreplace="persontoreplace";
  }



public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[ReplaceModel countryoriginal = ")
		.append(countryoriginal)
		.append(" countrytoreplace = ")
		.append(countrytoreplace)
		.append("]");
	return b.toString();


}



public String getCountryoriginal() {
	return countryoriginal;
}



public void setCountryoriginal(String countryoriginal) {
	this.countryoriginal = countryoriginal;
}



public String getCountrytoreplace() {
	return countrytoreplace;
}



public void setCountrytoreplace(String countrytoreplace) {
	this.countrytoreplace = countrytoreplace;
}



public String getTownoriginal() {
	return townoriginal;
}



public void setTownoriginal(String townoriginal) {
	this.townoriginal = townoriginal;
}



public String getTowntoreplace() {
	return towntoreplace;
}



public void setTowntoreplace(String towntoreplace) {
	this.towntoreplace = towntoreplace;
}



public String getStreetoriginal() {
	return streetoriginal;
}



public void setStreetoriginal(String streetoriginal) {
	this.streetoriginal = streetoriginal;
}



public String getStreettoreplace() {
	return streettoreplace;
}



public void setStreettoreplace(String streettoreplace) {
	this.streettoreplace = streettoreplace;
}



public String getObjectoriginal() {
	return objectoriginal;
}



public void setObjectoriginal(String objectoriginal) {
	this.objectoriginal = objectoriginal;
}



public String getObjecttoreplace() {
	return objecttoreplace;
}



public void setObjecttoreplace(String objecttoreplace) {
	this.objecttoreplace = objecttoreplace;
}



public String getPersonoriginal() {
	return personoriginal;
}



public void setPersonoriginal(String personoriginal) {
	this.personoriginal = personoriginal;
}



public String getPersontoreplace() {
	return persontoreplace;
}



public void setPersontoreplace(String persontoreplace) {
	this.persontoreplace = persontoreplace;
}



public String getCountryoriginaltext() {
	return countryoriginaltext;
}



public void setCountryoriginaltext(String countryoriginaltext) {
	this.countryoriginaltext = countryoriginaltext;
}



public String getCountrytoreplacetext() {
	return countrytoreplacetext;
}



public void setCountrytoreplacetext(String countrytoreplacetext) {
	this.countrytoreplacetext = countrytoreplacetext;
}



public String getTownoriginaltext() {
	return townoriginaltext;
}



public void setTownoriginaltext(String townoriginaltext) {
	this.townoriginaltext = townoriginaltext;
}



public String getTowntoreplacetext() {
	return towntoreplacetext;
}



public void setTowntoreplacetext(String towntoreplacetext) {
	this.towntoreplacetext = towntoreplacetext;
}



public String getStreetoriginaltext() {
	return streetoriginaltext;
}



public void setStreetoriginaltext(String streetoriginaltext) {
	this.streetoriginaltext = streetoriginaltext;
}



public String getStreettoreplacetext() {
	return streettoreplacetext;
}



public void setStreettoreplacetext(String streettoreplacetext) {
	this.streettoreplacetext = streettoreplacetext;
}



public String getObjectoriginaltext() {
	return objectoriginaltext;
}



public void setObjectoriginaltext(String objectoriginaltext) {
	this.objectoriginaltext = objectoriginaltext;
}



public String getObjecttoreplacetext() {
	return objecttoreplacetext;
}



public void setObjecttoreplacetext(String objecttoreplacetext) {
	this.objecttoreplacetext = objecttoreplacetext;
}



public String getPersonoriginaltext() {
	return personoriginaltext;
}



public void setPersonoriginaltext(String personoriginaltext) {
	this.personoriginaltext = personoriginaltext;
}



public String getPersontoreplacetext() {
	return persontoreplacetext;
}



public void setPersontoreplacetext(String persontoreplacetext) {
	this.persontoreplacetext = persontoreplacetext;
}
}
