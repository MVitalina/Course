package sample.Patterns.Bridge;

import javafx.scene.control.Alert;

interface BridgeReader {
    public void getBook();
}

public class AbstReader{
    BridgeReader reader;

    public AbstReader(BridgeReader reader) {
        this.reader = reader;
    }

    public void getBook(){
        this.reader.getBook();
    }
}