package braunimmobilien;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.operation.DatabaseOperation;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        try{Class.forName("com.mysql.jdbc.Driver");
		 
				Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appfusedb", "root", "Braun");
				IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
				connection.getConfig().setFeature(connection.getConfig().FEATURE_CASE_SENSITIVE_TABLE_NAMES, Boolean.TRUE);
				
				IDataSet dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Datasets written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-links.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Links written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-obj.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Objekte written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-ang.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Angebote written");			
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-eig.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Eigentuemer written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-mit.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Mitarbeiter written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-kund.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Kunden written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-nach.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Nachweise written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-scout.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Scout written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-muster.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Eigentuemermuster written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-zuordnungstabelle.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Zuordnungstabelle written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset-zuordangobj.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Zuordangobj written");
				/*DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Angstatus written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_eigentuemertyp.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Eigentuemertyp written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_eigtstatus.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Eigtstatus written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_emailbriefe.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Emailbriefe written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_konditionen.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Konditionen written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_kundenart.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Kundenart written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_land.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Land written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_objarttyp.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Objarttyp written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_objektarten.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Objektarten written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_objektsuch.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Objektsuch written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_orte.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Orte written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_status.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Status written");
				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_strassen.xml")); // Load XML file to DB unit dataset 
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				System.out.println("Dataset Strassen written");
				//dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_subjects.xml")); // Load XML file to DB unit dataset 
				//DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				//System.out.println("Dataset Subjects written");
				//dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_telefonart.xml")); // Load XML file to DB unit dataset 
				//DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
				//System.out.println("Dataset Telefonart written");
//				dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset_type.xml")); // Load XML file to DB unit dataset */
	//			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet); //Import your data
		//		System.out.println("Dataset Type written");
//			
		//		dataSet = new FlatXmlDataSet(new FileInputStream("src/test/resources/test-dataset.xml")); // Load XML file to DB unit dataset 
				
			//	System.out.println("Dataset Links written");*/
				
				
				
				
				
				connection.close();
				System.out.println("Dataset finished");
				
				
		        }
		        catch(Exception ex){System.err.println(ex);
		        ex.printStackTrace();
		        }
//				CLEAN_INSERT deletes all existing records and does a fresh import. If foreign key constraints are setup in database, include tables in same order in which they should be loaded (child tables followed by parent), during deletion, dbunit deletes them reverse order. 
//				To insert null values, use a placeholder value, like "[NULL]" in your XML and replace it with null at runtime using ReplacementDataSet. 

//				ReplacementDataSet dataSetrep = new ReplacementDataSet(new FlatXmlDataSet(…)); 
//				dataSetrep.addReplacementObject("[NULL]", null);
	}

}
