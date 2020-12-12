package part3;

import utilits.Utilits;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static part2.MethodProgonki.methodProgonki;
import static utilits.Utilits.*;

public class DifferenceMethod {
    public static final double h = 0.4;
    public static final double T = variant;

    public static ArrayList<Double> getXWithH(int n, double h) {
        //x с номерами от 0 до n
        return Utilits.getXWithH(0.0, n + 1, h);
    }

    //x с номерами от 1 до n - 1
    public static ArrayList<Double> getXWithHWithoutFirstAndLast(ArrayList<Double> x) {
        return IntStream
                .range(1, x.size() - 1)
                .mapToObj(x::get)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //f(x) для x с номерами от 0 до n
    public static ArrayList<Double> f(ArrayList<Double> x) {
        ArrayList<Double> f = new ArrayList<>();
        for (Double xk : x) {
            f.add(4.0 * variant * xk * xk * xk * xk
                    - 3.0 * variant * T * xk * xk * xk
                    + 6.0 * variant * xk
                    - 2.0 * variant * T);
        }
        return f;
    }

    //f(x) для x с номерами от 1 до n-1
    public static ArrayList<Double> fWithoutFirstAndLast(ArrayList<Double> f) {
        return IntStream
                .range(1, f.size() - 1)
                .mapToObj(f::get)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static double p(double x) { return x * x; }

    public static double q(double x) { return x; }

    public static ArrayList<ArrayList<Double>> getDifferenceScheme(int n, ArrayList<Double> x, double h) {
        //разностная схема строится x с номерами от 1 до n-1
        //ArrayList<Double> x = getXWithHWithoutFirstAndLast(n);
        n--; //т.к. n-1
        ArrayList<ArrayList<Double>> scheme = new ArrayList<>();

        // заполняю первую строку трехдиагональной матрицы
        scheme.add(new ArrayList<>());
        scheme.get(0).add(- 2.0 / h / h + q(x.get(0)));
        scheme.get(0).add(1.0 / h / h + p(x.get(0)) / 2.0 / h);
        for (int i = 2; i < n; i++) {
            scheme.get(0).add(0.0);
        }

        //заполняю со 2 по n-1 строки матрицы
        for (int i = 1; i < n - 1; i++) {
            scheme.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    scheme.get(i).add(- 2.0 / h / h + q(x.get(i)));
                } else if (j == i - 1) {
                    scheme.get(i).add(1.0 / h / h - p(x.get(i)) / 2.0 / h);
                } else if (j == i + 1) {
                    scheme.get(i).add(1.0 / h / h + p(x.get(i)) / 2.0 / h);
                } else {
                    scheme.get(i).add(0.0);
                }
            }
        }

        // заполняю n-ную строку трехдиагональной матрицы
        scheme.add(new ArrayList<>());
        for (int i = 0; i < n - 2; i++) {
            scheme.get(n - 1).add(0.0);
        }
        scheme.get(n - 1).add(1.0 / h / h - p(x.get(n - 1)) / 2.0 / h);
        scheme.get(n - 1).add(- 2.0 / h / h + q(x.get(n - 1)));

        return scheme;
    }

    public static ArrayList<Double> differenceMethod(ArrayList<ArrayList<Double>> scheme,
                                                     ArrayList<Double> f) {
        ArrayList<Double> y = methodProgonki(scheme, f);
        //по условию y0=yn=0
        y.add(0, 0.0);
        y.add(0.0);
        return y;
    }

    public static ArrayList<Double> getYTochnoe(ArrayList<Double> x) {
        ArrayList<Double> y = new ArrayList<>();
        for (Double xk : x) {
            y.add(variant * xk * xk * (xk - T));
        }
        return y;
    }

    public static void showWork() {
        System.out.println("3.2 Разностный метод решения краевой задачи для ОДУ второго порядка");
        int n = 10;
        ArrayList<Double> x = getXWithH(n, h);
        ArrayList<ArrayList<Double>> scheme = getDifferenceScheme(n, getXWithHWithoutFirstAndLast(x), h);
        ArrayList<Double> f = fWithoutFirstAndLast(f(x));
        getTable(x, getYTochnoe(x), differenceMethod(scheme, f));
    }

}
