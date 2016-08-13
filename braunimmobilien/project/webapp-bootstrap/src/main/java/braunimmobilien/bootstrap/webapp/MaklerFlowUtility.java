package braunimmobilien.bootstrap.webapp;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;

import braunimmobilien.model.Objekte;

import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import braunimmobilien.model.BaseObject;
public class MaklerFlowUtility {

	public static boolean fits(PageParameters actual,PageParameters reference,boolean total){
		Iterator it=null;
		if (total){it=actual.getNamedKeys().iterator();
		while (it.hasNext()){
			String key=(String)it.next();
			if (reference.getPosition(key)<0) return false;
			if(actual.get(key).toString().equals("null")){	
			if (!reference.get(key).toString().equals("null")) return false;
			}
			else{
				if (reference.get(key).toString().equals("null")) return false;
			}
		}	
		}
		it=reference.getNamedKeys().iterator();
		while (it.hasNext()){
			String key=(String)it.next();	
			if (actual.getPosition(key)<0) return false;	
			if(reference.get(key).toString().equals("null")){	
				if (!actual.get(key).toString().equals("null")) return false;			
			}
			else{
				if (actual.get(key).toString().equals("null")) return false;	
				
			}
		}
		return true;
	
	}
	
	public void search(Object object,Class responsepage,PageParameters actual,BaseObject baseobject){
		Method[] methods=object.getClass().getMethods();
		for(int i=0; i<methods.length; i++) {
			if(methods[i].getName().startsWith("makeFlowAction"))
				try{	
					System.err.println("-----------------------------------------"+methods[i].getName()+" "+methods[i].getParameterTypes().length);
			
			methods[i].invoke(object,responsepage,actual,baseobject);
		} catch (IllegalAccessException x) {
		    x.printStackTrace();
		}
		 catch (InvocationTargetException x) {
		    x.printStackTrace();
		}	
			 catch (IllegalArgumentException x) {
				   x.printStackTrace();
				
			 }
			
		}
		
	}
	public BreadCrumbPanel searchBreadcrumb(Object object,String componentId,IBreadCrumbModel breadCrumbModel,Class responsepage,PageParameters actual,BaseObject baseobject){
		Method[] methods=object.getClass().getMethods();
		for(int i=0; i<methods.length; i++) {
			if(methods[i].getName().startsWith("makeFlowActionBreadcrumb"))
				try{	
					System.err.println("-----------------------------------------"+methods[i].getName()+" "+methods[i].getParameterTypes().length);
					BreadCrumbPanel breadCrumbPanel =(BreadCrumbPanel) methods[i].invoke(object,componentId,breadCrumbModel,responsepage,actual,baseobject);
					if (breadCrumbPanel!=null) return breadCrumbPanel;
		} catch (IllegalAccessException x) {
		    x.printStackTrace();
		}
		 catch (InvocationTargetException x) {
		    x.printStackTrace();
		}	
			 catch (IllegalArgumentException x) {
				   x.printStackTrace();
				
			 }
			
		}
		System.exit(5);
		return null;
	}	
}




