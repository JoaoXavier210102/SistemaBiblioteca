package br.com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.library.factory.ConnectionFactory;
import br.com.library.model.Book;
import br.com.library.model.User;

public class UserDAO {

	// Método para salvar dados no banco
	public void save(User users) {

		String sql = "INSERT INTO user(name, password, type) VALUES (?,?,?);";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, users.getName());
			pstm.setInt(2, users.getPassword());
			pstm.setString(3, users.getType());

			pstm.execute();

			System.out.println("Sucesso no armazenamento de dados!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// Fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Método para listar todos os usuários do banco
	public List<User> listAllUsers() {

		String sql = "SELECT * FROM user;";

		List<User> users = new ArrayList<User>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			// Enquanto tiver dados no banco faça isso
			while (rset.next()) {
				User user = new User();

				// Recuper o nome do usuário
				user.setName(rset.getString("name"));
				// Recuperar a senha do usuário
				user.setPassword(rset.getInt("password"));
				// Recuperar o Id do usuário
				user.setId(rset.getInt("id"));
				// Recuper o tipo do usuário
				user.setType(rset.getString("type"));

				users.add(user);
			}
		} catch (Exception e) {
			System.out.println("Erro no listAllUsers");
			e.printStackTrace();
		} finally {

			// Fechar conexões
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	// Método para deletar algum usuário
	public void removeUserById(int id) {

		String sql = "DELETE FROM user WHERE id = ?;";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

			System.out.println("usuário deletado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro no removeUserById");
			e.printStackTrace();
		} finally {
			// Fechando conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// Método para buscar pelo nome
	public List<User> searchName(String name) {

		String sql = "SELECT * FROM user WHERE name = ?;";

		List<User> users = new ArrayList<User>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, name);

			rset = pstm.executeQuery();

			while (rset.next()) {
				User user = new User();

				// Recuperar o nome do usuário
				user.setName(rset.getString("name"));
				// Recuperar o id do usuário
				user.setId(rset.getInt("id"));
				// Recuperar a senha do usuário
				user.setPassword(rset.getInt("password"));
				//Recuper o tipo do usuário
				user.setType(rset.getString("type"));

				users.add(user);

			}
		} catch (Exception e) {
			System.out.println("Erro no searchName");
			e.printStackTrace();
		} finally {
			// Fechando as conexões
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;
	}
	
	// Método para bucar pelo id
	public List<User> searchId(int id) {

		String sql = "SELECT * FROM user WHERE id = ?;";

		List<User> users = new ArrayList<User>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			rset = pstm.executeQuery();

			while (rset.next()) {
				User user = new User();

				// Recuperar o nome do usuário
				user.setName(rset.getString("name"));
				// Recuperar o id do usuário
				user.setId(rset.getInt("id"));
				// Recuperar a senha do usuário
				user.setPassword(rset.getInt("password"));
				//Recuperar o quantidade de empréstimo do usuário
				user.setBorrowing(rset.getInt("borrowing"));
				//Recuperar o tipo do usuário
				user.setType(rset.getString("type"));

				users.add(user);

			}
		} catch (Exception e) {
			System.out.println("Erro no searchName");
			e.printStackTrace();
		} finally {
			// Fechando as conexões
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	// Método para buscar pelo tipo
	public List<User> searchType(String type) {

		String sql = "SELECT * FROM user WHERE type = ?;";

		List<User> users = new ArrayList<User>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, type);

			rset = pstm.executeQuery();

			while (rset.next()) {
				User user = new User();

				// Recuperar o nome do usuário
				user.setName(rset.getString("name"));
				// Recuperar o id do usuário
				user.setId(rset.getInt("id"));
				// Recuperar a senha do usuário
				user.setPassword(rset.getInt("password"));
				// Recuperar o tipo do usuário
				user.setType(rset.getString("type"));

				users.add(user);

			}
		} catch (Exception e) {
			System.out.println("Erro no searchName");
			e.printStackTrace();
		} finally {
			// Fechando as conexões
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return users;
	}
	
	//Método para atualizar quantidade de empréstimos de usuários
	public void update(User user, int id) {

		String sql = "UPDATE user SET borrowing = ? WHERE id = ?;";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			pstm.setInt(1, user.getBorrowing());

			pstm.setInt(2, id);

			// Execute a sql para inserção dos dados
			pstm.execute();

			System.out.println("Sucesso na atualização de dados");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
