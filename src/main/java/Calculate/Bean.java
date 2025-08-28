package Calculate;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface Bean {
    double add(int x, int y);
    double subtract(int x, int y);
    double multiply(int x, int y);
    double divide(int x, int y);
    List<String> getHistory();
}
