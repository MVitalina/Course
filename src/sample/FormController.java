package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FormController implements Initializable {
    @FXML
    private Label nickL, nameL, bornL, addressL, phoneL;
    private DBModel model = new DBModel();

    private String nickname;

    public void setNick(String nickname) {
        this.nickname = nickname;
    }

    public void initialize(URL location, ResourceBundle resources) {
        try{
            Form form = model.getByNick(nickname);
            /*nameL.setText(form.name);
            nickL.setText(form.nick);
            bornL.setText(Integer.toString(form.born));
            addressL.setText(form.address);
            phoneL.setText(form.phone);*/
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void setForm(Form form) {
        nameL.setText(form.name);
        nickL.setText(form.nick);
        bornL.setText(Integer.toString(form.born));
        addressL.setText(form.address);
        phoneL.setText(form.phone);
    }

    public void GoToLibrary (ActionEvent e) throws SQLException {
        //if () { todo less than 5 books
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = null;
        try {
            root = loader.load(getClass().getResource("Library.fxml").openStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        LibraryController libController = (LibraryController)loader.getController();
        primaryStage.setTitle("Бібліотека");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
