package braunimmobilien;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;





import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.operation.DatabaseOperation;

public class ExtractInsert {
	 
	;
	 public static final String[] TABLES = new String[]{"XTyp","Angstatus", "Eigentuemertyp","Eigtstatus","Emailbriefe","Konditionen","Kundenart","Land","Objarttyp","Objektarten","Objektsuch","Orte","Status","Strassen","Subjects","Telefonart","Type","Links","Eigentuemer","Objekte","Angebote","Mitrumpf","Kunden","Nachweise","Scout","Eigentuemermuster","Zuordnungstabelle","Zuordangobj","Mitzuord"};

	    public static void main(String[] args) throws Exception {
	        System.out.println("Running Migration...");

	        Class.forName("com.mysql.jdbc.Driver");
	   	 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-resources.xml","applicationContext-dao.xml","applicationContext-service.xml","applicationContext-reindex.xml");
			

			System.out.println("Calling Bean method: yahp");


	        
	        Connection exportConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqldb", "root", "Braun");
	        IDatabaseConnection exportDatabaseConnection = new DatabaseConnection(exportConnection);

	      
	        Connection importConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appfusedb?characterEncoding=UTF-8", "root", "Braun");
			IDatabaseConnection importDatabaseConnection = new DatabaseConnection(importConnection);
		
			importDatabaseConnection.getConfig().setFeature(importDatabaseConnection.getConfig().FEATURE_CASE_SENSITIVE_TABLE_NAMES, Boolean.TRUE);
			

	        for (String table : TABLES) {
	        System.out.println("Migrating table: " + table);
	            QueryDataSet exportDataSet = new QueryDataSet(exportDatabaseConnection);
	            exportDataSet.addTable(table, "SELECT * FROM " + table);
	            DatabaseOperation.INSERT.execute(importDatabaseConnection, exportDataSet);
	        }

	        exportDatabaseConnection.close();
	        importDatabaseConnection.close();
	        ReIndexEigentuemer  reIndexEigentuemer=(ReIndexEigentuemer) context.getBean("reIndexEigentuemer");

			System.out.println("Start Reindex Eigentuemer");


	     //   reIndexEigentuemer.reindex();
	        System.out.println("Migration complete");

	    }

}
