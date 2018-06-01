package sample;

import sample.Patterns.BookPrototype;

public class Book  extends BookPrototype{
    public String author;
    public String title;
    public String ISBN;
    public String country;
    public String language;
    public int pages;
    public String publishing;
    public String availability;
    public int id;

    public Book() {}

    public Book(String author, String title, String ISBN, String country, String language, int pages, String publishing, String availability, int id) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.country = country;
        this.language = language;
        this.pages = pages;
        this.publishing = publishing;
        this.availability = availability;
        this.id = id;
    }

    public void setBook(String author, String title, String ISBN, String country, String language, int pages, String publishing, String availability, int id) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.country = country;
        this.language = language;
        this.pages = pages;
        this.publishing = publishing;
        this.availability = availability;
        this.id = id;
    }

    //TODO SetISBN
    @Override
    public String SetISBN(){
        String isbn = "";
        return isbn;
    }
}
