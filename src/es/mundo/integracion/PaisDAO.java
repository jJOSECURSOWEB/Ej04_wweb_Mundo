package es.mundo.integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.mundo.modelo.Pais;

public class PaisDAO {
	private Connection cx;		
	
		private void conectar(){
		try {
			Class.forName("com.mysql.jdbc");
			cx=DriverManager.getConnection("jdbc:mysql://localhost:3306/mundo","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}		

		private void desconectar(){
			try {
				cx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
public int darAlta(Pais pais) 
		{
	
	try {
		//1 conectar
		conectar();	
		//2 prepararla sql(query)		   
		PreparedStatement ps=cx.prepareStatement("INSET INTO PAIS VALUES(?,?,?)");
		 //2.1 setear las interrogogantes
		ps.setInt(1,0);
		ps.setString(2,pais.getNombre());
		ps.setInt (3,pais.getHabitantes());		
		//3 ejecutar la consulta
		int filasAfectadas=ps.executeUpdate();
		//4
		cx.commit();
		//5 cerrar la conexion
		desconectar();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		return 0;
		}


private void setInt(int i, int j) {
	// TODO Auto-generated method stub
	
}

private void PreparedStatement(String string) {
	// TODO Auto-generated method stub
	
}

public Pais consultarUno(int id) 
{   Pais p=new Pais();
	try {
		// 1. Conectar
		conectar();
		// 2. preparar la consulta sql
		// "PreparedStatement" es un objeto que permite desde Java
		//  construir instrucciones SQL
		PreparedStatement ps;
		ps = cx.prepareStatement("SELECT * FROM pais WHERE ID=?");
		// 2.1 setear los interrogantes
		ps.setInt(1, id);
		// 3. ejecutar la consulta sql que devuelve 1 ResultSet
		ResultSet rs = ps.executeQuery();
		//int filasAfectadas = rs.executeQuery(); //) ... para "Select" que no modifica la BBDD
				
		// 4. llenar el objeto pais con los datos de
		// respuesta de la Base de Datos que viene en 
		// un objeto del tipo ResultSet "rs".
		// Comprobamos si la consulta devuelve al menos 1 elemento...
		// Como la consulta la hacemos con la clave principal
		// sabemos que devolverá 1 sola fila por eso nos sirve
		// el "if" si no deberíamos usar "while" 
		
		if(rs.next())
		{// viene al menos 1 fila (tiene un next)
		   p.setId(rs.getInt("id"));
		   p.setNombre(rs.getString("nombre"));
		   p.setHabitantes(rs.getInt("habitantes"));
		}  
		
		// 5. cerrar la conexión
		desconectar();
} catch (SQLException e) {e.printStackTrace();}
  return p;
}		

}