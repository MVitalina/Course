package sample.Patterns.Strategy;

public class Context {
    private ISBNStrategy strategy;

    public void SetStrategy(ISBNStrategy strategy){
        this.strategy = strategy;
    }

    public String getISBN(){
        return strategy.getISBN();
    }
}
