package sample.Patterns.Strategy;

public class oldStandart extends ISBNStrategy{
    public oldStandart(){}

    @Override
    public String getISBN(){
        return "K V N C";
    }
}
