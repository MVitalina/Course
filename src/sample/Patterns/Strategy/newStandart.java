package sample.Patterns.Strategy;

public class newStandart extends ISBNStrategy{
    public newStandart(){}

    @Override
    public String getISBN(){
        return "978 K V N C";
    }
}
