package braunimmobilien.bootstrap.webapp.pages.subjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import org.apache.wicket.markup.html.link.PopupSettings;
import braunimmobilien.bootstrap.webapp.EntityModel;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.AttributeModifier;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import braunimmobilien.bootstrap.webapp.MaklerFlow;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
import braunimmobilien.bootstrap.webapp.pages.provider.MyFooProvider;
import braunimmobilien.bootstrap.webapp.pages.wizard.NewLandWizard;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebMarkupContainer;
import braunimmobilien.model.Subjects;
import braunimmobilien.model.Links;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.SubjectsManager;
import braunimmobilien.service.LinksManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.EigentuemertypManager;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.tree.Tree;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */

public class SubjectsPage extends BasePage {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private SubjectsManager subjectsManager;
	@SpringBean
    private LinksManager linksManager;
	 private Subjects selectedSubject;
	 private Links selectedLink;
	 IChoiceRenderer subjectschoice=        		new IChoiceRenderer() {

	  	    public Object getDisplayValue(Object object){
			Subjects subject= (Subjects)object;
	         return subject.getSubject();
	  	    	
	  	    }

	  	    public String getIdValue(Object object,int index)
	  	    {Subjects subject= (Subjects)object;
	  	        return subject.getId().toString();
	  	    }
	}; 
	
	IModel<List<? extends Subjects>> makeChoicesSubjects = new AbstractReadOnlyModel<List<? extends Subjects>>()
	        {
	            @Override
	            public List<Subjects> getObject()
	            { List<Subjects> subjectlist=new  ArrayList<Subjects>(); 
	            	
	            	Iterator subjectiterator=subjectsManager.getSubjectses().iterator();
	            while(subjectiterator.hasNext()){
	            	Subjects subject=(Subjects)subjectiterator.next();
	            	
	            	subjectlist.add(subject);
	            }
	               
	                return subjectlist;
	            }

	        };
	        
	        IChoiceRenderer linkchoice=        		new IChoiceRenderer() {

	      	    public Object getDisplayValue(Object object){
	    		Links link= (Links)object;
	             return link.getBeschreibung();
	      	    	
	      	    }

	      	    public String getIdValue(Object object,int index)
	      	    {Links link= (Links)object;
	      	        return link.getId().toString();
	      	    }
	    }; 
	        
	        IModel<List<? extends Links>> makeChoiceLinks = new AbstractReadOnlyModel<List<? extends Links>>()
	    	        {
	    	            @Override
	    	            public List<Links> getObject()
	    	            { List<Links> linkslist=new  ArrayList<Links>(); 
	    	          if(selectedSubject!=null){
	    	            Iterator linkiterator=linksManager.getLinkses().iterator();
	    	            	while(linkiterator.hasNext()){
	    	            	Links link=(Links)linkiterator.next();
	    	            	if(link.getSubject().getId().longValue()==selectedSubject.getId().longValue()){
	    	            	
	    	            linkslist.add(link);}}
	    	            } 
	    	          return linkslist;
	    	            }
	    	        };
	       



public SubjectsPage()
	
	{ super();
	  BootstrapForm  bootstrapForm = new BootstrapForm("form");
	  add(bootstrapForm);
	  
	
	  
	  
	  
	  
	  final NotificationPanel feedback = new NotificationPanel("feedback");
		add(feedback);
		 List<Object> l1 = new ArrayList<Object>();
			Iterator it=subjectsManager.getSubjectses().iterator();
			while(it.hasNext()){
				Subjects subjects=(Subjects)it.next();	
				 List<Object> l2 = new ArrayList<Object>();
				 l2.add(subjects);
				 Iterator its=linksManager.getLinkses().iterator();
				 while(its.hasNext()){
						Links links=(Links)its.next();
						if(links.getSubject().getId().longValue()==subjects.getId().longValue()){
						l2.add(links);}}
			
      l1.add(l2);}
			TreeModel treeModel = convertToTreeModel(l1);
			
			final Tree tree = new Tree("tree", treeModel)
			{
				@Override
				protected String renderNode(TreeNode node)
				{
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)node;
					Object userObject = treeNode.getUserObject();
					if (userObject instanceof Subjects)  return ((Subjects) userObject).getSubject();
					
													if (userObject instanceof Links) {  return ((Links)userObject).getBeschreibung();}
													if (userObject instanceof String) {  return ((String)userObject);}
					else return "<sub>";
				}
				 @Override
				 protected void onNodeLinkClicked(AjaxRequestTarget target, TreeNode node) { 
					if (((DefaultMutableTreeNode) node).getUserObject() instanceof Links)	
				getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(((Links)((DefaultMutableTreeNode) node).getUserObject()).getInternetadresse()));
					}		
			};
			// disable ajax links in this example
