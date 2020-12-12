package part4;

import java.util.ArrayList;

import static part2.Gauss.methodGauss0;
import static utilits.Utilits.getTable;

public class QudraturMethod {
    private static final double a = 0.0;
    private static final double b = 1.0;
    private static final int n = 10;
    private static final int v = 4;
    private static final double h = 0.1;

    public static double getAkj(double xk, double tj) {
        return xk * tj
                + xk * xk * tj * tj
                + xk * xk * xk * tj * tj * tj;
    }

    public static ArrayList<Double> getX() {
        ArrayList<Double> x = new ArrayList<>();
        double i = a + h;
        while (i <= b) {
            x.add(i);
            i += h;
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

    public static ArrayList<ArrayList<Double>> getSLAY(ArrayList<Double> x, ArrayList<Double> f) {
        ArrayList<ArrayList<Double>> slay = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            slay.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                slay.get(k).add(h * getAkj(x.get(k), x.get(j)));
            }
            slay.get(k).set(k, slay.get(k).get(k) + 1.0);
            slay.get(k).add(f.get(k));
        }
        return slay;
    }

    public static ArrayList<Double> getY(ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> f = getF(x);
        ArrayList<Double> q = methodGauss0(getSLAY(x, f));
        double sum;
        int j = 0;
        for (double xk : x) {
            sum = 0.0;
            for (int k = 0; k < n; k++) {
                sum += h * q.get(k) * getAkj(xk, x.get(k));
            }
            y.add(f.get(j) - sum);
            j++;
        }
        return y;
    }

    public static void showWork() {
        System.out.println("4.2 Квадратурный метод решения интегрального уравнения Фредгольма");
        ArrayList<Double> x =  getX();
        ArrayList<Double> y = getY(x);
        getTable(x, getYTochnoe(x), y);
    }
}
