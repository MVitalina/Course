package sample.Patterns;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Controllers.FormController;
import sample.Form;

import java.io.IOException;

abstract class Abstract_Library {
    public abstract void GoToForm(boolean go, ActionEvent e, Form form) throws IOException;
}

class Library extends Abstract_Library {
    @Override
    public void GoToForm(boolean go, ActionEvent e, Form form) throws IOException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("../View/Form.fxml").openStream());
        FormController formController = (FormController)loader.getController();
        formController.setForm(form);
        formController.setBooks(form.nick);
        formController.setNick(form.nick);
        if (form.nick != null)
            primaryStage.setTitle("Формуляр читача");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

public class ProxyLib extends Abstract_Library {
    private Library library = new Library();

    @Override
    public void GoToForm(boolean go,ActionEvent e, Form form) throws IOException {
        if (go) library.GoToForm(go, e, form);
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!!");
            alert.setHeaderText("Nick isn`t correct");
            alert.showAndWait();
        }
    }
}
