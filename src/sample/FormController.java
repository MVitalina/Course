package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class FormController implements Initializable {
    @FXML
    private Label nickL, nameL, bornL, addressL, phoneL, authorL, titleL, isbnL;
    @FXML
    private ListView <String> listView;
    @FXML
    private Pane pane;

    private DBModel model = new DBModel();

    private String nickname;

    public void setNick(String nickname) {
        this.nickname = nickname;
    }

    private int bookId;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    private ObservableList<String> itemList;

    public void initialize(URL location, ResourceBundle resources) {}

    public void setForm(Form form) {
        nameL.setText(form.name);
        nickL.setText(form.nick);
        bornL.setText(Integer.toString(form.born));
        addressL.setText(form.address);
        phoneL.setText(form.phone);
    }

    public void setBooks(String nickname) {
        try {
            itemList = FXCollections.observableArrayList();
            System.out.println(nickname);
            for (Book b: model.getFormBookList(nickname) ) {
                itemList.add("\"" + b.title + "\" " + b.author);
            }
            listView.setItems(itemList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setBookId(indexOfBook());
                if (bookId != -1) {
                    pane.setVisible(true);
                    try {
                        Book book = model.getBookList().get(bookId);
                        titleL.setText(book.title);
                        authorL.setText(book.author);
                        isbnL.setText(book.ISBN);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else pane.setVisible(false);
            }
        });
    }

    public void GoToLibrary (ActionEvent e) throws SQLException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = null;
        try {
            root = loader.load(getClass().getResource("Library.fxml").openStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        LibraryController libController = (LibraryController)loader.getController();
        libController.setNick(this.nickname);
        primaryStage.setTitle("Бібліотека");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private int indexOfBook() {
        for (String s: itemList ) {
            if (s.equals(listView.getSelectionModel().getSelectedItem())){
                return itemList.indexOf(s);
            }
        }
        return -1;
    }
}
