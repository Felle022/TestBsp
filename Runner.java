package TestBspHueMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Runner {

	static String url = "jdbc:mysql://localhost:3306/infi";
	static String user = "root"; // user anlegen
	static String pass = "";  //PW
    
	static Connection getConnection(String tableName) throws ClassNotFoundException {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws ClassNotFoundException {
	Connection c= getConnection("TestBspHue.db");
	dropTable(c,"SchuelerZuKlasse");
	dropTable(c, "Schueler");
	dropTable(c,"Klasse");

	Schueler.createTable(c, "Schueler");
	Schueler.insertSchueler(c, "Eggers", "Mark", 38);
	Schueler.insertSchueler(c, "Ron", "Bielecki", 23);
	Schueler.insertSchueler(c, "Nico", "Lazzo", 28);
	Schueler.CSVInsert(c, "C:\\Users\\E5550\\Documents\\Schule\\3AHWII\\INFI\\CSV\\Kunden(NN,VV,Age).csv");
	
	Klasse.createTable(c, "Klasse");
	Klasse.insertKlasse(c, "4Bier", 30);
	Klasse.insertKlasse(c, "2DP", 23);
	Klasse.CSVInsert(c,"C:\\Users\\E5550\\Documents\\Schule\\3AHWII\\INFI\\CSV\\Klassen.csv" );
	
	SchuelerZuKlass.createTable(c, "SchuelerZuKlasse");
	SchuelerZuKlass.insertInto(c, "Ron", "Bielecki", 23, 1);
	SchuelerZuKlass.insertInto(c, "Eggers", "Mark", 38,3);
	SchuelerZuKlass.insertInto(c, "Eggers", "Mark", 38,4);
	SchuelerZuKlass.insertInto(c, "Eggers", "Mark", 38,2);
	
	
	Schueler.select(c, "Schueler");
	System.out.println();
	Klasse.select(c, "Klasse");
	System.out.println();
	SchuelerZuKlass.writeCSV(c,"C:\\Users\\E5550\\Documents\\Schule\\3AHWII\\INFI\\CSV\\SchuelerZuKlasse.csv");
	
	
	}
	
	static void dropTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "drop table if exists " + tableName + ";";
			stmt.executeUpdate(sql);
			System.out.println("Tabelle " + tableName + " wurde gelöscht.");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
