package sample;
import java.sql.*;

public class SqliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:LibraryDB.db");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
