package sample.Patterns.Decorator;

import javafx.scene.control.Label;

public class BorderDecorator extends Decorator{

    public BorderDecorator(FormText decoratedText) {
        super(decoratedText);
    }

    @Override
    public void set(Label label) {
        decoratedText.set(label);
        setBorder(decoratedText, label);
    }

    private void setBorder(FormText decoratedText, Label label) {
        label.setStyle("-fx-border-color: black;");
    }
}
