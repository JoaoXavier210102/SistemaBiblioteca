package br.com.library.main;

import br.com.library.dao.BookDAO;
import br.com.library.dao.UserDAO;
import br.com.library.model.Book;
import br.com.library.model.User;
import java.util.Scanner;

public class Main {

	public static void register() {

		UserDAO userDao = new UserDAO();
		User newUser = new User();

		Scanner scan = new Scanner(System.in);

		String user;
		String type;
		String opcType;
		int passWord;
		int passWord2;

		System.out.println("=== " + "Criar conta" + " ===");
		System.out.println("Nome de usuário: (20)");
		user = scan.nextLine();
		System.out.println("Tipo de usuário:\n1 - Aluno\n2 - Professor");
		opcType = scan.nextLine();
		System.out.println("Senha de números: (6) ");
		passWord = scan.nextInt();
		System.out.println("Repita sua senha: ");
		passWord2 = scan.nextInt();

		int opcTypeInt = Integer.parseInt(opcType);
		String stringPassWord = Integer.toString(passWord);

		if (opcTypeInt != 1 && opcTypeInt != 2) {
			System.out.println("Digite uma opção válida!");
			scan.nextLine();
			register();
		}

		// Tratando dados
		if (passWord != passWord2 || stringPassWord.length() > 6) {
			do {
				System.out.println("Digite uma senha válida!");
				System.out.println("Senha de números: (6) ");
				passWord = scan.nextInt();
				System.out.println("Repita sua senha: ");
				passWord2 = scan.nextInt();
			} while (passWord != passWord2 || stringPassWord.length() > 6);
		}

		// Setando dados na classe user
		newUser.setType(opcTypeInt == 1 ? "Aluno" : "Professor");
		newUser.setName(user);
		newUser.setPassword(passWord);

		// Salvando dados no banco de dados
		userDao.save(newUser);

		System.out.println("Seu usuário foi criado com sucesso!\n");
		scan.nextLine();
		logIn();

	}

	public static void logIn() {
		UserDAO userDao = new UserDAO();
		User newUser = new User();

		Scanner scan = new Scanner(System.in);

		String userLogin;
		int passwordLogin;

		System.out.println("=== Acessar Conta ===");
		System.out.println("Usuário: ");
		userLogin = scan.nextLine();
		System.out.println("Senha: ");
		passwordLogin = scan.nextInt();

		// variável pra saber se a senha digitada está correta
		int searchPassword = 0;
		
		//Pegando id do usuário
		int id = 0;
		
		//pegando o tipo do usuário
		String typeUser = "";

		// Buscando usuário e identificando se existe
		for (User u : userDao.searchName(userLogin)) {
			searchPassword = u.getPassword();
			id = u.getId();
			typeUser = u.getType();
		}

		if (searchPassword == passwordLogin) {
			// if pra saber se foi logado como admin ou usuário comum
			if (userLogin.toLowerCase().equals("admin")) {
				adminUser();
			} else {
				othersUser(id, typeUser);
			}
		} else {
			do {
				System.out.println("\nErro no login, repita sua senha!");

				System.out.println("Senha: ");
				passwordLogin = scan.nextInt();

				for (User u : userDao.searchName(userLogin)) {
					searchPassword = u.getPassword();
				}

			} while (searchPassword != passwordLogin);

			// if pra saber se foi logado como admin ou usuário comum
			if (userLogin.toLowerCase() == "admin") {
				adminUser();
			} else {
				othersUser(id, typeUser);
			}
		}
	}

	// Método quando loga como ADMIN
	public static void adminUser() {

		Scanner scan = new Scanner(System.in);

		int opc;

		System.out.println("\nBem vindo Admin!\nDeseja fazer qual operação?");
		System.out.println(
				"1 - incluir novos livros\n2 - Listar livros\n3 - Excluir livros\n4 - Listar usuários\n5 - Excluir usuários\n6 - Sair");
		opc = scan.nextInt();

		switch (opc) {
		case 1:
			addBook();
			break;
		case 2:
			listBooks();
			break;
		case 3:
			removeBook();
			break;
		case 4:
			listUsers();
			break;
		case 5:
			removeUsers();
			break;
		case 6:
			System.exit(0);
			break;
		default:
			System.out.println("Tente uma opção válida!");
			break;
		}

	}

