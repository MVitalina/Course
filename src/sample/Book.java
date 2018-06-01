package sample;

import sample.Patterns.BookPrototype;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.SetISBN();
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
        this.SetISBN();
    }

    @Override
    public void SetISBN(){

        // PATTERN STRATEGY

        Context context = new Context();
        context.SetStrategy(new newStandart());
        String isbn = context.getISBN();

        // PATTERN COMMAND

        GeneratorK generator = new GeneratorK();
        Task gen = new Task();
        switch (this.country) {
            case "England":
            case "America": {
                EnglishSpeaking k = new EnglishSpeaking();
                generator.SetExecutor(gen, k);
                break;
            }
            case "Ukraine": {
                Ukraine k = new Ukraine();
                generator.SetExecutor(gen, k);
                break;
            }
            default: {
                Default k = new Default();
                generator.SetExecutor(gen, k);
                break;
            }
        }
        isbn = isbn.replace("K", generator.GenerateK(gen));

        Random random = new Random();
        isbn = isbn.replace("V", Integer.toString((1 + random.nextInt(999))));
        isbn = isbn.replace("N", Integer.toString((1 + random.nextInt(99999))));
        isbn = isbn.replace("C", GetCtrlCode(isbn));
        this.ISBN = isbn;
    }

    private String GetCtrlCode(String isbn){
        int ctrlInt;
        int sum = 0;
        Pattern pDig = Pattern.compile("([0-9])");
        Matcher mDig = pDig.matcher(isbn);
        int i = 1;
        while (mDig.find()){
            sum += Integer.parseInt(mDig.group())*i;
            i++;
        }
        ctrlInt = sum % 11;
        if (ctrlInt == 10) return "X";
        return Integer.toString(ctrlInt);
    }
}
