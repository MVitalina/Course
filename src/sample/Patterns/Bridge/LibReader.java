package sample.Patterns.Bridge;

import javafx.scene.control.Alert;

public class LibReader implements BridgeReader {
    public void getBook(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Читальна зала");
        alert.setHeaderText("Читання книги...");
        alert.showAndWait();
    }
}
