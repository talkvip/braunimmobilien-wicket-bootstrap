package braunimmobilien.bootstrap.webapp.pages.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import braunimmobilien.bootstrap.webapp.pages.model.AngebotModel;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angstatus;
import braunimmobilien.service.AngebotManager;
public class SortableAngebotDataProvider extends SortableDataProvider {
	@SpringBean
	private AngebotManager angebotManager;
	private Angstatus angstatus=null;
	private String verkaufsart="";
	 List angebotes = new ArrayList();
    public SortableAngebotDataProvider(Angstatus angstatus,String verkaufsart) {
    	this.angstatus=angstatus;
    	this.verkaufsart=verkaufsart;
        
        setSort("id", SortOrder.ASCENDING);
        Injector.get().inject(this);
        List angebote = angebotManager.getAngebote();
        Iterator it=angebote.iterator();
        while(it.hasNext()){
        	Angebot angebot=(Angebot)it.next();
        	if (angstatus.getId().longValue()!=0&&angebot.getAngstatus().getId().longValue()==angstatus.getId().longValue()&&angebot.getId().startsWith(verkaufsart)){
        	angebotes.add(angebot);		
        	}
        	if (angstatus.getId().longValue()==0&&angebot.getId().startsWith(verkaufsart)){
            	angebotes.add(angebot);		
            	}
        }
    }
    
    public SortableAngebotDataProvider() {
        // default sort
        setSort("id", SortOrder.ASCENDING);
    }
public class angebotComparator implements Comparator<Angebot> {
		
	    public int compare(Angebot angebot1, Angebot angebot2) {
	    	if(angebot1.getId().substring(0,1).equals(angebot2.getId().substring(0,1))) {
	    		Long long1=new Long(0);
	    		try{long1=new Long(angebot1.getId().substring(2).trim());}
	    		catch(java.lang.NumberFormatException e){}
	    		Long long2=new Long(0);
	    		try{long2=new Long(angebot2.getId().substring(2).trim());}
	    		catch(java.lang.NumberFormatException e){}
	    		return long1.compareTo(long2);
	    	}
	        return angebot1.getId().substring(0,1).compareTo(angebot2.getId().substring(0,1));
	     
	}
	}
	
    /**
     * @todo implement paging in the database, this is just stubbed out for now
     */
    @SuppressWarnings("unchecked")
    public Iterator iterator(long first, long count) {
       
        
        if (first > 0) {
            angebotes = angebotes.subList(new Long(first).intValue(), new Long(first + count).intValue());
        }
        
        SortParam sp = getSort();
        
        if (sp != null) {
            String sortColumn = (String)sp.getProperty();
            Comparator comparator=null;
           
            if(sortColumn.equals("id"))
            comparator = new angebotComparator();
    
            if (!sp.isAscending()) {
                comparator = new ReverseComparator(comparator);
            }
            
            Collections.sort(angebotes, comparator);
        }

        return angebotes.iterator();
    }

    public IModel model(Object object) {
        return new AngebotModel((Angebot) object, angebotManager);
    }

    public long size() {

       return angebotes.size();
    	
    }
    public void  setAngstatus(Angstatus angstatus) {
    	this.angstatus=angstatus;
    	angebotes = new ArrayList();
    	   List angebote = angebotManager.getAngebote();
           Iterator it=angebote.iterator();
           while(it.hasNext()){
           	Angebot angebot=(Angebot)it.next();
           	if (angstatus.getId().longValue()!=0&&angebot.getAngstatus().getId().longValue()==angstatus.getId().longValue()&&angebot.getId().startsWith(verkaufsart)){
           	angebotes.add(angebot);		
           	}
           	if (angstatus.getId().longValue()==0&&angebot.getId().startsWith(verkaufsart)){
               	angebotes.add(angebot);		
               	}
           }
     }
    public void  setVerkaufsart(String verkaufsart) {
    		this.verkaufsart= verkaufsart;
    		angebotes = new ArrayList();
    	   List angebote = angebotManager.getAngebote();
           Iterator it=angebote.iterator();
           while(it.hasNext()){
           	Angebot angebot=(Angebot)it.next();
           	if (angstatus.getId().longValue()!=0&&angebot.getAngstatus().getId().longValue()==angstatus.getId().longValue()&&angebot.getId().startsWith(verkaufsart)){
           	angebotes.add(angebot);		
           	}
           	if (angstatus.getId().longValue()==0&&angebot.getId().startsWith(verkaufsart)){
               	angebotes.add(angebot);		
               	}
           }
     }
}
