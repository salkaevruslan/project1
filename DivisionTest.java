import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest {
    private Division division;

    @Before
    public void initTest() {
        division = new Division();
    }

    @After
    public void afterTest() {
        division = null;
    }

    @Test
    public void testSetPoint() throws Exception {
        division.setPoint(4);
        assertEquals(4, division.getPoint());
        division.setPoint(3);
        assertEquals(3, division.getPoint());
        division.setPoint(0);
        assertEquals(0, division.getPoint());
    }

    @Test
    public void testSetMinlen() throws Exception {
        division.setMinlen(4);
        assertEquals(4, division.getMinlen());
        division.setMinlen(7);
        assertEquals(7, division.getMinlen());
        division.setMinlen(0);
        assertEquals(0, division.getMinlen());
    }

    @Test
    public void testDivide() throws Exception {
        division.setPoint(0);
        int[][] arr1 = {
                {-1, 0, 0, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, -1, 0, 0}
        };
        int[][] result1 = {{0, 0}, {2, 3}};
        division.setMinlen(0);
        boolean k = true;
        int[][] x1 = division.divide(arr1);
        if (result1.length != x1.length) k = false;
        else {
            for (int i = 0; i < x1.length; i++) {
                if ((x1[i][0] != result1[i][0]) || (x1[i][1] != result1[i][1])) k = false;
            }
        }
        assertEquals(true, k);


        int[][] arr2 = {
                {-1, 0, 0},
                {0, -1, 0},
        };
        int[][] result2 = {{0, 1}};
        division.setMinlen(0);
        k = true;
        int[][] x2 = division.divide(arr2);
        if (result2.length != x2.length) k = false;
        else {
            for (int i = 0; i < x2.length; i++) {
                if ((x2[i][0] != result2[i][0]) || (x2[i][1] != result2[i][1])) k = false;
            }
        }
        assertEquals(true, k);


        int[][] arr3 = {
                {-1, 0, 0, 0, 0, -1},
                {0, 0, 0, 0, -1, -1},
                {0, 0, -1, 0, 0, -1}
        };
        int[][] result3 = {{4, 5}};
        division.setMinlen(1);
        k = true;
        int[][] x3 = division.divide(arr3);
        if (result3.length != x3.length) k = false;
        else {
            for (int i = 0; i < x3.length; i++) {
                if ((x3[i][0] != result3[i][0]) || (x3[i][1] != result3[i][1])) k = false;
            }
        }
        assertEquals(true, k);


        division.setPoint(12);
        int[][] arr4 = {
                {-1, 17, 11},
                {0, 41, 12},
                {12, 111, 12}
        };
        int[][] result4 = {{0, 0}, {2, 2}};
        division.setMinlen(0);
        k = true;
        int[][] x4 = division.divide(arr4);
        if (result4.length != x4.length) k = false;
        else {
            for (int i = 0; i < x4.length; i++) {
                if ((x4[i][0] != result4[i][0]) || (x4[i][1] != result4[i][1])) k = false;
            }
        }
        assertEquals(true, k);


        division.setPoint(4);
        int[][] arr5 = {
                {-1, 66},
                {1, 123},
                {12, -1},
                {3, 722}
        };
        int[][] result5 = {{0, 1}};
        division.setMinlen(0);
        k = true;
        int[][] x5 = division.divide(arr5);
        if (result5.length != x5.length) k = false;
        else {
            for (int i = 0; i < x5.length; i++) {
                if ((x5[i][0] != result5[i][0]) || (x5[i][1] != result5[i][1])) k = false;
            }
        }
        assertEquals(true, k);
    }

    @Ignore
    @Test(timeout = 1000)
    public void testTime() {

    }

    public static void main(String[] args) throws Exception {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(DivisionTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }
}