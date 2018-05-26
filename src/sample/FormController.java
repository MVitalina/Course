package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FormController {
    @FXML
    private Label nickL, nameL, bornL, addressL, phoneL;
    private DBModel model = new DBModel();

    private String nickname;

    public void setNick(String nickname) {
        this.nickname = nickname;
    }

    public void initialize(URL location, ResourceBundle resources) throws SQLException {
        try{
            Form form = model.getByNick(nickname);
            nameL.setText(form.name);
            nickL.setText(form.nick);
            bornL.setText(Integer.toString(form.born));
            addressL.setText(form.address);
            phoneL.setText(form.phone);
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
}
