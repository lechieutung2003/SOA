package Calculate;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless(name = "StatelessBean")
public class StatelessBean implements Bean {

    @Override
    public double add(int x, int y) {
        return x + y;
    }

    @Override
    public double subtract(int x, int y) {
        return x - y;
    }

    @Override
    public double multiply(int x, int y) {
        return x * y;
    }

    @Override
    public double divide(int x, int y) {
        if (y == 0) throw new ArithmeticException("Divide by zero");
        return (double) x / y;
    }
    
    @Override
    public List<String> getHistory() {
        return new ArrayList<>(); // hoặc Collections.emptyList()
    }
}
