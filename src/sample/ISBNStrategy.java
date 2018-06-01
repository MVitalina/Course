package sample;

public abstract class ISBNStrategy {
    public abstract String getISBN();
}

class newStandart extends ISBNStrategy{
    public newStandart(){}

    @Override
    public String getISBN(){
        return "978 K V N C";
    }
}

class oldStandart extends ISBNStrategy{
    public oldStandart(){}

    @Override
    public String getISBN(){
        return "K V N C";
    }
}

class Context {
    private ISBNStrategy strategy;

    public void SetStrategy(ISBNStrategy strategy){
        this.strategy = strategy;
    }

    public String getISBN(){
        return strategy.getISBN();
    }
}