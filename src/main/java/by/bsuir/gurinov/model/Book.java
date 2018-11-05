package by.bsuir.gurinov.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(isValidValue(title)){
            this.title = title;
        }else {
            this.title = "title";
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(isValidValue(author)){
            this.author = author;
        }else {
            this.author = "author";
        }
    }

    public Book(String title, String author, String type) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("Paper") || type.equals("Electronic")){
            this.type = type;
        } else {
            this.type = "Paper";
        }
    }

    @Override
    public String toString() {
        return "title='" + title + '\''
                + ", author='" + author + '\''
                + ", type='" + type + "\'\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return this.getTitle().equals(book.getTitle())
                && this.getAuthor().equals(book.getAuthor())
                && this.getType().equals(book.getType());
    }

    private boolean isValidValue(String str){
        if ((str.length() > 4) && (str.length() < 50)){
            return true;
        }
        return false;
    }
}
