package jogodaforca.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import jogodaforca.modelo.Palavra;

public class SQLiteJDBC {

	private static Connection c = null;
	private static Statement stmt = null;
	static final String JDBC_DRIVER = "org.sqlite.JDBC";
	
	public static void main( String args[] )
	{	    
	    try {
	      Class.forName(JDBC_DRIVER);
	      c = DriverManager.getConnection("jdbc:sqlite:forca.db");
	      
	      System.out.println("Opened database successfully");
	      stmt = c.createStatement();
	      
	      String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='PALAVRAS';";
	      ResultSet rs = stmt.executeQuery(sql);
	      if(!rs.next())
	      {
	    	  sql = "";
	    	  sql = "CREATE TABLE PALAVRAS " +
	    		  	   "(ID 			INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL, " +
	                   " TEMA	        TEXT    							NOT NULL, " +
	                   " PALAVRA        TEXT     							NOT NULL)"; 
	    	  stmt.executeUpdate(sql);
	      }
	      
	      sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='RECORDES';";
	      rs = stmt.executeQuery(sql);
	      if(!rs.next())
	      {
	    	  sql = "";
	    	  sql = "CREATE TABLE RECORDES " +
	              "(ID 				INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL,"  +
	              " NOME	       	TEXT     							NOT NULL, " +
	              " PONTOS			INTEGER    							NOT NULL)";
	    	  stmt.executeUpdate(sql);
	      }
	      	      
	      sql = "SELECT * FROM PALAVRAS WHERE TEMA = 'BEBIDAS' AND PALAVRA IS NOT NULL;";
	      rs = stmt.executeQuery(sql);
	      if(!rs.next())
	      {
	    	  sql = "";
	    	  sql = "INSERT INTO PALAVRAS (TEMA, PALAVRA) VALUES ('BEBIDAS', 'CERVEJA');\n"
	    	  		+ "INSERT INTO PALAVRAS (TEMA, PALAVRA) VALUES ('BEBIDAS', 'BACARDI');\n"
	    	  		+ "INSERT INTO PALAVRAS (TEMA, PALAVRA) VALUES ('BEBIDAS', 'VODKA');\n"
	    	  		+ "INSERT INTO PALAVRAS (TEMA, PALAVRA) VALUES ('BEBIDAS', 'CACHAÇA');\n"
	    	  		+ "INSERT INTO PALAVRAS (TEMA, PALAVRA) VALUES ('BEBIDAS', 'WHISKY');\n"
	    	  		+ "INSERT INTO PALAVRAS (TEMA, PALAVRA) VALUES ('BEBIDAS', 'ABSINTO');"; 
	    	  stmt.executeUpdate(sql);
	      }
	      
	      sql = "SELECT * FROM RECORDES";
	      rs = stmt.executeQuery(sql);
	      if(!rs.next())
	      {
	    	  sql = "";
	    	  sql = "INSERT INTO RECORDES (NOME, PONTOS) VALUES ('Jogador A', 30);\n"
	    	  		+ "INSERT INTO RECORDES (NOME, PONTOS) VALUES ('Jogador B', 20);\n"
	    			+ "INSERT INTO RECORDES (NOME, PONTOS) VALUES ('Jogador C', 10);"; 
	    	  stmt.executeUpdate(sql);
	      }
	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}
	
	public static List<String[]> getPontos(String sql){
		List<String[]> lst = new ArrayList<String[]>();
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				lst.add(new String [] { rs.getString(1), rs.getString(2) });
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return lst;
	}
	
	public static List<String> getTemas(String sql){
		List<String> lst = new ArrayList<String>();
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				lst.add(rs.getString(1));
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return lst;
	}
	
	public static ArrayList<Palavra> getPalavras(String sql){
		ArrayList<Palavra> lst = new ArrayList<Palavra>();
		Palavra palavra;
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				palavra = new Palavra(Integer.parseInt(rs.getString(1)), rs.getString(3), rs.getString(2));
				lst.add(palavra);
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return lst;
	}
	
	public static void execute(String sql) {
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();			
			 
    	    stmt.executeUpdate(sql);
			
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
  	}
}
