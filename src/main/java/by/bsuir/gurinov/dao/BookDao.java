package by.bsuir.gurinov.dao;

import by.bsuir.gurinov.Serialization;
import by.bsuir.gurinov.model.Book;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BookDao implements EntityDao<Book> {
    private ArrayList<Book> books = new ArrayList<Book>();
    private Serialization<Book> serialization = new Serialization<>();

    public BookDao() {
        books = serialization.deSerialize( new TypeToken<ArrayList<Book>>() {}.getType(),"src/main/java/by/bsuir/gurinov/files/books.txt");
    }

    /**
     * @return all books.
     */
    @Override
    public ArrayList<Book> getAll() {
        return books;
    }

    /**
     * Get book by title.
     * @param name title
     * @return title list.
     */
    public ArrayList<Book> getByName(String name) {
        ArrayList<Book> arrayList = new ArrayList<Book>();
        for (Book book: books) {
            if (book.getTitle().equals(name)){
                arrayList.add(book);
            }
        }
        return arrayList;
    }

    /**
     * add book.
     * @param obj book
     */
    @Override
    public void add(Book obj) {
        books.add(obj);
        serialization.serialize(books, "src/main/java/by/bsuir/gurinov/files/books.txt");
    }

    /**
     * delete book
     * @param obj book
     */
    @Override
    public boolean delete(Book obj) {
        if(books.contains(obj)){
            books.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Find book in catalog.
     * @param title book title
     * @param author book author
     * @param type book type
     * @return book
     */
    public Book findBook(String title, String author, String type) {
        for (Book book: books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getType().equals(type)){
                return book;
            }
        }
        return null;
    }
    /**
     * Edit book in catalog.
     * @param newTitle book title
     * @param newAuthor book author
     * @param newType book type
     * @return book
     */
    public String editBook(Book book, String newTitle, String newAuthor, String newType) {
        if (books.contains(book)) {
            books.get(books.indexOf(book)).setTitle(newTitle);
            books.get(books.indexOf(book)).setAuthor(newAuthor);
            books.get(books.indexOf(book)).setType(newType);

            return "Success";
        }
        return "Book not found";
    }
}
