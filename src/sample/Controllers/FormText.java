package sample.Controllers;

import javafx.scene.control.Label;
import sample.Form;

public interface FormText {
    void set(Label label);
}

class SimpleFormText implements FormText{
    @Override
    public void set(Label label) {
        label.setText("Формуляр читача");
    }
}

abstract class Decorator implements FormText{
    protected FormText decoratedText;

    public Decorator(FormText decoratedText){
        this.decoratedText = decoratedText;
    }

    public void set(Label label){
        decoratedText.set(label);
    }
}

class BorderDecorator extends Decorator{

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