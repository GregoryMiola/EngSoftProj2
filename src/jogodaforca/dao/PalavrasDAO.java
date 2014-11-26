package jogodaforca.dao;

import jogodaforca.db.SQLiteJDBC;
import jogodaforca.modelo.Palavra;

public class PalavrasDAO {
			
	public static void salvaPalavra(Palavra palavra){
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PALAVRAS (TEMA, PALAVRA) ");
		sql.append("VALUES ('" + palavra.getTema() + "', '" + palavra.getPalavra() + "') "); 
		
		SQLiteJDBC.execute(sql.toString());
	}
	
	public static String[] getTemas(){
		String sql = "SELECT DISTINCT TEMA FROM PALAVRAS ORDER BY ID";
		
		return (String[]) SQLiteJDBC.getTemas(sql).toArray(new String[0]);
	}
}