	// Método quando loga como usuário comum
	public static void othersUser(int id, String typeUser) {

		Scanner scan = new Scanner(System.in);

		int opc;

		System.out.println("\nBem vindo " + typeUser + "!" + "\nDeseja fazer qual operação?");
		System.out.println(
				"1 - Retirar livro\n2 - Devolver livro\n3 - Pagar multas\n4 - Ver Valor de multas\n5 - Sair\n");
		opc = scan.nextInt();
		
		//VER ALGUMA FORMA DE SOLUCIONAR O PROBLEMA DAS MULTAS

		switch (opc) {
		case 1:
			withdrawBook(id, typeUser);
			break;
		case 2:
			giveBackBook(id);
			break;
		case 3:
			System.out.println("Pagar multas");
			break;
		case 4:
			System.out.println("Ver valor de multas");
			break;
		case 5:
			System.exit(0);
			break;
		default:
			System.out.println("Tente uma opção válida!");
			break;
		}
	}

	// // MÉTODOS PARA ADMIN

	// Método para excluir livros
	public static void removeBook() {

		BookDAO bookDao = new BookDAO();

		Scanner scan = new Scanner(System.in);

		String ID;
		String opc;

		System.out.println("=== Excluir Livro ===");
		System.out.println("ID que deseja excluir: ");
		ID = scan.nextLine();
		System.out.println("Deseja mesmo fazer essa operação? (s/n)");
		opc = scan.nextLine();

		if (opc.toLowerCase().equals("s")) {
			bookDao.removeBookById(Integer.parseInt(ID));
			scan.nextLine();
			adminUser();
		} else {
			adminUser();
		}

	}

	// Método para incluir livros
	public static void addBook() {

		Scanner scan = new Scanner(System.in);

		BookDAO bookDao = new BookDAO();
		Book book = new Book();

		String title;
		String author;
		String edition;
		String year;
		String ISBN;
		String publishingCompany;

		System.out.println("=== Adicionar Livro ===");
		System.out.println("Título: ");
		title = scan.nextLine();
		System.out.println("Autor: ");
		author = scan.nextLine();
		System.out.println("Edição: ");
		edition = scan.nextLine();
		System.out.println("Ano de lançamento: ");
		year = scan.nextLine();
		System.out.println("Editora: ");
		publishingCompany = scan.nextLine();
		System.out.println("ISBN: ");
		ISBN = scan.nextLine();

		// Transformando string em int e long
		int yearInt = Integer.parseInt(year);
		long ISBNLong = Long.parseLong(ISBN);

		book.setTitle(title);
		book.setAuthors(author);
		book.setEdition(edition);
		book.setYear(yearInt);
		book.setISBN(ISBNLong);
		book.setPublishingCompany(publishingCompany);

		bookDao.save(book);

		scan.nextLine();
		adminUser();

	}

	// Método para listar livros (todos, por títulos e pelo ISBN)
	public static void listBooks() {

		BookDAO bookDao = new BookDAO();

		Scanner scan = new Scanner(System.in);

		String opc;

		System.out.println("=== Listar Livros ===");
		System.out.println("1 - Listar todos\n2 - Buscar por título\n3 - Buscar pelo ISBN");
		opc = scan.nextLine();

		int opcInt = Integer.parseInt(opc);

		switch (opcInt) {
		case 1:
			for (Book b : bookDao.listAllBook()) {
				System.out.println("Título: " + b.getTitle());
				System.out.println("Id: " + b.getId());
				System.out.println("Autor: " + b.getAuthors());
				System.out.println("Edição: " + b.getEdition());
				System.out.println("Ano de lançamento: " + b.getYear());
				System.out.println(b.getBorrowed() == 1 ? "Emprestado: Sim\n" : "Emprestado: Não\n");
			}
			break;
		case 2:
			String title;

			System.out.println("Digite o título: ");
			title = scan.nextLine();

			for (Book b : bookDao.searchTitle(title)) {
				System.out.println("\nTítulo: " + b.getTitle());
				System.out.println("Id: " + b.getId());
				System.out.println("Autor: " + b.getAuthors());
				System.out.println("Edição: " + b.getEdition());
				System.out.println("Ano de lançamento: " + b.getYear());
				System.out.println(b.getBorrowed() == 1 ? "Emprestado: Sim\n" : "Emprestado: Não\n");
			}
			break;
		case 3:
			String ISBN;

			System.out.println("Digite o ISBN: ");
			ISBN = scan.nextLine();

			long ISBNLong = Long.parseLong(ISBN);

			for (Book b : bookDao.searchISBN(ISBNLong)) {
				System.out.println("\nTítulo " + b.getTitle());
				System.out.println("Id: " + b.getId());
				System.out.println("Autor: " + b.getAuthors());
				System.out.println("Edição: " + b.getEdition());
				System.out.println("Ano de lançamento: " + b.getYear());
				System.out.println(b.getBorrowed() == 1 ? "Emprestado: Sim\n" : "Emprestado: Não\n");
			}
			break;
		default:
			System.out.println("Escolha uma opção válida!");
			break;
		}

		scan.nextLine();

		adminUser();
	}

