package part3;

import utilits.Utilits;

import java.util.ArrayList;

import static part2.MethodProgonki.methodProgonki;
import static part3.DifferenceMethod.*;
import static utilits.Utilits.getTable;
import static utilits.Utilits.variant;

public class OdnorodnayaZadacha {
    public static final double h = 0.1;

    public static final double T = 1;
    public static final double a = 1;
    public static final double b = variant + 1;

    //z(x) = (b - a) / T * x + a
    public static double getZ(double x) {
        return (b - a) / T * x + a;
    }

    public static double getZ1() {
        return (b - a) / T;
    }

    //y(x) = V * x^3 + 1
    public static ArrayList<Double> getYTochnoe(ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>();
        for (Double xk : x) {
            y.add(variant * xk * xk * xk + 1);
        }
        return y;
    }

    //f(x) для x с номерами от 0 до n
    public static ArrayList<Double> f(ArrayList<Double> x) {
        ArrayList<Double> f = new ArrayList<>();
        for (Double xk : x) {
            f.add(4.0 * variant * xk * xk * xk * xk
                    + xk * (6 * variant + 1));
        }
        return f;
    }

    //newF(x) = f(x) - p(x)*z(x)' - q(x)*z(x)
    public static ArrayList<Double> fWithZ(ArrayList<Double> x, ArrayList<Double> f) {
        ArrayList<Double> newf = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            double xi = x.get(i);
            newf.add(f.get(i) - p(xi) * getZ1() - q(xi) * getZ(xi));
        }
        return newf;
    }

    //y(x) = y0(x) + z(x)
    public static ArrayList<Double> getY(ArrayList<Double> x, ArrayList<Double> y0) {
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            double xi = x.get(i);
            y.add(y0.get(i) + getZ(xi));
        }
        return y;
    }

    public static void showWork() {
        int n = 10;
        ArrayList<Double> x = getXWithH(n, h);
        ArrayList<ArrayList<Double>> scheme = getDifferenceScheme(n, getXWithHWithoutFirstAndLast(x), h);
        ArrayList<Double> fWithZ = fWithZ(x, f(x));
        ArrayList<Double> y0 = differenceMethod(scheme, fWithoutFirstAndLast(fWithZ));
        getTable(x, getYTochnoe(x), getY(x, y0));
    }
}
