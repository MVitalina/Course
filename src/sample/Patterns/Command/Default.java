package sample.Patterns.Command;

import java.util.Random;

public class Default extends Executor{
    @Override
    public String Execute(){
        Random random = new Random();
        return Integer.toString((2 + random.nextInt(8)));
    }
}
