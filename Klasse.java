package TestBspHueMysql;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Klasse {
	
	static public void createTable(Connection c,String tableName)
	{
		try {
			Statement st =c.createStatement();
			String sql="create table "+ tableName+"(ID int primary key auto_increment,Klassenbezeichnung varchar(6), size int);";
			st.execute(sql);
			st.close();
			System.out.println();
			System.out.println("Table "+tableName+" wurde erstellt");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static public void insertKlasse(Connection c, String kk,int size) {
		try {
			Statement st= c.createStatement();
			String sql="insert into Klasse(Klassenbezeichnung,size) values('"+kk+"',"+size+");";
			st.execute(sql);
			st.close();
			System.out.println("Klasse "+kk+"wurde erfolgreich angelegt");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static void CSVInsert(Connection c, String url) {
		File file = new File(url);
		Scanner sc;
		String daten = "";
		
		try {
			sc = new Scanner(file);
			String firstLine = sc.nextLine();
			String[] insert = new String[Spa(firstLine)];
			

			while (sc.hasNextLine()) {
				daten = sc.nextLine();
				insert = daten.split(";");
				String sql = "insert into Klasse(Klassenbezeichnung,size) values (?, ?);";
				PreparedStatement st;
				try {
					st = c.prepareStatement(sql);
					st.setString(1, insert[0]);
					int size = Integer.parseInt(insert[1]);
					st.setInt(2, size);
					st.executeUpdate();
					st.close();
					System.out.println(
							"Die Klasse " + insert[0] + " wurde aus der CVS-Datei ausgelsen.");

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static int Spa(String firstline) {
		 int anzSp=0;
			
			for(int i=0;i<firstline.length();i++)
			{
				char vergleich=firstline.charAt(i);
				if(vergleich==';')
				{
					anzSp++;
				}
				
			}
			return  anzSp;
	 }

	static void select(Connection c,String tableName)
	{
		 Statement stmt;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Klasse;" );
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String  KB = rs.getString("Klassenbezeichnung");
		         int size  = rs.getInt("size");
		         System.out.printf("\n|"+id+"|"+KB+"|"+size+"|");
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
		
	}
	
}
