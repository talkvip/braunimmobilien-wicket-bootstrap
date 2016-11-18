package braunimmobilien.bootstrap.webapp.pages;

import braunimmobilien.service.*;
import braunimmobilien.webapp.person.SynchronizeContacts;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.AngebotPanel;
import braunimmobilien.model.*;
import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.webapp.person.*;
import braunimmobilien.bootstrap.webapp.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.SessionFactory;

import java.io.File;

import javax.servlet.ServletContext;


import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.Application;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.tester.TagTester;


import org.junit.After;

import org.junit.Before;
import org.junit.runner.RunWith;


import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;


import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.annotation.ExpectedException;

import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-dao.xml","classpath:applicationContext-resources.xml","classpath:applicationContext-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
	public abstract class AbstractWicketTest {
	    @Autowired
	    private ApplicationContext applicationContext;
	 
	    private WicketApplication myWebApp;
	 
	    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	    protected FormTester formTester = null;  
     
	    protected TagTester tagTester = null;  
	    
	    protected List<TagTester> tagTesterList = null;
	    
	    protected WicketTester tester = null;
	    
	    @Before
	    public void setUp() throws Exception {
	    		
				
	    		myWebApp = new WicketApplication(); 
	        	
	        	myWebApp.setApplicationContext(applicationContext); 
	        	
	        	    tester = new WicketTester(myWebApp);
	         	    tester.startPage(BraunHomePage.class);
	                formTester = tester.newFormTester("intform");
	            	formTester.setValue("maklerUsername", "root");
	            	formTester.setValue("maklerPassword", "Braun");		
	            	formTester.submit();
	            	setupTest();
	    }
	 
	    /**
	     * Subclasses can use this method to provide the configuration needed by
	     * each test.
	     */
	    protected abstract void setupTest();
	 
	    /**
	     * Adds mock to the mock application context.
	     * @param beanName  The name of the mock bean.
	     * @param mock  The mock object.
	     */
	  
	 
	    protected WicketTester getTester() {
	        return tester;
	    }
	    @After
	    public void tearDown(){
	    	//clear any side effect occurred during test.
	    	tester.destroy();
	    }  
	    
	}

