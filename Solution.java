
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.io.*;

import static java.lang.System.out;

public class Solution {
    public static ArrayList<Double> arr = new ArrayList<Double>();

    public static double count(String s) throws Exception {
        double a = 0;
        if ((s.contains("+")) || (s.contains("-"))) {
            if (s.lastIndexOf("+") > s.lastIndexOf("-")) {
                a = count(s.substring(0, s.indexOf("+"))) + count(s.substring(s.indexOf("+") + 1, s.length()));
            } else {
                a = count(s.substring(0, s.lastIndexOf("-"))) - count(s.substring(s.lastIndexOf("-") + 1, s.length()));
            }
        } else if ((s.contains("*")) || (s.contains("/"))) {
            if (s.lastIndexOf("*") > s.lastIndexOf("/")) {
                a = count(s.substring(0, s.indexOf("*"))) * count(s.substring(s.indexOf("*") + 1, s.length()));
            } else {
                a = count(s.substring(0, s.lastIndexOf("/"))) / count(s.substring(s.lastIndexOf("/") + 1, s.length()));
            }
        } else {
            a = arr.get(Integer.parseInt(s));
        }
        return a;
    }

    public static ArrayList<Pair<Integer, Integer>> brackets(String s) throws Exception {
        ArrayList<Integer> beg = new ArrayList<Integer>();
        ArrayList<Pair<Integer, Integer>> res = new ArrayList<Pair<Integer, Integer>>();
        char[] s1 = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s1[i] == '(') beg.add(i);
            if (s1[i] == ')') {
                int last = i;
                int start = beg.get(beg.size() - 1);
                beg.remove(beg.size() - 1);
                Pair<Integer, Integer> pair = new Pair<>(start, last);
                res.add(pair);
            }
        }
        return res;
    }

    public static double result(String s) throws Exception {
        s = "1+" + s + "-1";
        int j = 1;
        int v = 0;
        int start = -1;
        int last = -1;
        if (!s.substring(0, 0 + 1).matches("^\\D*$")) start = 0;
        while (j < s.length()) {
            if (j < s.length() - 1)
                if ((!s.substring(j, j + 1).matches("^\\D*$")) && (s.substring(j - 1, j).matches("^\\D*$"))) {
                    start = j;
                    if ((s.substring(j - 1, j).equals("-")) && (!s.substring(j - 2, j - 1).equals(")")) && (!s.substring(j - 2, j - 1).matches("^\\d*$"))) {
                        start--;
                    }
                }
            if (j == s.length() - 1) {
                if ((!s.substring(j, j + 1).matches("^\\D*$"))) {
                    start = j;
                }
                if ((s.substring(j - 1, j).equals("-")) && (!s.substring(j - 2, j - 1).equals(")")) && (!s.substring(j - 2, j - 1).matches("^\\d*$"))) {
                    start--;
                }
            }
            if ((s.substring(j, j + 1).matches("^\\D*$")) && (!s.substring(j - 1, j).matches("^\\D*$"))) {
                last = j;
                arr.add(Double.parseDouble(s.substring(start, last)));
                s = s.substring(0, start) + v + s.substring(last, s.length());
                j = 1 + start + Integer.toString(v).length();
                v++;
            } else
                j++;
        }

        if ((!s.substring(j - 1, j).matches("^\\D*$"))) {
            last = s.length();
            arr.add(Double.parseDouble(s.substring(start, last)));
            s = s.substring(0, start) + v;
            v++;
        }
        ArrayList<Pair<Integer, Integer>> t = new ArrayList<Pair<Integer, Integer>>();
        t = brackets(s);
        int[][] x = new int[t.size()][2];
        for (int i1 = 0; i1 < t.size(); i1++) {
            x[i1][0] = t.get(i1).getKey();
            x[i1][1] = t.get(i1).getValue();
        }
        int d = x.length;
        for (int i = 0; i < d; i++) {
            t = brackets(s);
            for (int c = 0; c < t.size(); c++) {
                x[c][0] = t.get(c).getKey();
                x[c][1] = t.get(c).getValue();
            }
            int st = x[0][0];
            int la = x[0][1];
            //System.out.println(st + " " + la);
            double f = count(s.substring(st + 1, la));
            arr.add(f);
            s = s.substring(0, st) + v + s.substring(la + 1, s.length());
            v++;
        }
        double a = count(s);
        //System.out.println(a);
        arr.clear();
        return a;
    }
}

