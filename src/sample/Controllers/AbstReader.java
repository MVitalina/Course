package sample.Controllers;

import javafx.scene.control.Alert;

interface BridgeReader {
    public void getBook();
}

class LibReader implements BridgeReader {
    public void getBook(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Читальна зала");
        alert.setHeaderText("Читання книги...");
        alert.showAndWait();
    }
}

class HomeReader implements BridgeReader {
    public void getBook(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Підтвердження додавання");
        alert.setHeaderText("Підтвердіть додавання книги.");
        alert.showAndWait();
    }
}

public class AbstReader{
    BridgeReader reader;

    public AbstReader(BridgeReader reader) {
        this.reader = reader;
    }

    public void getBook(){
        this.reader.getBook();
    }
}