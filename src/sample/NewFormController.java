package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewFormController implements Initializable {
    @FXML
    private TextField nick, name, born, address, phone;
    private DBModel model = new DBModel();

    public void initialize(URL location, ResourceBundle resources) {
        /*if (model.isDbConnected()) {
        } else {
        }*/
    }

    public void isUnique (ActionEvent e) {
        //TODO isUnique
    }

    public void addForm (ActionEvent e) {
        try {
            if (model.isUnique(nick.getText())) {
                int bornint = 0;
                try { bornint = Integer.parseInt(born.getText()); } catch (Exception e2) {}
                model.newForm(nick.getText(), name.getText(), bornint, address.getText(), phone.getText());
                ((Node)e.getSource()).getScene().getWindow().hide();
            } else {
                System.out.println("not unique");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}