package part3;

import utilits.Utilits;

import java.util.ArrayList;

import static part2.Gauss.methodGauss0;
import static part3.DifferenceMethod.*;
import static utilits.Utilits.getTable;
import static utilits.Utilits.variant;

public class UndefinedCoefficientsMethod {
    public static final double h = 0.4;
    public static final double T = variant;
    public static final int n = 10;

    public static double getFik(double xk, int k) {
        return Math.pow(xk, k) * (xk - T);
    }

    public static double getFik1(double xj, int k) {
        return (k + 1) * Math.pow(xj, k)
                - T * k * Math.pow(xj, k - 1);
    }

    public static double getFik2(double xj, int k) {
        return (k + 1) * k * Math.pow(xj, k - 1)
                - T * k * (k - 1) * Math.pow(xj, k - 2);
    }

    public static ArrayList<Double> getXWithH(int n, double h) {
        return Utilits.getXWithH(h, n, h);
    }

    public static ArrayList<Double> f(ArrayList<Double> x) {
        ArrayList<Double> f = new ArrayList<>();
        for (Double xk : x) {
            f.add(4.0 * T * xk * xk * xk * xk
                    - 3.0 * T * T * xk * xk * xk
                    + 6.0 * T * xk
                    - 2.0 * T * T);
        }
        return f;
    }

    public static ArrayList<Double> getYTochnoe(ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>();
        for (Double xk : x) {
            y.add(T * xk * xk * (xk - T));
        }
        return y;
    }

    public static ArrayList<ArrayList<Double>> getSLAY() {
        ArrayList<ArrayList<Double>> A = new ArrayList<>();
        ArrayList<Double> x = getXWithH(n, h);
        double xj;
        for (int j = 1; j <= n; j++) {
            A.add(new ArrayList<>());
            xj = x.get(j - 1);
            for (int k = 1; k <= n; k++) {
                A.get(j - 1).add(getFik2(xj, k)
                        + p(xj) * getFik1(xj, k)
                        + q(xj) * getFik(xj, k));
            }
        }
        return A;
    }

    public static ArrayList<Double> getAk(ArrayList<ArrayList<Double>> A, ArrayList<Double> f) {
        return methodGauss0(A, f);
    }

    public static ArrayList<Double> getY(ArrayList<Double> ak, ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>();
        for (double xk : x) {
            double yk = 0.0;
            for (int k = 1; k <= n; k++) {
                yk += ak.get(k - 1) * getFik(xk, k);
            }
            y.add(yk);
        }
        return y;
    }

    public static void showWork() {
        System.out.println("3.4 Метод неопределенных коэффициентов");
        ArrayList<Double> x =  getXWithH(n, h);
        ArrayList<ArrayList<Double>> A = getSLAY();
        ArrayList<Double> Ak = getAk(A, f(x));
        ArrayList<Double> y = getY(Ak, x);
        getTable(x, getYTochnoe(x), y);
    }
}
