package sample.Patterns.Command;

public class Task {
    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public String Execute(){
        return executor.Execute();
    }
}
