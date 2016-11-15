package braunimmobilien.bootstrap.webapp;
import java.util.Map;
public class Configuration {

Map progress;

String googleAuthenticationJson;





public String getGoogleAuthenticationJson() {
	return googleAuthenticationJson;
}

public void setGoogleAuthenticationJson(String googleAuthenticationJson) {
	this.googleAuthenticationJson = googleAuthenticationJson;
}



String nachweiseEmail;
public String getNachweiseEmail() {
	return nachweiseEmail;
}

public void setNachweiseEmail(String nachweiseEmail) {
	this.nachweiseEmail = nachweiseEmail;
}



String nachweisePDF;
public String getNachweisePDF() {
	return nachweisePDF;
}

public void setNachweisePDF(String nachweisePDF) {
	this.nachweisePDF = nachweisePDF;
}



Map simpleAccessList;
Map switchTabOn;
Map newPageParameters;
Map searchReturnClass;
Map switchoffTable;

public Map getProgress() {
	return progress;
}

public void setProgress(Map progress) {
	this.progress = progress;
}

public Map getSwitchTabOn() {
	return switchTabOn;
}

public void setSwitchTabOn(Map switchTabOn) {
	this.switchTabOn = switchTabOn;
}

public Map getNewPageParameters() {
	return newPageParameters;
}

public void setNewPageParameters(Map newPageParameters) {
	this.newPageParameters = newPageParameters;
}

public Map getSearchReturnClass() {
	return searchReturnClass;
}

public void setSearchReturnClass(Map searchReturnClass) {
	this.searchReturnClass = searchReturnClass;
}

public Map getSwitchoffTable() {
	return switchoffTable;
}

public void setSwitchoffTable(Map switchoffTable) {
	this.switchoffTable = switchoffTable;
}

public Map getSimpleAccessList() {
	return simpleAccessList;
}

public void setSimpleAccessList(Map simpleAccessList) {
	this.simpleAccessList = simpleAccessList;
}



public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Configuration nachweisePDF = ")
		.append(this.getNachweisePDF())
		.append("]");
	return b.toString();
}

}

