package TestBspHueMysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class SchuelerZuKlass {
	
	static void createTable(Connection c, String tableName) {
		try {
			Statement stmt = c.createStatement();
			String sql = "create table if not exists " + tableName +		// in Zukunft keine ID einbauen(KundenID&ArtikelID zum PrimaryKey machen)
			" (SchuelerID int,KlassenID int, Datum DATE, primary key(SchuelerID,KlassenID,Datum), foreign key (SchuelerID) references Schueler (ID), foreign key (KlassenID) references Klasse (ID));";
			System.out.println("Tabelle " + tableName + " wurde erstellt.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void insertInto(Connection c, int SID, int KID) {
		try {
			Statement stmt = c.createStatement();
			java.sql.Date sqlLd = java.sql.Date.valueOf(LocalDate.now());
			
				String sql = "insert into SchuelerZuKlasse values " +
				"(" + SID + ", " + KID + ",  \"" +  sqlLd + "\" );";
				System.out.println("Schueler --> Klasse");
				stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
