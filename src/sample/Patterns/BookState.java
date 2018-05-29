package sample.Patterns;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

abstract class State {
    boolean availability;
    abstract public void setAvailability(Label label, Button button);
}

class AvailableState extends State {
    AvailableState() {
        availability = true;
    }

    @Override
    public void setAvailability(Label label, Button button) {
        label.setText("Наявна");
        button.setDisable(false);
    }
}

class NotAvailableState extends State {
    NotAvailableState() {
        availability = false;
    }

    @Override
    public void setAvailability(Label label, Button button) {
        label.setText("Наразі книга на руках");
        button.setDisable(true);
    }
}

public class BookState {
    private AvailableState availableState = new AvailableState();
    private NotAvailableState notAvailableState = new NotAvailableState();

    private State CurrentState;

    public BookState(String state) {
        if (state.equals("not in use"))
            CurrentState = availableState;
        else
            CurrentState = notAvailableState;
    }

    public void setAvailability(Label label, Button button) throws SQLException {
        CurrentState.setAvailability(label, button);
    }
}
