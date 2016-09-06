package braunimmobilien.bootstrap.webapp.pages;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Fragment;

import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Xtyp;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.service.TypeManager;
import braunimmobilien.service.XtypManager;
import braunimmobilien.util.ScoutUtil;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

import java.util.Date;

import org.apache.wicket.event.IEvent;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelectConfig;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.Stack;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UpdatableProgressBar;
import braunimmobilien.bootstrap.webapp.components.base.StateSelect;
import braunimmobilien.bootstrap.webapp.WicketApplication;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.annotation.mount.MountPath;

import braunimmobilien.model.Angebot;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapMultiSelect;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Map;
import java.util.TimeZone;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.ClientSideBootstrapTabbedPanel;
import braunimmobilien.bootstrap.webapp.components.basecss.ButtonGroups;
import braunimmobilien.service.AngebotManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.wicket.spring.injection.annot.SpringBean;
/**
 * The {@code ComponentsPage}
 *
 * @author miha
 */


public class CalendarPage extends BasePage {
	@SpringBean
    private AngebotManager angebotManager;
	@SpringBean
	private  TypeManager typeManager;
	@SpringBean
	private  EigentuemermusterManager eigentuemermusterManager;
	@SpringBean
		private  StrassenManager strassenManager;
	@SpringBean
	private  ObjektartManager objektartManager;
	@SpringBean
		private  ObjektManager objektManager;
	  @SpringBean
	private  ObjektsuchManager objektsuchManager;
	@SpringBean
		private PersonManager personManager;
	  @SpringBean
	private  ObjarttypManager objarttypManager;
	@SpringBean
    private XtypManager xtypManager;	
	@SpringBean
	private  OrteManager orteManager;
	@SpringBean
	protected ScoutManager scoutManager;
	
	private static 	  Calendar service = null;
	
	 private static final List<String> data = Lists.newArrayList(
	            "Scouttermine setzen","Termine verlagern"
	    );
	
transient	    HttpTransport httpTransport = new NetHttpTransport();
transient	    JsonFactory jsonFactory = new JacksonFactory();
	 private String key;
private String task;
private static Date date;	
private static Date date1;	
	
	
	

	

