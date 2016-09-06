package braunimmobilien.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import braunimmobilien.util.EntityModel;
import braunimmobilien.dao.MemberDao;
import braunimmobilien.model.BaseObject;
import braunimmobilien.model.Identifiable;
import braunimmobilien.model.Member;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Identifiable;
import braunimmobilien.service.EntityLoader;

public class EntityLoaderTest extends BaseManagerTestCase {
	@Autowired
	EntityLoader entityLoader;
	@Autowired
	private MemberDao memberdao;
	public void setEntityLoader(EntityLoader entityLoader) {
		this.entityLoader = entityLoader;
	}
	
	 @Test
	    public void testEntityLoader(){
		 
	
		   memberdao.create("Heiner", "Braun");     
       	   List<Member> memberList=memberdao.findAll();
       assertTrue(memberList.size()>0); 
      Member member1 =memberList.get(0);
    Member member=entityLoader.load(Member.class, member1.getId());
    assertEquals(member.getFirstName(),"Heiner");
   
   
	    }
	 @Test
	    public void testEntityModel() throws IllegalAccessException,NoSuchFieldException{
		 Angobjzuord angobjzuord=new Angobjzuord();
		 angobjzuord.setId(null);
		 Angebot angebot=new Angebot();
		 angebot.setId("RH998");
		 angobjzuord.setAngebot(angebot);
	//	 EntityModel<Angobjzuord> entityModel= new EntityModel<Angobjzuord>(angobjzuord);
	//	 entityModel.setEntityLoader(entityLoader);
	//	 Angobjzuord angobjzuord1=entityModel.getObject();
		 for (Field f : angobjzuord.getClass().getDeclaredFields()) {
			    Class<?> clazz = f.getType();
			    f.setAccessible(true);
			  if (!clazz.isPrimitive()) {
				  if(f.get(angobjzuord) instanceof BaseObject) { 
					  if(f.get(angobjzuord)!=null){
						  assertEquals(clazz.getName(),"braunimmobilien.model.Angebot");	  
		   	  Field field=clazz.getDeclaredField("id");
		   	assertEquals(field.getType().getName(),"java.lang.String");	  
		   	  field.setAccessible(true);
						  assertEquals(field.get(f.get(angobjzuord)),"RH998");
					f.set(angobjzuord,entityLoader.load(clazz,((String) field.get(f.get(angobjzuord)))));
					  assertEquals(angobjzuord.getAngebot().getAngkurzbeschreibung(),"Mehrfamilienhaus");		  
				 	  }
				  }
			    }
		 }
		 angobjzuord=new Angobjzuord();
		 angobjzuord.setId(null);
		 angebot=new Angebot();
		 angebot.setId("RH998");
		 angobjzuord.setAngebot(angebot);
			EntityModel<Angobjzuord> entitymodel=new EntityModel<Angobjzuord>(angobjzuord); 
			entitymodel.setEntityLoader(entityLoader);
			Angobjzuord angobjzuord1=entitymodel.getObject();
			assertEquals(angobjzuord1.getAngebot().getAngkurzbeschreibung(),"Mehrfamilienhaus");		  
	 
	 }
	
	
}
