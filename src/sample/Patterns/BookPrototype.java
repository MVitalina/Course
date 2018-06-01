package sample.Patterns;

import sample.Book;

public abstract class BookPrototype implements Cloneable {

    abstract public String SetISBN();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public static Book getBook() {
        Book b = new Book();
        return (Book) b.clone();
        //return (Book) this.clone();
    }
}