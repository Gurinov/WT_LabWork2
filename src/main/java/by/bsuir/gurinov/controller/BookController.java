package by.bsuir.gurinov.controller;

import by.bsuir.gurinov.model.Book;
import by.bsuir.gurinov.service.BookService;

import java.util.Scanner;

public class BookController {

    private BookService bookService;

    public BookController() {
        bookService = new BookService();
    }

    /**
     * View all books.
     * @return response.
     */
    public String viewCatalog(){
        String result = "";
        for (Book book: bookService.getAll()) {
            result += book.toString();
        }
        if (result.length() > 0){
            return result;
        }
        return "Please login";
    }

    /**
     * View 1 books.
     * @param title books title.
     * @return response.
     */
    public String findBook(String title){
        String result = "";
        for (Book book: bookService.getByName(title)) {
                result += book.toString();
        }
        if (result.length() > 0){
            return result;
        }
        return "Please login";
    }

    /**
     * Edit book.
     * @param title books title.
     * @param author books author.
     * @param type books type.
     * @return response.
     */
    public String editBook(String title, String author, String type){
        String result = "";
        Book book = bookService.findBook(title, author, type);
        if (book != null){
            Scanner input = new Scanner(System.in);
            String newTitle = input.nextLine();
            String newAuthor = input.nextLine();
            String newType = input.nextLine();

            return bookService.editBook(book, newTitle, newAuthor, newType);
        }
        return "Book not found";
    }

    /**
     * Delete book.
     * @param title books title.
     * @param author books author.
     * @param type books type.
     */
    public String deleteBook(String title, String author, String type){
        String result = "";
        return bookService.delete(title, author, type);
    }

    /**
     * Add book.
     * @param title books title.
     * @param author books author.
     * @param type books type.
     */
    public String addBook(String title, String author, String type){
        bookService.add(new Book(title, author, type));
        return "Success";
    }
}
