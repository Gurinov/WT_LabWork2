package by.bsuir.gurinov.view;

import by.bsuir.gurinov.controller.BookController;
import by.bsuir.gurinov.controller.UserController;
import by.bsuir.gurinov.model.User;

import java.util.Scanner;

public final class Console {

	private Console() {}

	private static final Console instance = new Console();

	public static Console getInstance() {
		return instance;
	}

	private UserController userController = new UserController();
	private BookController bookController = new BookController();

	private Scanner input = new Scanner(System.in);

	/**
	 * @return user
	 */
	public User getUserInfo(){
	    return userController.getUser();
    }

	/**
	 * show User Actions
	 */
	public void showUserActions() {
		System.out.println("\n");
		System.out.println("Sign In: 1");
		System.out.println("SignUp: 2");
		System.out.print("> ");

		String command = input.next();

		if (command.contentEquals("1")) {
			this.signIn();
		} else if (command.contentEquals("2")){
			this.signUp();
		}
	}

	/**
	 * show User Actions after authorization
	 */
	public void showBookActions() {
		System.out.println("\n");
		System.out.println("Find books by title: 1");
		System.out.println("View catalog: 2");
        System.out.println("Sign Out: 3");
        if(getUserInfo().isAdmin()){
            System.out.println("-------------------------");
            System.out.println("Add new book: 4");
            System.out.println("Delete book: 5");
            System.out.println("Edit book: 6");
        }

		System.out.print("> ");

		String command = input.next();

		if (command.contentEquals("1")) {
			this.findBook();
		} else if (command.contentEquals("2")) {
			this.getAll();
		} else if (command.contentEquals("3")) {
            this.signOut();
        } else
            if(getUserInfo().isAdmin()){
                if (command.contentEquals("4")) {
                    this.add();
                } else if (command.contentEquals("5")) {
                    this.delete();
                } else if (command.contentEquals("6")) {
                    this.edit();
                }
            }
	}

	public void findBook() {
        System.out.print("Title: ");
        String title = input.next();

        System.out.println(bookController.findBook(title));
	}

	public void getAll() {
        System.out.println(bookController.viewCatalog());
	}

	public void add() {
		System.out.print("Title: ");
		String title = input.next();
        System.out.print("Author: ");
        String author = input.next();
		System.out.print("Type: ");
		String type = input.next();

        System.out.println(bookController.addBook(title, author, type));
	}

    public void delete() {
        System.out.print("Title: ");
        String title = input.next();
        System.out.print("Author: ");
        String author = input.next();
        System.out.print("Type: ");
        String type = input.next();

        System.out.println(bookController.deleteBook(title, author, type));
    }

    public void edit() {
        System.out.print("Title: ");
        String title = input.next();
        System.out.print("Author: ");
        String author = input.next();
        System.out.print("Type: ");
        String type = input.next();

        System.out.println(bookController.editBook(title, author, type));
    }

	public void signIn() {
		String username;
		String password;

		System.out.print("Username: ");
		username = input.next();
		System.out.print("Password: ");
		password = input.next();

		userController.signIn(username, password);
	}

	public void signUp() {
		String username;
		String password;

		System.out.println("Username:");
		username = input.next();
		System.out.println("Password:");
		password = input.next();

		userController.signUp(username, password);
	}

	public void signOut() {
		userController.signOut();
	}

    public void start() {
        while (true) {
            if (getUserInfo() != null) {
                showBookActions();
            }
            else {
                showUserActions();
            }
        }
    }
}
