package TestBspHueMysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Schueler {

	static public void createTable(Connection c, String tableName)
	{
		try {
			Statement st= c.createStatement();
			String sql="create table "+tableName+"(ID int primary key auto_increment,Nachname varchar(30), Vorname varchar(30), age int)";
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
			System.out.println("Der Schueler"+nn+" "+vn+" wurde erfolgreich ins System aufgenommen ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
