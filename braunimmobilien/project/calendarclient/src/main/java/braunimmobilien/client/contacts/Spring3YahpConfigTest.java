package braunimmobilien.client.contacts;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Spring3YahpConfigTest {

		public static void main(String[] args) {

			 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-resources.xml","applicationContext-dao.xml","applicationContext-service.xml","applicationContext-main.xml");
		

		System.out.println("Calling Bean method: yahp");


		Spring3Yahp myBean = (Spring3Yahp) context.getBean("spring3Yahpcontacts");
	
		myBean.yahp(args);
		}

		}

