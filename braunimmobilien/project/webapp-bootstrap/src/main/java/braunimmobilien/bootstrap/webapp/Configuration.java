package braunimmobilien.bootstrap.webapp;
import java.util.Map;
public class Configuration {

Map progress;
String cocoonURL;
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

public String getCocoonURL() {
	return cocoonURL;
}

public void setCocoonURL(String cocoonURL) {
	this.cocoonURL = cocoonURL;
}

public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Configuration cocoonURL = ")
		.append(this.getCocoonURL())
		.append("]");
	return b.toString();
}

}

