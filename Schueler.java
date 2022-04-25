package TestBspHueMysql;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Schueler {

	static public void createTable(Connection c, String tableName)
	{
		try {
			Statement st= c.createStatement();
			String sql="create table if not exists "+tableName+"(Nachname varchar(30), Vorname varchar(30), age int, primary key(Nachname,Vorname,age))";
			st.executeUpdate(sql);
			System.out.println();
			System.out.println("Table "+tableName+" wurde erstellt");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static public void insertSchueler(Connection c, String nn, String vn, int age) {
		try {
			Statement st= c.createStatement();
			String sql="insert into Schueler(Nachname,Vorname,age) values('"+nn+"','"+vn+"','"+age+"')";
			st.execute(sql);
			st.close();
			System.out.println("Der Schueler "+nn+" "+vn+" wurde erfolgreich ins System aufgenommen ");
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
				daten.trim();
				insert = daten.split(";");
				String sql = "insert into Schueler(Nachname,Vorname,age) values (?, ?, ?);";
				PreparedStatement st;
				try {
					st = c.prepareStatement(sql);
					st.setString(1, insert[0]);
					st.setString(2, insert[1]);
					int age = Integer.parseInt(insert[2]);
					st.setInt(3, age);
					st.executeUpdate();
					st.close();
					System.out.println(
							"Der Schueler " + insert[0] + " " + insert[1] + " wurde erfolgreich sus der CSV-Datei ausgelsen.");

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
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Schueler;" );
		      
		      while ( rs.next() ) {
		         String  NN = rs.getString("Nachname");
		         String  VN = rs.getString("Vorname");
		         int age  = rs.getInt("age");
		         System.out.printf("\n|"+NN+"|"+VN+"|"+age+"|");
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
		
	}
}

