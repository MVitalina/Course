package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    private ListView <String> listView;
    @FXML
    private Pane pane;
    @FXML
    private Label titleL, authorL, isbnL, avL;

    private DBModel model = new DBModel();

    private ObservableList<String> itemList;

    private int bookId;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    private String nickname;

    public void setNick(String nickname) {
        this.nickname = nickname;
    }

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
                        if (book.availability.equals("not in use")) {
                            avL.setText("Наявна");
                        } else {
                            avL.setText("Наразі книга на руках");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void AddBookToForm(ActionEvent e) {
        try {
            if (model.howManyBooks(nickname) < 5) {
                model.newBookToForm(nickname, bookId+1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
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
