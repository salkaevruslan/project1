
import javafx.util.Pair;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void count() {
        try {
            assertEquals(3, Solution.count("1+2"));
            assertEquals(6, Solution.count("5-3+2*2"));
            assertEquals(6, Solution.count("1-1-1-1*3+2*10/2"));
            assertEquals(0, Solution.count("0/11"));
            assertEquals(12, Solution.count("11-23+24/3*3/3*3"));
        } catch (Exception e) {

        }
    }

    @Test
    void brackets() {
        try {
            String s = "";
            int x = 0;
            ArrayList<Pair<Integer, Integer>> t = new ArrayList<Pair<Integer, Integer>>();
            s = "(1+2)";
            t = Solution.brackets(s);
            x = t.get(0).getKey();
            assertEquals(0, x);
            x = t.get(0).getValue();
            assertEquals(4, x);
            s = "(((1+2)+(2*3)))";
            t = Solution.brackets(s);
            x = t.get(0).getKey();
            assertEquals(2, x);
            x = t.get(0).getValue();
            assertEquals(6, x);
        } catch (Exception e) {

        }
    }

    @Test
    void result() {
        try {
            assertEquals(2.0, Solution.result("1+1*1"));
            assertEquals(0.0, Solution.result("0*(1*3-1)"));
            assertEquals(2.0, Solution.result("(1+1)-(2*4-(1+1))/6+1"));
            assertEquals(7.0, Solution.result("((2-1)+(2*3))"));
        } catch (Exception e) {

        }

    }
}