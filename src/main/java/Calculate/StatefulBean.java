package Calculate;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful(name = "StatefulBean")
public class StatefulBean implements Bean, Serializable {
    private static final long serialVersionUID = 1L;

    private final List<String> history = new ArrayList<>();
    private Double accumulatedResult = null;

    @Override
    public double add(int x, int y) {
        double current = x + y;
        if (accumulatedResult == null) {
            accumulatedResult = current;
        } else {
            accumulatedResult += current;
        }
        history.add(x + " + " + y + " = " + current);
        return accumulatedResult;
    }

    @Override
    public double subtract(int x, int y) {
        double current = x - y;
        if (accumulatedResult == null) {
            accumulatedResult = current;
        } else {
            accumulatedResult += current;
        }
        history.add(x + " - " + y + " = " + current);
        return accumulatedResult;
    }

    @Override
    public double multiply(int x, int y) {
        double current = x * y;
        if (accumulatedResult == null) {
            accumulatedResult = current;
        } else {
            accumulatedResult += current;
        }
        history.add(x + " * " + y + " = " + current);
        return accumulatedResult;
    }

    @Override
    public double divide(int x, int y) {
        if (y == 0) throw new ArithmeticException("Divide by zero");
        double current = (double) x / y;
        if (accumulatedResult == null) {
            accumulatedResult = current;
        } else {
            accumulatedResult += current;
        }
        history.add(x + " / " + y + " = " + current);
        return accumulatedResult;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }
}
