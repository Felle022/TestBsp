package TestBspHueMysql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class SchuelerZuKlass {
	
	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName +		// in Zukunft keine ID einbauen(KundenID&ArtikelID zum PrimaryKey machen)
			" (Nachname varchar(30),Vorname varchar(30),age int,KlassenID int, Datum DATE, primary key(Nachname,Vorname,age ,KlassenID,Datum), foreign key (Nachname,Vorname,age) references Schueler (Nachname,Vorname,age), foreign key (KlassenID) references Klasse (ID));";
			System.out.println();
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void insertInto(Connection c, String nachname,String vorname,int age, int KID) {
		try {
			Statement stmt = c.createStatement();
			java.sql.Date sqlLd = java.sql.Date.valueOf(LocalDate.now());
			
				String sql = "insert into SchuelerZuKlasse values " +
				"('" + nachname + "','" + vorname + "'," + age +"," + KID + ", \"" +  sqlLd + "\" );";
				
				System.out.println(sql);
				System.out.println(""+nachname+" --> "+KID+"\n");
				stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	static void writeCSV(Connection c,String url)
	{
		 Statement stmt;
		
			try {
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM schuelerzuklasse;" );
				File file = new File(url);
					FileWriter fileWriter = new FileWriter(file);
					String head="Nachname;Vorname;age;KlassenID,Datum";
					fileWriter.write(head);
			      while ( rs.next() ) {
			         String nn = rs.getString("nachname");
			         String vn = rs.getString("vorname");
			         int age=rs.getInt("age");
			         int kid = rs.getInt("KlassenID");
			         java.sql.Date sqlLd=rs.getDate("Datum");
			         String send="\n"+nn+";"+vn+";"+age+";"+kid+";"+sqlLd+"";
			         //System.out.println(send);
			         fileWriter.write(send);
			         	}
			      fileWriter.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		     	
	}		



}
	

