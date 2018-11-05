package by.bsuir.gurinov.service;

import by.bsuir.gurinov.dao.BookDao;
import by.bsuir.gurinov.model.Book;

import java.util.ArrayList;

public class BookService implements EntityService<Book> {

    private BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }

    /**
     * @return all books.
     */
    @Override
    public ArrayList<Book> getAll() {
        return bookDao.getAll();
    }

    /**
     * Get book by title.
     * @param name title
     * @return title list.
     */
    public ArrayList<Book> getByName(String name) {
        return bookDao.getByName(name);
    }

    /**
     * add book.
     * @param obj book
     */
    @Override
    public boolean add(Book obj) {
        if((obj != null) && (!bookDao.getAll().contains(obj))){
            bookDao.add(obj);
            return true;
        }
        return false;
    }

    /**
     * delete book
     */
    public String delete(String title, String author, String type) {
        Book book = bookDao.findBook(title, author, type);
        if(book != null){
            if(bookDao.delete(book)){
                return "Success";
            }
        }
        return "Incorrect values";
    }
    /**
     * Find book in catalog.
     * @param title book title
     * @param author book author
     * @param type book type
     * @return book
     */
    public Book findBook(String title, String author, String type) {
        return bookDao.findBook(title, author, type);
    }

    /**
     * Edit book in catalog.
     * @param newTitle book title
     * @param newAuthor book author
     * @param newType book type
     * @return book
     */
    public String editBook(Book book, String newTitle, String newAuthor, String newType) {
        if(isValidValue(newTitle) && isValidValue(newAuthor) && isValidValue(newType) && book != null){
            return bookDao.editBook(book, newTitle, newAuthor, newType);
        }
        return "Incorrect values";
    }

    private boolean isValidValue(String str){
        if ((str.length() > 4) && (str.length() < 50)){
            return true;
        }
        return false;
    }
}
