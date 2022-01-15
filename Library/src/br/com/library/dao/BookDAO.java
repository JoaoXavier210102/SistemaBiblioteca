package br.com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.library.factory.ConnectionFactory;
import br.com.library.model.Book;

public class BookDAO {

	// Método para salvar Algum dado no banco
	public void save(Book book) {

		String sql = "INSERT INTO book(ISBN, authors, edition, title, year, publishingCompany) VALUES (?,?,?,?,?,?);";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			// Adicionando valores esperado pela query
			pstm.setLong(1, book.getISBN());
			pstm.setString(2, book.getAuthors());
			pstm.setString(3, book.getEdition());
			pstm.setString(4, book.getTitle());
			pstm.setInt(5, book.getYear());
			pstm.setString(6, book.getPublishingCompany());

			// Executar query
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

	// Método para listar TODAS as informações do banco de dados
	public List<Book> listAllBook() {

		String sql = "SELECT * FROM book;";

		List<Book> books = new ArrayList<Book>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {

			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			// Enquanto estiver dados no banco faça isso
			while (rset.next()) {
				Book book = new Book();

				// Recuperar o autor do livro
				book.setAuthors(rset.getString("authors"));
				// Recuperar edição do livro
				book.setEdition(rset.getString("edition"));
				// Recuperar título do livro
				book.setTitle(rset.getString("title"));
				// Recuperar o ano do livro
				book.setYear(rset.getInt("year"));
				// Recuper ISBN do livro
				book.setISBN(rset.getLong("ISBN"));
				// Recuper o id do livro
				book.setId(rset.getInt("id"));
				// Recuper o estado do livro (emprestado)
				book.setBorrowed(rset.getInt("borrowed"));

				books.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		return books;
	}

	// Método para atualizar algum dado no banco
	public void update(Book book) {

		String sql = "UPDATE book SET ISBN = ?, authors = ?, edition = ?, title = ?, year = ? WHERE id = ?;";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			pstm.setLong(1, book.getISBN());
			// Adiciona o valor do segundo parâmetro da sql
			pstm.setString(2, book.getAuthors());
			// Adiciona o valor do terceiro parâmetro da sql
			pstm.setString(3, book.getEdition());
			// Adiciona o valor do quarto parâmetro da sql
			pstm.setString(4, book.getTitle());
			// Adiciona o valor do quinta parâmetro da sql
			pstm.setInt(5, book.getYear());

			pstm.setInt(6, book.getId());

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

	// Método para atualizar Empréstido no banco de dados
	public void updateBorrowed(Book book, int idBook) {

		String sql = "UPDATE book SET borrowed = ? WHERE id = ?;";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(sql);

			// Adiciona o valor do primeiro parâmetro da sql
			pstm.setLong(1, book.getBorrowed());

			pstm.setInt(2, idBook);

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

	// Método para deletar algum dado no banco
	public void removeBookById(int id) {

		String sql = "DELETE FROM book WHERE id = ?;";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

			System.out.println("Livro deletado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	// Método para listar todos os livros com ISBN
	public List<Book> searchISBN(long ISBN) {

		String sql = "SELECT * FROM book WHERE ISBN = ?";

		List<Book> books = new ArrayList<Book>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {

			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, ISBN);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Book book = new Book();

				// Recuperar o autor do livro
				book.setAuthors(rset.getString("authors"));
				// Recuperar edição do livro
				book.setEdition(rset.getString("edition"));
				// Recuperar título do livro
				book.setTitle(rset.getString("title"));
				// Recuperar o ano do livro
				book.setYear(rset.getInt("year"));
				// Recuper ISBN do livro
				book.setISBN(rset.getLong("ISBN"));
				// Recuper o id do livro
				book.setId(rset.getInt("id"));

				books.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		return books;
	}

	// Método para listar todos os livros com título
	public List<Book> searchTitle(String title) {

		String sql = "SELECT * FROM book WHERE title = ?";

		List<Book> books = new ArrayList<Book>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {

			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, title);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Book book = new Book();

				// Recuperar o autor do livro
				book.setAuthors(rset.getString("authors"));
				// Recuperar edição do livro
				book.setEdition(rset.getString("edition"));
				// Recuperar título do livro
				book.setTitle(rset.getString("title"));
				// Recuperar o ano do livro
				book.setYear(rset.getInt("year"));
				// Recuper ISBN do livro
				book.setISBN(rset.getLong("ISBN"));
				// Recuper o id do livro
				book.setId(rset.getInt("id"));

				books.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		return books;
	}

	// Método para listar todos os livros com editora
	public List<Book> searchPublishingCompany(String publishingCompany) {

		String sql = "SELECT * FROM book WHERE publishingCompany = ?";

		List<Book> books = new ArrayList<Book>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {

			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, publishingCompany);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Book book = new Book();

				// Recuperar o autor do livro
				book.setAuthors(rset.getString("authors"));
				// Recuperar edição do livro
				book.setEdition(rset.getString("edition"));
				// Recuperar título do livro
				book.setTitle(rset.getString("title"));
				// Recuperar o ano do livro
				book.setYear(rset.getInt("year"));
				// Recuper ISBN do livro
				book.setISBN(rset.getLong("ISBN"));
				// Recuper o id do livro
				book.setId(rset.getInt("id"));

				books.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		return books;
	}

	// Método para listar todos os livros com id
	public List<Book> searchId(int id) {

		String sql = "SELECT * FROM book WHERE id = ?";

		List<Book> books = new ArrayList<Book>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {

			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, id);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Book book = new Book();

				// Recuperar o autor do livro
				book.setAuthors(rset.getString("authors"));
				// Recuperar edição do livro
				book.setEdition(rset.getString("edition"));
				// Recuperar título do livro
				book.setTitle(rset.getString("title"));
				// Recuperar o ano do livro
				book.setYear(rset.getInt("year"));
				// Recuper ISBN do livro
				book.setISBN(rset.getLong("ISBN"));
				// Recuper o id do livro
				book.setId(rset.getInt("id"));
				// Recuperar o empréstimo do livro
				book.setBorrowed(rset.getInt("borrowed"));

				books.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		return books;
	}
}
