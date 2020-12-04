package part4;

import java.util.ArrayList;

import static part2.Gauss.methodGauss0;
import static utilits.Utilits.getTable;

public class IntegralEquationDegenerateCore {
    private static final double a = 0.0;
    private static final double b = 1.0;
    private static final int n = 3;
    private static final int v = 4;

    public static ArrayList<Double> getX() {
        ArrayList<Double> x = new ArrayList<>();
        double i = a;
        while (i <= b) {
            x.add(i);
            i += 0.1;
        }
        return x;
    }

    public static ArrayList<Double> getF(ArrayList<Double> x) {
        ArrayList<Double> fx = new ArrayList<>();
        for (double xi : x) {
            fx.add(v * (4.0 / 3.0 * xi + 0.25 * xi * xi + 0.2 * xi * xi * xi));
        }
        return fx;
    }

    public static ArrayList<Double> getYTochnoe(ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>();
        for (double xi : x) {
            y.add(v * xi);
        }
        return y;
    }

    public static ArrayList<ArrayList<Double>> getSLAY() {
        ArrayList<ArrayList<Double>> slay = new ArrayList<>();
        slay.add(new ArrayList<>());
        slay.get(0).add(1.0 + 1.0 / 3.0);
        slay.get(0).add(0.25);
        slay.get(0).add(0.2);
        slay.get(0).add(1969.0 / 900.0);

        slay.add(new ArrayList<>());
        slay.get(1).add(0.25);
        slay.get(1).add(1.0 + 0.2 );
        slay.get(1).add(1.0 / 6.0);
        slay.get(1).add(5.0 / 3.0);

        slay.add(new ArrayList<>());
        slay.get(2).add(0.2);
        slay.get(2).add(1.0 / 6.0);
        slay.get(2).add(1.0 + 1.0 / 7.0);
        slay.get(2).add(401.0 / 210.0);
        return slay;
    }

     public static ArrayList<Double> getY(ArrayList<Double> x) {
         ArrayList<Double> y = new ArrayList<>();
         ArrayList<Double> q = methodGauss0(getSLAY());
         ArrayList<Double> f = getF(x);

         double sum;
         int j = 0;
         for (double xi : x) {
             sum = 0.0;
             for (int i = 0; i < n; i++) {
                 sum += q.get(i) * Math.pow(xi, i + 1);
             }
             y.add(f.get(j) - sum);
             j++;
         }
         return y;
    }

    public static void showWork() {
        ArrayList<Double> x =  getX();
        ArrayList<Double> y = getY(x);
        getTable(x, getYTochnoe(x), y);
    }
}
