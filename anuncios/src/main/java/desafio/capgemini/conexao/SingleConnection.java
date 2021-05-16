package desafio.capgemini.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String URL_BANCO = "jdbc:postgresql://localhost:5432/anuncios";
	private static String PASSWORD = "admin";
	private static String USER = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(URL_BANCO, USER, PASSWORD);
				connection.setAutoCommit(false);
				System.out.println("Connecteded");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConection() {
		return connection;

	}

}

