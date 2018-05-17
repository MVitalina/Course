package sample;

import javafx.scene.image.Image;

public class Book {
    public String author;
    public String title;
    public Image image;
    public String ISBN;
    public String country;
    public int pages;
    public String publishing;
    public boolean availability;

    public Book(String author, String title, Image image, String ISBN, String country, int pages, String publishing, boolean availability) {
        this.author = author;
        this.title = title;
        this.image = image;
        this.ISBN = ISBN;
        this.country = country;
        this.pages = pages;
        this.publishing = publishing;
        this.availability = availability;
    }

    //TODO SetISBN
    public String SetISBN(){
        String isbn = "";
        return isbn;
    }


}