	// Método para listar usuários
	public static void listUsers() {

		UserDAO userDao = new UserDAO();

		Scanner scan = new Scanner(System.in);

		String opc;

		System.out.println("=== Listar usuários ===");
		System.out.println("1 - Listar todos\n2 - Listar pelo nome\n3 - Listar pelo tipo");
		opc = scan.nextLine();

		int opcInt = Integer.parseInt(opc);

		switch (opcInt) {
		case 1:
			for (User u : userDao.listAllUsers()) {
				System.out.println("\n=== " + u.getName() + " ===");
				System.out.println("Tipo: " + u.getType());
				System.out.println("Id: " + u.getId());
				System.out.println("Senha: " + u.getPassword());
			}
			break;

		case 2:

			String name;

			System.out.println("Nome: ");
			name = scan.nextLine();

			for (User u : userDao.searchName(name)) {
				System.out.println("=== " + u.getName() + " ===");
				System.out.println("Id: " + u.getId());
				System.out.println("Senha: " + u.getPassword());
			}
			break;

		case 3:
			String type;

			System.out.println("Tipo: ");
			type = scan.nextLine();

			for (User u : userDao.searchType(type)) {
				System.out.println("=== " + u.getName() + " ===");
				System.out.println("Tipo: " + u.getType());
				System.out.println("Id: " + u.getId());
				System.out.println("Senha: " + u.getPassword());
			}
			break;
		default:
			System.out.println("Escolha uma opção válida!");
			break;
		}

		scan.nextLine();
		adminUser();

	}

	// Método para remover usuários
	public static void removeUsers() {

		UserDAO userDao = new UserDAO();

		Scanner scan = new Scanner(System.in);

		String id;
		String opc;

		System.out.println("=== Excluir usuário");
		System.out.println("ID do usuário: ");
		id = scan.nextLine();
		System.out.println("Deseja mesmo fazer essa operação? (s/n)");
		opc = scan.nextLine();

		if (opc.toLowerCase().equals("s")) {

			int IdAdmin = -1;

			for (User u : userDao.searchName("admin")) {
				IdAdmin = u.getId();
			}

			// Verificando para não excluir o usuário admin
			if (IdAdmin == Integer.parseInt(id)) {
				System.out.println("Impossível deletar Admin!");
				scan.nextLine();
				adminUser();
			} else {
				userDao.removeUserById(Integer.parseInt(id));
				scan.nextLine();
				adminUser();
			}

		} else {
			adminUser();
		}

	}

	// // MÉTODOS PARA USUÁRIOS COMUM

