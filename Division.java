
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Division {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        setMinlen(1);
        setPoint(0);
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = in.nextInt();
        int[][] res = divide(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i][0] + " " + res[i][1]);
        }
    }


    /*в код вставить вот это*/
    public static int minlen = 1; //минимальная длина отрезка(чтобы не брал точки)
    public static int point = 0;//число по которому определяем цвет

    public static void setMinlen(int i) {
        minlen = i;
    }

    public static void setPoint(int i) {
        point = i;
    }

    public static int getMinlen() {
        return minlen;
    }

    public static int getPoint() {
        return point;
    }

    public static int[][] divide(int[][] arr) { //arr-массив значений
        ArrayList<String> list = new ArrayList<String>();
        String s = "";
        int start = -1;
        int last = -1;
        int sold = -1;
        int lold = -1;
        for (int i = 0; i < arr[0].length; i++) {
            boolean k = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i] < point) k = true;
            }
            if (k) {
                if (start == sold) start = i;
                else last = i;
            } else {
                if (sold != start) {
                    if (last == lold) last = start;
                    s = start + " " + last;
                    if (Math.abs(last - start) >= minlen) {
                        list.add(s);
                    }
                    sold = start;
                    lold = last;
                }
            }
        }
        if (sold != start) {
            if (last == lold) s = start + " " + start;
            else s = start + " " + last;
            if (Math.abs(last - start) >= minlen) {
                list.add(s);
                System.out.println(start + " " + last + " " + minlen);
            }
        }
        String[] sar = {}; // конвертируем ArrayList в массив
        sar = list.toArray(new String[list.size()]);
        int[][] res = new int[sar.length][2];
        for (int i = 0; i < sar.length; i++) {
            int t = sar[i].indexOf(" ");
            res[i][0] = Integer.parseInt(sar[i].substring(0, t));
            res[i][1] = Integer.parseInt(sar[i].substring(t + 1, sar[i].length()));
        }
        return res;
    }
}
