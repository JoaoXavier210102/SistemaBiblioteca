package br.com.library.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usuário
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "Coloque sua senha do banco de dados";

	//Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8";
	
    //CONEXÃO COM O BANCO DE DADOS
	public static Connection createConnectionToMySQL() throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria a conexão com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		Connection conn = createConnectionToMySQL();
		
		if(conn != null) {
			System.out.println("Conexão obetida com sucesso");
			conn.close();
		}
	}
}
