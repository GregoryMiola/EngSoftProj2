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
	  
	/*public static Usuario verificaLogin(String user, String passwd) {
		Usuario loged = null;  
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();
					  
			String sql = "SELECT * FROM USUARIOS WHERE USERNAME = '" + user + "' AND PASSWORD = '" + passwd + "';";
			ResultSet rs = stmt.executeQuery(sql);
	      
			while (rs.next()) {
				loged = new Usuario();
		        loged.setId(rs.getInt("ID"));
		        loged.setNome(rs.getString("NOME"));
		        loged.setCargo(rs.getString("CARGO"));
		        loged.setUsername(rs.getString("USERNAME"));
		        loged.setPassword(rs.getString("PASSWORD"));
		        loged.setNivelAcesso(NivelAcesso.values()[rs.getInt("NIVEL_ACESSO")]);
		        break;
			}
			
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  	}
		  
	  	return loged;
	}*/
	
	/*
	public static void insereUsuario(Usuario user) {
				
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();
			
			String  sql = String.format("INSERT INTO USUARIOS (NOME, CARGO, USERNAME, PASSWORD, NIVEL_ACESSO) VALUES ('%s', '%s', '%s', '%s', '%d');", 
					user.getNome(), user.getCargo(), user.getUsername(), user.getPassword(), user.getNivelAcesso().getValue()); 
  	    stmt.executeUpdate(sql);
			
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  	}
	}
	*/
	
	//-- adicionado
	/*public static List<String> getLista(String sql){
		List<String> lst = new ArrayList<String>();
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();
			 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				lst.add(rs.getString(1) + " - " + rs.getString(2));
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return lst;
	}*/
	
	/*public Usuario getUsuario(String sql){
		//List<String> lst = new ArrayList<String>();
		
		Usuario user = null;
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();			 
			ResultSet rs = stmt.executeQuery(sql);
			
			
			
			while(rs.next()){				
				user.setId(rs.getInt("ID"));
				user.setNome(rs.getString("NOME"));
				user.setCargo(rs.getString("CARGO"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setNivelAcesso(NivelAcesso.values()[rs.getInt("NIVEL_ACESSO")]);
		        break;
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return user;
	}*/
	
	/*public Dispositivo getDispositivo(String sql){
		//List<String> lst = new ArrayList<String>();
		
		Dispositivo disp = null;
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();			 
			ResultSet rs = stmt.executeQuery(sql);
			
			
			
			while(rs.next()){				
				disp.setID(rs.getInt("ID"));
				disp.setNOME(rs.getString("NOME"));
				disp.setCODIGO(rs.getString("ENDERECO"));
				disp.setTIPO(rs.getString("TIPO"));				
		        break;
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return disp;
	}*/
	
	/*public Mudanca getMudanca(String sql){
		//List<String> lst = new ArrayList<String>();
		
		Mudanca mudanca = null;
		
		try{
			c = DriverManager.getConnection("jdbc:sqlite:forca.db");
			stmt = c.createStatement();			 
			ResultSet rs = stmt.executeQuery(sql);			
			
			while(rs.next()){				
				mudanca.setID(rs.getInt("ID"));
				mudanca.setTITULO(rs.getString("TITULO"));
				mudanca.setID_DISPOSITIVO(rs.getInt("ID_EQUIPAMENTO"));
				mudanca.setID_USUARIO(rs.getInt("ID_USUARIO"));
				mudanca.setDATA_INI(rs.getDate("DATA_INI"));
				mudanca.setHORA_INI(rs.getDate("HORA_INI"));
				mudanca.setDATA_FIM(rs.getDate("DATA_FIM"));
				mudanca.setHORA_INI(rs.getDate("HORA_FIM"));
				mudanca.setARQUIVO_INI(rs.getString("ARQUIVO_INI"));
				mudanca.setARQUIVO_FIM(rs.getString("ARQUIVO_FIM"));
				mudanca.setCOMANDOS_APLICADOS(rs.getString("COMANDOS_APLICADOS"));
				mudanca.setSTATUS(rs.getString("STATUS"));
				mudanca.setANALISADO(rs.getString("ANALISADO"));								
		        break;
			}
			
			stmt.close();
			c.close();
			rs.close();
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	  	}
		
		return mudanca;		
	}*/
	//-- adicionado

}