	// Método para retirar livros
	public static void withdrawBook(int id, String typeUser) {

		BookDAO bookDao = new BookDAO();
		UserDAO userDao = new UserDAO();

		User user = new User();
		Book book = new Book();

		Scanner scan = new Scanner(System.in);
		
		String opc;

		System.out.println("Qual operação deseja fazer?");
		System.out.println("1 - Listar Livros\n2 - Retirar livro");
		opc = scan.nextLine();

		switch (Integer.parseInt(opc)) {
		case 1:
			System.out.println("\nLista de livros: ");
			for (Book b : bookDao.listAllBook()) {
				if (b.getBorrowed() == 1) {
					System.out.println("=== " + b.getTitle() + " ===");
					System.out.println("LIVRO NÃO DISPONÍVEL\n");
				} else {
					System.out.println("=== " + b.getTitle() + " ===");
					System.out.println("Id: " + b.getId());
					System.out.println("Autor: " + b.getAuthors());
					System.out.println("Edição: " + b.getEdition());
					System.out.println("Ano de lançamento: " + b.getYear() + "\n");
				}
			}
			
			scan.nextLine();
			withdrawBook(id, typeUser);
			break;
		case 2:
			
			int idBook;
			
			System.out.println("Digite o id do livro que deseja retirar: ");
			idBook = scan.nextInt();
			
			//Verificando se o livro já está emprestado
			int borrowedBook = 0;
			
			for(Book b : bookDao.searchId(idBook)) {
				borrowedBook = b.getBorrowed();
			}
			
			if(borrowedBook == 1) {
				System.out.println("\nLivro já está emprestado!\n");
				withdrawBook(id, typeUser);
			}
			
			//Buscar no banco de dados quantidade de livros emprestados por um usuário
			int borrowing = 0;
			
			for(User u : userDao.searchId(id)) {
				borrowing = u.getBorrowing();
			}
			
			//Professor pode pegar até 5 livros e aluno até 3
			if(typeUser.equals("Professor")) {
				
				//Verificando se o usuário tem mais empréstimo que pode retirar
				if(borrowing < 5) {
					
					//Atualizar banco de dados do livro para mostrar que está emprestado
					Book bookUpdate = new Book();
					
					bookUpdate.setBorrowed(1);
					
					bookDao.updateBorrowed(bookUpdate, idBook);
					
					//Atualizar banco de dados do usuário para aumentar os empréstimos
					User userUpdate = new User();
					
					userUpdate.setBorrowing(borrowing + 1);
					
					userDao.update(userUpdate, id);
					
					System.out.println("Parabéns você retirou o livro!");
				} else {
					System.out.println("Você já tem muitos empréstimos!");
				}
				
			} else if (typeUser.equals("Aluno")) {
				
				//Verificando se o usuário tem mais empréstimo que pode retirar
				if(borrowing < 3) {
					
					//Atualizar banco de dados do livro para mostrar que está emprestado
					Book bookUpdate = new Book();
					
					bookUpdate.setBorrowed(1);
					
					bookDao.updateBorrowed(bookUpdate, idBook);
					
					//Atualizar banco de dados do usuário para aumentar os empréstimos
					User userUpdate = new User();
					
					userUpdate.setBorrowing(borrowing + 1);
					
					userDao.update(userUpdate, id);
					
					System.out.println("Parabéns você retirou o livro!");
				} else {
					System.out.println("Você já tem muitos empréstimos!");
				}
			}
			
			
			try {
				
			} catch (Exception e) {
				System.out.println("Erro ao retirar esse livro!");
				System.out.print(e);
			}

			break;

		default:
			System.out.println("Digite um opção válida!");
			break;
		}
		

	}
	
	// Método para devolver livros
	public static void giveBackBook(int idUser) {
		
		BookDAO bookDao = new BookDAO();
		UserDAO userDao = new UserDAO();

		User user = new User();
		Book book = new Book();
		
		Scanner scan = new Scanner(System.in);
		
		String titleBook;
		int idBook = 0;
		
		
		try {
			
			System.out.println("\nDigite o título do livro que deseja devolver: ");
			titleBook = scan.nextLine();
			
			//Pegando o id do livro que deseja devolver
			for(Book b : bookDao.searchTitle(titleBook)) {
				idBook = b.getId();
			}
			
			System.out.println(idBook);
			
			//Devolvendo livro
			book.setBorrowed(0);
			
			bookDao.updateBorrowed(book, idBook);
			
			int borrowing = 0;
			
			//Verificando quantos livros tem emprestado o usuário
			for(User u : userDao.searchId(idUser)) {
				borrowing = u.getBorrowing();
			}
			
			user.setBorrowing(borrowing - 1);
			
			//Atualizando banco de dados do usuário (diminuindo livros emprestados)
			userDao.update(user, idUser);
			
			System.out.println("Sucesso! Livro devolvido.");
			
			
		} catch (Exception e) {
			System.out.println("Erro ao devolver o livro: " + e);
		}
		
	}

