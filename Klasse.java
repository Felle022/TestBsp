package TestBspHueMysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

}