//			tree.setLinkType(LinkType.REGULAR);
			bootstrapForm.add(tree);
			
			bootstrapForm.add(new AjaxLink<Void>("expandAll")
					{
						@Override
						public void onClick(AjaxRequestTarget target)
						{
							 tree.getTreeState().expandAll();
							tree.updateTree(target);
						}
					});

			bootstrapForm.add(new AjaxLink<Void>("collapseAll")
					{
						@Override
						public void onClick(AjaxRequestTarget target)
						{
							tree.getTreeState().collapseAll();
							tree.updateTree(target);
						}
					});

			bootstrapForm.add(new AjaxLink<Void>("switchRootless")
					{
						@Override
						public void onClick(AjaxRequestTarget target)
						{
							tree.setRootLess(!tree.isRootLess());
							tree.updateTree(target);
						}
					});
			
			
			
      final DropDownChoice<Subjects> subject = new DropDownChoice<Subjects>("subject",
              new PropertyModel<Subjects>(this, "selectedSubject"), makeChoicesSubjects,subjectschoice);
      bootstrapForm.add(subject);
      final DropDownChoice<Links> link = new DropDownChoice<Links>("link",
              new PropertyModel<Links>(this, "selectedLink"), makeChoiceLinks,linkchoice);   
      link.setOutputMarkupId(true);
      bootstrapForm.add(link);
     
    
   
      
     
     
    

		
	final Button editLinksButton=new Button("editLinksButton")
		{
			@Override
			public void onSubmit()
			{PageParameters pars = new PageParameters();
			pars.add("linksid", new Long(selectedLink.getId().toString()));

				// just set a new instance of the page
				setResponsePage(SubjectsPage.class,pars);
			}
		};  
		bootstrapForm.add(editLinksButton);
		
		
		 
				
			final Button insertLinksButton=new Button("insertLinksButton")
				{
					@Override
					public void onSubmit()
					{PageParameters pars = new PageParameters();
					pars.add("linksid", new Long(0));
					pars.add("subjectid", new Long(selectedSubject.getId().toString()));
						// just set a new instance of the page
						setResponsePage(SubjectsPage.class,pars);
					}
				};  
				bootstrapForm.add(insertLinksButton);
		
		
		
		  link.add(new AjaxFormComponentUpdatingBehavior("onchange")
	         {
	             @Override
	             protected void onUpdate(AjaxRequestTarget target)
	             {
	                 target.add(editLinksButton);
	                 target.add(insertLinksButton);
	             }
	         });
		  final Button editSubjectsButton=new Button("editSubjectsButton")
			{
				@Override
				public void onSubmit()
				{PageParameters pars = new PageParameters();
				pars.add("id", new Long(selectedSubject.getId().toString()));

					// just set a new instance of the page
					setResponsePage(SubjectsPage.class,pars);
				}
			};  
			bootstrapForm.add(editSubjectsButton);
			
			
			 
					
				final Button insertSubjectsButton=new Button("insertSubjectsButton")
					{
						@Override
						public void onSubmit()
						{PageParameters pars = new PageParameters();
						pars.add("id", new Long(0));
						
							setResponsePage(SubjectsPage.class,pars);
						}
					};  
					bootstrapForm.add(insertSubjectsButton);
			
			
			
			  link.add(new AjaxFormComponentUpdatingBehavior("onchange")
		         {
		             @Override
		             protected void onUpdate(AjaxRequestTarget target)
		             {
		                 target.add(editLinksButton);
		                 target.add(insertLinksButton);
		             }
		         });
			  subject.add(new AjaxFormComponentUpdatingBehavior("onchange")
		         {
		             @Override
		             protected void onUpdate(AjaxRequestTarget target)
		             { target.add(editSubjectsButton);
		             target.add(insertSubjectsButton);
		                 target.add(link);
		             }
		         });
	  
}


public Subjects getSelectedSubject() {
		return selectedSubject;
	}
	public void setSelectedSubject(Subjects selectedSubject) {
		this.selectedSubject = selectedSubject;
	}
	public Links getSelectedLink() {
		return selectedLink;
	}
	public void setSelectedLink(Links selectedLink) {
		this.selectedLink = selectedLink;
	}
	 private TreeModel convertToTreeModel(List<Object> list)
		{
			TreeModel model = null;
			
			DefaultMutableTreeNode rootNode= new DefaultMutableTreeNode("Links");
			add(rootNode, list);
			model = new DefaultTreeModel(rootNode);
			return model;
		}
	
	 @SuppressWarnings("unchecked")
		private void add(DefaultMutableTreeNode parent, List<Object> sub)
		{
			for (Object obj : sub)
			{
				if (obj instanceof List)
				{
					DefaultMutableTreeNode child=new DefaultMutableTreeNode("<no>");
						Iterator itt=((List) obj).iterator();
						Object object=null;
						while(itt.hasNext()){
							object=itt.next();
							if(object instanceof Subjects) break;
							}
				child = new DefaultMutableTreeNode(object);
				((List)obj).remove(object);
					
					
					parent.add(child);
					add(child, (List<Object>) obj);
					
			
				}
				else
				{
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(obj);
					parent.add(child);
				}
			}
		}
	
}
