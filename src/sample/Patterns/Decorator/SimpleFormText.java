package sample.Patterns.Decorator;

import javafx.scene.control.Label;

public class SimpleFormText implements FormText{
    @Override
    public void set(Label label) {
        label.setText("Формуляр читача");
    }
}
