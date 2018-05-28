package sample;
import java.sql.*;
import java.util.LinkedList;

public class DBModel {
    Connection connection;
    public DBModel () {
        connection = SqliteConnection.Connector();
        if (connection == null) {
            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public Form getByNick(String nickname) throws SQLException {
        String query = "SELECT * FROM Forms WHERE nickname = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nickname);
        ResultSet rs = ps.executeQuery();
        while ( rs.next() ) {
            String name = rs.getString("name");
            int born = rs.getInt("born");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            rs.close();
            ps.close();
            return new Form(name, born, nickname, phone, address);
        }
        return null;
    }

    public LinkedList<Book> getBookList() throws SQLException {
        LinkedList <Book> bookList = new LinkedList<Book> ();
        String query = "SELECT * FROM Books";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while ( rs.next() ) {
            String author = rs.getString("author");
            String title = rs.getString("title");
            String isbn = rs.getString("isbn");
            String country = rs.getString("country");
            String language = rs.getString("language");
            int pages = rs.getInt("pages");
            String publishing = rs.getString("publishing");
            String availability = rs.getString("availability");
            int id = rs.getInt("id");
            bookList.add(new Book(author, title, isbn, country, language, pages, publishing, availability, id));
        }
        rs.close();
        ps.close();
        return bookList;
    }

    public int howManyBooks(String nickname) throws SQLException {
        PreparedStatement ps = null;
        String query = "SELECT * FROM Books WHERE availability = ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, nickname);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while ( rs.next() ) {
            count++;
        }
        rs.close();
        ps.close();
        return count;
    }

    public void newBookToForm(String nickname, int bookId) throws SQLException {
        PreparedStatement ps = null;
        String query = "UPDATE Books SET availability = ? WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, nickname);
        ps.setInt(2, bookId );
        ps.execute();
        ps.close();
    }

    public boolean isLogin(String nickname) throws SQLException {
        PreparedStatement ps = null;
        String query = "SELECT * FROM Forms WHERE nickname = ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, nickname);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ps.close();
            assert rs != null;
            rs.close();
        }
    }

    public void newForm(String username, String name, int born, String address, String phone) throws SQLException {
        String query = "INSERT INTO Forms(nickname , name, born, address, phone) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, name);
        ps.setInt(3, born);
        ps.setString(4, address);
        ps.setString(5, phone);
        try {
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isUnique(String nickname) throws SQLException {
        String query = "SELECT CASE WHEN EXISTS(SELECT nickname FROM Forms WHERE nickname = ?) THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nickname);
        try {
            ResultSet rs = ps.executeQuery();
            return !(rs.getBoolean(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ps.close();
        }
        return false;
    }
}
