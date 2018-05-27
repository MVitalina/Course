package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    private ListView <String> listView;

    private DBModel model = new DBModel();

    private ObservableList<String> itemList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            itemList = FXCollections.observableArrayList();
            for (Book b: model.getBookList() ) {
                itemList.add("\"" + b.title + "\" " + b.author);
            }
            listView.setItems(itemList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getBookStringList(LinkedList<Book> books) {
        LinkedList<String> stringList = new LinkedList<String>();
        for (Book b: books ) {
            stringList.add("\"" + b.title + "\"" + b.author);
        }
        return stringList;
    }
}
