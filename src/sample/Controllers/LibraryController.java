package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Book;
import sample.DBModel;
import sample.Form;
import sample.Patterns.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    private ListView <String> listView;
    @FXML
    private Pane pane;
    @FXML
    public Label titleL, authorL, isbnL, avL;
    @FXML
    public Button addB;
    @FXML
    private ChoiceBox<String> chBoxGetBook;

    private DBModel model = new DBModel();

    public ObservableList<String> itemList;

    public LibraryController() throws SQLException {
        itemList = FXCollections.observableArrayList();
        for (Book b: model.getBookList() ) {
            itemList.add("\"" + b.title + "\" " + b.author);
        }
    }

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

        listView.setItems(itemList);

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
                        chBoxGetBook.setItems(FXCollections.observableArrayList(
                                "Занести в формуляр", "Читати в залі")
                        );

                        // PATTERN STATE

                        BookState bookState = new BookState(book.availability);
                        bookState.setAvailability(avL, addB);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else pane.setVisible(false);
            }
        });
    }

    public void AddBookToForm(ActionEvent e) {
        String getBook = chBoxGetBook.getSelectionModel().getSelectedItem();

        // PATTERN BRIDGE

        if (getBook != null) {
            if (getBook.equals("Занести в формуляр")) {
                try {
                    if (model.howManyBooks(nickname) < 5) {
                        AbstReader abstReader = new AbstReader(new HomeReader());
                        abstReader.getBook();
                        model.newBookToForm(nickname, bookId + 1);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else if (getBook.equals("Читати в залі")) {
                AbstReader abstReader = new AbstReader(new LibReader());
                abstReader.getBook();
            }
        }
    }

    public void BackToForm(ActionEvent e) throws SQLException, IOException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("../View/Form.fxml").openStream());
        FormController formController = (FormController)loader.getController();
        Form f = model.getByNick(nickname);
        formController.setNick(nickname);
        formController.setForm(f);
        formController.setBooks(nickname);
        if (nickname != null)
            primaryStage.setTitle("Формуляр читача");
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