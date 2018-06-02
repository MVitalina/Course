package sample.Patterns.Command;

import java.util.Random;

public class GeneratorK {
    public void SetExecutor(Task k, Executor executor) {
        k.setExecutor(executor);
    }

    public String GenerateK(Task k) {
        return k.Execute();
    }
}

abstract class Executor {
    public abstract String Execute();
}

