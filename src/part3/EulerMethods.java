package part3;

import java.util.ArrayList;

import static utilits.Utilits.variant;

public class EulerMethods {
    public static final double h = 1.0;

    public static double f(double x, double y) {
        return 2 * variant * x + variant * x * x - y;
    }

    public static ArrayList<Double> getYTochonoe(double x0, int n) {
        ArrayList<Double>  y = new ArrayList<>();
        y.add(variant * x0 * x0);
        double xk = x0;
        for (int i = 2; i <= n; i++) {
            xk += h;
            y.add(variant * xk * xk);
        }
        return y;
    }

    public static ArrayList<Double> getX(double x0, int n) {
        ArrayList<Double> x = new ArrayList<>();
        double xk = x0;
        for (int i = 1; i <= n; i++) {
            x.add(xk);
            xk += h;
        }
        return x;
    }

    public static ArrayList<Double> methodEuler(double x0, double y0, int n) {
        ArrayList<Double>  y = new ArrayList<>();
        y.add(y0);
        double xk = x0;
        for (int i = 1; i < n; i++) {
            double yk = y.get(i - 1);
            y.add(yk + h * f(xk, yk));
            xk += h;
        }
        return y;
    }

    public static ArrayList<Double> upgradeMethodEuler(double x0, double y0, int n) {
        ArrayList<Double>  y = new ArrayList<>();
        y.add(y0);
        double xk = x0;
        for (int i = 1; i < n; i++) {
            double yk = y.get(i - 1);
            y.add(yk + h * f(xk + h / 2.0, yk + h / 2.0 * f(xk, yk)));
            xk += h;
        }
        return y;
    }

    public static ArrayList<Double> methodPreditorCorrector(double x0, double y0, int n) {
        ArrayList<Double>  y = new ArrayList<>();
        y.add(y0);
        double xk = x0;
        for (int i = 1; i < n; i++) {
            double yk = y.get(i - 1);
            y.add(yk + h / 2.0 * (f(xk, yk) + f(xk + h, yk + h * f(xk, yk))));
            xk += h;
        }
        return y;
    }

    public static ArrayList<Double> getE(ArrayList<Double> yt, ArrayList<Double> y) {
        ArrayList<Double> e = new ArrayList<>();
        for (int i = 0; i < yt.size(); i++) {
            e.add(Math.abs(yt.get(i) - y.get(i)));
        }
        return e;
    }

    public static void getTable(ArrayList<Double> x, ArrayList<Double> yt, ArrayList<Double> y) {
        System.out.print("x:     ");
        for (double xs : x) {
            System.out.format("%8.3f", xs);
        }
        System.out.println();
        System.out.print("y мет: ");
        for (double ys : y) {
            System.out.format("%8.3f", ys);
        }
        System.out.println();
        System.out.print("y точн:");
        for (double ys : yt) {
            System.out.format("%8.3f", ys);
        }
        System.out.println();
        System.out.print("e:     ");
        ArrayList<Double> e = getE(yt, y);
        for (double es : e) {
            System.out.format("%8.3f", es);
        }
        System.out.println();
        System.out.println();
    }
}
