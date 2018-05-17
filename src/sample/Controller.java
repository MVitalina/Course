package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label label1;
    public void SetLabelText(ActionEvent e) {
        label1.setText("pasha pidor");
        System.out.println("jhgvugtv");
    }
}