	// Método main
	public static void main(String[] args) {

		BookDAO bookDao = new BookDAO();
		UserDAO userDao = new UserDAO();

		User user = new User();
		Book book = new Book();

		Scanner scan = new Scanner(System.in);

		int opc;

		System.out.println("=== Bem vindo ao sistema ===");

		do {
			System.out.println("Deseja fazer: ");
			System.out.println("1 - LogIn \n2 - register");
			opc = scan.nextInt();
		} while (opc != 1 && opc != 2);

		if (opc == 1) {
			System.out.println("\n\n");
			logIn();
		} else if (opc == 2) {
			System.out.println("\n\n");
			register();
		}

		// Dados que são usadas para fazer o método CRUD
//		user.setName("Admin");
//		user.setPassword(111111);

		// Dados que são usados para fazer o método CRUD
//		book.setTitle("E não sobrou nenhum");
//		book.setAuthors("Ágatha Christie");
//		book.setEdition("Premium");
//		book.setYear(2015);
//		book.setISBN(9786584568013L);
//		book.setPublishingCompany("Intrínseca");

		// Salvar dados de novo usuário no banco de dados
//		userDao.save(user);

		// Salvar dados de novo livro no banco de dados
//		bookDao.save(book);

		// Listar o banco de dados de usuários
//		for (User u: userDao.listAllUsers()) {
//			System.out.println("=== Usuário ===" );
//			System.out.println("Id: " + u.getId());
//			System.out.println("Nome: " + u.getName());
//			System.out.println("Senha: " + u.getPassword());
//		}

		// Listar o banco de dados de livros
//		for(Book b : bookDao.listAllBook()) {
//			System.out.println("=== " + b.getTitle() + " ===");
//			System.out.println("Id: " + b.getId());
//			System.out.println("Autor: " + b.getAuthors());
//			System.out.println("Edição: " + b.getEdition());
//			System.out.println("Ano de lançamento: " + b.getYear() + "\n");
//		}

		// Buscar livro pelo ISBN
//		for(Book b : bookDao.searchISBN(9786589999013L)) {
//			System.out.println("=== " + b.getTitle() + " ===");
//			System.out.println("Id: " + b.getId());
//			System.out.println("Autor: " + b.getAuthors());
//			System.out.println("Edição: " + b.getEdition());
//			System.out.println("Ano de lançamento: " + b.getYear() + "\n");
//		}

		// Buscar livro pelo Título
//		for(Book b : bookDao.searchTitle("Do_mil_Ao_Milhão")) {
//			System.out.println("=== " + b.getTitle() + " ===");
//			System.out.println("Id: " + b.getId());
//			System.out.println("Autor: " + b.getAuthors());
//			System.out.println("Edição: " + b.getEdition());
//			System.out.println("Ano de lançamento: " + b.getYear() + "\n");
//		}

		// Buscar livro pela Editora
//		for(Book b : bookDao.searchPublishingCompany("intrinseca")) {
//			System.out.println("=== " + b.getTitle() + " ===");
//			System.out.println("Id: " + b.getId());
//			System.out.println("Autor: " + b.getAuthors());
//			System.out.println("Edição: " + b.getEdition());
//			System.out.println("Ano de lançamento: " + b.getYear() + "\n");
//		}

		// Buscar usuário pelo nome
//		for(User u : userDao.searchName("admin")) {
//			System.out.println("=== " + u.getName() + " ===");
//			System.out.println("Id: " + u.getId());
//			System.out.println("Senha: " + u.getPassword());
//		}
//		
		// Atualizar banco de dados de livros com id
//		Book bookUpdate = new Book();
//		bookUpdate.setId(1);
//		bookUpdate.setName("A Beleza da vida do Dianho");
//		bookUpdate.setAuthors("Sherlock");
//		bookUpdate.setEdition("Premium");
//		bookUpdate.setYear(2008);
//		
//		bookDao.update(bookUpdate);

		// Remove o livro no banco de dados com o id
//		bookDao.removeBookById(2);

		// Remove usuário no banco de dados com o id
//		userDao.removeUserById(1);

	}

}
