package sample.Patterns.Bridge;

import javafx.scene.control.Alert;

public class HomeReader implements BridgeReader {
    public void getBook(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Підтвердження додавання");
        alert.setHeaderText("Підтвердіть додавання книги.");
        alert.showAndWait();
    }
}
