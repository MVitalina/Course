package sample;

import java.util.Random;

public class GeneratorK {
    public void SetExecutor(Task k, Executor executor) {
        k.setExecutor(executor);
    }

    public String GenerateK(Task k) {
        return k.Execute();
    }
}

class Task {
    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public String Execute(){
        return executor.Execute();
    }
}

abstract class Executor {
    public abstract String Execute();
}

class EnglishSpeaking extends Executor{
    @Override
    public String Execute(){
        return "1";
    }
}

class Ukraine extends Executor{
    @Override
    public String Execute(){
        return "5";
    }
}

class Default extends Executor{
    @Override
    public String Execute(){
        Random random = new Random();
        return Integer.toString((2 + random.nextInt(8)));
    }
}