	private final String i18nJavaCode = "BootstrapSelectConfig config = new BootstrapSelectConfig();\n"
	            + "            config\n"
	            + "                    .withNoneSelectedText(\"My nothing selected\")\n"
	            + "                    .withNoResultText(\"My no results found\")\n"
	            + "                    .withCountSelectedText(\"My selected {0} from {1}\")\n"
	            + "                    .withMaxOptionsText(\"My limit ({n} {var} max)\",\n"
	            + "                            \"My group limit({n} {var} max)\",\n"
	            + "                            \"items\", \"item\");\n";



final BootstrapSelect<String> first =new BootstrapSelect<String>("default-live-search",new PropertyModel<String>(this,"task"),data).with(of(false).withLiveSearch(true));
transient GoogleAuthorizationCodeFlow flow=null;
final	 AjaxLink ajaxlink=new AjaxLink<Void>("update-default") {

    private static final long serialVersionUID = -3698659776363173730L;

    @Override
    public void onClick(AjaxRequestTarget target) {
    System.err.println(task);
    System.err.println(date);
    	String merkmal="Scoutannonce";
 	 	String pageToken = null;
    	int i=0;
    	try{
    do {++i;
    	int j=0;
    	int k=0;
    	  System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN Jahr "+ date.getYear()+" Monat "+(date.getMonth()-1)+" Tag : "+date.getDay());
    	 java.util.Calendar calendar = new GregorianCalendar();
    	
    	 java.util.Calendar cal = new GregorianCalendar();
    	    cal.setTime(date);
    	    int year = cal.get(java.util.Calendar.YEAR);
    	    int month = cal.get(java.util.Calendar.MONTH);
    	    int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
    	    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN Jahr "+ year+" Monat "+(month)+" Tag : "+day);
    	    java.util.Calendar cal1 = new GregorianCalendar();
    	    cal1.setTime(date1);
    	    int year1 = cal1.get(java.util.Calendar.YEAR);
    	    int month1 = cal1.get(java.util.Calendar.MONTH);
    	    int day1 = cal1.get(java.util.Calendar.DAY_OF_MONTH);
    	    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN Jahr "+ year1+" Monat "+(month1)+" Tag : "+day1);
    	    
    	    calendar.set(java.util.Calendar.YEAR, year);
    	calendar.set(java.util.Calendar.MONTH, month); // 11 = december
    	calendar.set(java.util.Calendar.DAY_OF_MONTH, day); // christmas eve
    	calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
    	   com.google.api.client.util.DateTime minTime = new  com.google.api.client.util.DateTime(calendar.getTime(), TimeZone
    		          .getDefault());
    	   calendar.add(java.util.Calendar.HOUR_OF_DAY, 24);
    	   com.google.api.client.util.DateTime maxTime = new  com.google.api.client.util.DateTime(calendar.getTime(), TimeZone
    		          .getDefault());
   
    	   Events events = service.events().list("primary").setTimeMax(maxTime).setTimeMin(minTime).setPageToken(pageToken).execute();
    	   j=0;
    	List<Event> items = events.getItems();
    	  	for (Event event : items) {
    	  		 ++j;
    	    	if(event.getStart()!=null&&event.getEnd()!=null){
    	    com.google.api.client.util.DateTime start=event.getStart().getDateTime();
    	    com.google.api.client.util.DateTime end=event.getEnd().getDateTime();
    	    		if(start!=null&&end!=null){
    	    Date startdate=new Date(start.getValue());
    	    java.util.Calendar calsearch = java.util.Calendar.getInstance();
    	
    	    calsearch.setTime(startdate);
    	    System.err.println("vor "+calsearch.get(java.util.Calendar.YEAR)+"  "+calsearch.get(java.util.Calendar.MONTH)+"  "+calsearch.get(java.util.Calendar.DAY_OF_MONTH));
			if(calsearch.get(java.util.Calendar.YEAR)==year&&calsearch.get(java.util.Calendar.MONTH)==month&&calsearch.get(java.util.Calendar.DAY_OF_MONTH)==day){
				System.err.println("zwischen "+calsearch.get(java.util.Calendar.YEAR)+"  "+calsearch.get(java.util.Calendar.MONTH)+"  "+calsearch.get(java.util.Calendar.DAY_OF_MONTH));
				if(event.getSummary().contains(merkmal)){
					System.err.println("nach "+calsearch.get(java.util.Calendar.YEAR)+"  "+calsearch.get(java.util.Calendar.MONTH)+"  "+calsearch.get(java.util.Calendar.DAY_OF_MONTH));
  				  java.util.Calendar calstart = java.util.Calendar.getInstance();
  		/*calstart.set(java.util.Calendar.YEAR, 2015);
          	calstart.set(java.util.Calendar.MONTH, 2); 
          	calstart.set(java.util.Calendar.DAY_OF_MONTH, 2);*/
          	calstart.set(java.util.Calendar.YEAR, year1);
          	calstart.set(java.util.Calendar.MONTH,month1); 
          	calstart.set(java.util.Calendar.DAY_OF_MONTH,day1);
          	calstart.set(java.util.Calendar.HOUR, calsearch.get(java.util.Calendar.HOUR));
          	calstart.set(java.util.Calendar.MINUTE, calsearch.get(java.util.Calendar.MINUTE));
          	   com.google.api.client.util.DateTime newstartdate = new  com.google.api.client.util.DateTime(calstart.getTime(), TimeZone
  	    		          .getDefault());
          	   EventDateTime eventStartDateTime=new EventDateTime();
   		      eventStartDateTime.setDateTime(newstartdate);
          	   event.setStart(eventStartDateTime);
          	   calstart.add(java.util.Calendar.MINUTE, 30);
          	  
          	  
          	
  	            	 com.google.api.client.util.DateTime newenddate = new  com.google.api.client.util.DateTime(calstart.getTime(), TimeZone
  	    	    		          .getDefault());
  	            	   EventDateTime eventEndDateTime=new EventDateTime();
   	    		      eventEndDateTime.setDateTime(newenddate);
  	            	   event.setEnd(eventEndDateTime);
  	            	  

  			Event updatedEvent = service.events().update("primary", event.getId(), event).execute();
  			System.out.println(updatedEvent.getUpdated());
  
    			++k;
    		
            		}}}}}
    	  System.out.println("Anzahl  Events :"+j+" found : "+k);
    	  pageToken = events.getNextPageToken();
    	 
    	 }while (pageToken != null);
System.out.println("Anzahl  Tokens :"+i);
    
    	}
    catch(Exception e){
    	System.out.println("Exception "+e);
   
    e.printStackTrace();
    }
    	target.add(first);
    }
};

public CalendarPage(PageParameters parameters) {
        super(parameters);
        service = ((WicketApplication)Application.get()).getCalendarService();
        Form form=new Form("intform");
  
        first.setVisible(false);
        first.setOutputMarkupPlaceholderTag(true);
        add(form);
    	// form.add(first );
    	  ajaxlink.setOutputMarkupPlaceholderTag(true);
          form.add(ajaxlink); 
          ajaxlink.setVisible(true);
         
        
          final	 WebMarkupContainer vermarkup = new WebMarkupContainer("vermarkup");
          vermarkup.setOutputMarkupPlaceholderTag(true);
          final  DateTextField  datelanguagefield= new DateTextField("language", new PropertyModel<Date>(this,"date"),new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage("us"));
          final  DateTextField  datelanguagefield1= new DateTextField("language1", new PropertyModel<Date>(this,"date1"),new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage("us"));
         // form.add(vermarkup);
          form.add(datelanguagefield1);
          form.add(datelanguagefield);
          vermarkup.setVisible(true);
          datelanguagefield.add(new AjaxFormComponentUpdatingBehavior("onchange")
     	 {
     	     @Override
     	     protected void onUpdate(AjaxRequestTarget target)
     	     {	
     	     target.add(ajaxlink);
     	     }
     	 });	
          datelanguagefield1.add(new AjaxFormComponentUpdatingBehavior("onchange")
      	 {
      	     @Override
      	     protected void onUpdate(AjaxRequestTarget target)
      	     {	
      	     target.add(ajaxlink);
      	     }
      	 });	
          first.add(new AjaxFormComponentUpdatingBehavior("onchange")
       	 {
       	     @Override
       	     protected void onUpdate(AjaxRequestTarget target)
       	     {	
       	    	 System.err.println("Weitergekommen");
       	    	 vermarkup.setVisible(true);
       	    	target.add(vermarkup);
       	    	ajaxlink.setVisible(true);
       	   	target.add(ajaxlink);
       	     }
       	 });
         
    }


    

   
   

    @Override
    protected boolean hasNavigation() {
        return true;
    }
   
 
   
   PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
	        PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
   
  
  
    
    private BootstrapSelectConfig of(boolean i18n) {
        BootstrapSelectConfig config = new BootstrapSelectConfig();
        if (i18n) {
           
            config
                    .withNoneSelectedText("My nothing selected")
                    .withNoResultText("My no results found")
                    .withCountSelectedText("My selected {0} from {1}")
                    .withMaxOptionsText("My limit ({n} {var} max)",
                            "My group limit({n} {var} max)",
                            "items", "item");
        }
        return config;
    }
    private Component newDatePicker(String markupId, DateTextFieldConfig dateTextFieldConfig) {
        return new DateTextField(markupId, dateTextFieldConfig);
    }
    public static Date getDate() {
    	return date;
    }

    public static void setDate(Date date) {
    	CalendarPage.date = date;
    } 
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	 public String getTask() {
			return task;
		}

		public void setTask(String task) {
			this.task = task;
		}
		public static Date getDate1() {
			return date1;
		}
		public static void setDate1(Date date1) {
			CalendarPage.date1 = date1;
		}
}
