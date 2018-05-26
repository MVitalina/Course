package sample;

import java.util.Date;
import java.util.LinkedList;

public class Form {
    public String name;
    public int born;
    public String nick;
    public String phone;
    public String address;
    public int bookCount;

    public Form(String name, int born, String nickname, String phone, String address, int bookCount) {
        this.name = name;
        this.born = born;
        this.nick = nickname;
        this.phone = phone;
        this.address = address;
        this.bookCount = bookCount;
    }

    public Form(String name, int born, String nickname, String phone, String address) {
        this.name = name;
        this.born = born;
        this.nick = nickname;
        this.phone = phone;
        this.address = address;
    }
}
