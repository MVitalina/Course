package sample.Patterns.Decorator;

import javafx.scene.control.Label;

public interface FormText {
    void set(Label label);
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

