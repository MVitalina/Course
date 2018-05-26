package sample;

public class Book {
    public String author;
    public String title;
    public String ISBN;
    public String country;
    public String language;
    public int pages;
    public String publishing;
    public String availability;
    public int id;

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

    //TODO SetISBN
    public String SetISBN(){
        String isbn = "";
        return isbn;
    }


}
