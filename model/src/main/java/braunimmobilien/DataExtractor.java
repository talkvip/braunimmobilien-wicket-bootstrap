package braunimmobilien;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
/**
* @author vhazrati
*
*/
public class DataExtractor {
 
public static void main(String[] args) throws DatabaseUnitException,ClassNotFoundException, SQLException, DataSetException, FileNotFoundException, IOException {
Class.forName("com.mysql.jdbc.Driver");
 
Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqldb", "root", "Braun");
IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

QueryDataSet partialDataSet = new QueryDataSet(connection);


partialDataSet.addTable("XTyp");

partialDataSet.addTable("Angstatus");


partialDataSet.addTable("Eigentuemertyp");

partialDataSet.addTable("Eigtstatus");

partialDataSet.addTable("Emailbriefe");

partialDataSet.addTable("Konditionen");

partialDataSet.addTable("Kundenart");

partialDataSet.addTable("Land");

partialDataSet.addTable("Objarttyp");

partialDataSet.addTable("Objektarten");

partialDataSet.addTable("Objektsuch");

partialDataSet.addTable("Orte");

partialDataSet.addTable("Status");

partialDataSet.addTable("Strassen");

partialDataSet.addTable("Subjects");

partialDataSet.addTable("Telefonart");

partialDataSet.addTable("Type");

FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Links");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-links.xml"));




partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Eigentuemer");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-eig.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Objekte");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-obj.xml"));

partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Angebote");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-ang.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Mitrumpf");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-mit.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Kunden");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-kund.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Nachweise");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-nach.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Scout");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-scout.xml"));

partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Eigentuemermuster");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-muster.xml"));


partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Zuordnungstabelle");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-zuordnungstabelle.xml"));

partialDataSet = new QueryDataSet(connection);
partialDataSet.addTable("Zuordangobj");
FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/test-dataset-zuordangobj.xml"));



System.out.println("Datasets  written");
}
}
