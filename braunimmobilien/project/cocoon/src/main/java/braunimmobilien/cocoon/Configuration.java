package braunimmobilien.cocoon;
import java.util.Map;
public class Configuration {
String	resourcepath;
public String getResourcepath() {
	return resourcepath;
}

public void setResourcepath(String resourcepath) {
	this.resourcepath = resourcepath;
}
String docdir;
String tel;
String homepage;
String fanpage;
public String getFanpage() {
	return fanpage;
}

public void setFanpage(String fanpage) {
	this.fanpage = fanpage;
}
String userConfigPath;
public String getUserConfigPath() {
	return userConfigPath;
}

public void setUserConfigPath(String userConfigPath) {
	this.userConfigPath = userConfigPath;
}
String email;
String firma;
String strasse;
String ort;
String name;
Map progress;
public Map getProgress() {
	return progress;
}

public void setProgress(Map progress) {
	this.progress = progress;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getHomepage() {
	return homepage;
}

public void setHomepage(String homepage) {
	this.homepage = homepage;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getFirma() {
	return firma;
}

public void setFirma(String firma) {
	this.firma = firma;
}

public String getStrasse() {
	return strasse;
}

public void setStrasse(String strasse) {
	this.strasse = strasse;
}

public String getOrt() {
	return ort;
}

public void setOrt(String ort) {
	this.ort = ort;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDocdir() {
	return docdir;
}

public void setDocdir(String docdir) {
	this.docdir = docdir;
}
public String toString()
{
	
	String email;
	String firma;
	String strasse;
	String ort;
	String name;
	Map progress;
	
	
 StringBuilder b = new StringBuilder();
	b.append("[Configuration resourcepath= ")
		.append(this.getResourcepath())
		.append(", docdir = ")
	.append(this.getDocdir())
	.append(", tel = ")
	.append(this.getTel())
		.append(", homepage = ")
	.append(this.getHomepage())
	.append(", email = ")
	.append(this.getEmail())
	.append(", firma = ")
	.append(this.getFirma())
	.append(", strasse = ")
	.append(this.getFirma())
		.append("]");
	return b.toString();
}

}

