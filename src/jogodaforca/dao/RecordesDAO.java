package jogodaforca.dao;

import java.util.List;

import jogodaforca.db.SQLiteJDBC;
import jogodaforca.modelo.Recorde;

public class RecordesDAO {
	public static Object[][] getRecordes(){
		String sql = "SELECT NOME, PONTOS FROM RECORDES ORDER BY PONTOS DESC";
		List<String[]> lst = SQLiteJDBC.getPontos(sql);
		
		String [][] recordes = new String[lst.size()][lst.get(0).length];
		
		for(int i = 0; i < lst.size();i++)
		{
			for(int j = 0; j < lst.get(i).length; j++)
			{
				recordes[i][j] = lst.get(i)[j];
			}
		}
		
		return recordes;		
	}
	
	public static void setRecordes(Recorde recorde){
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO RECORDES (NOME, PONTOS) ");
		sql.append("VALUES ('" + recorde.getNome() + "', '" + recorde.getPontos() + "') "); 
		
		SQLiteJDBC.execute(sql.toString());
	}
}
