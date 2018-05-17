package sample;
import java.sql.*;

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
}
