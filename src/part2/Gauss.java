package part2;

import Pair.Pair;

import java.util.ArrayList;
import java.util.Scanner;

import static utilits.Utilits.*;

public class Gauss {


    public static ArrayList<ArrayList<Double>> pryamoiHod(ArrayList<ArrayList<Double>> matrAb) {
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        int n = matrAb.size();

        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n + 1; j++) {
                res.get(i).add(matrAb.get(i).get(j));
            }
        }

        for (int k = 0; k < n; k++) {
            double del = res.get(k).get(k);
            for (int i = 0; i < n + 1; i++) {
                res.get(k).set(i, res.get(k).get(i) / del);
            }
            for (int i = k + 1; i < n; i++) {
                double mnog = res.get(i).get(k);
                for (int j = 0; j < n + 1; j++) {
                    res.get(i).set(j, res.get(i).get(j) - mnog * res.get(k).get(j));
                }
            }
        }
        return res;
    }

    public static Pair<ArrayList<Pair<Integer, Integer>>, ArrayList<ArrayList<Double>>> pryamoiHod0(ArrayList<ArrayList<Double>> matrAb) {
        int n = matrAb.size();
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> perestanovki = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n + 1; j++) {
                res.get(i).add(matrAb.get(i).get(j));
            }
        }
        int perestStrok = 0, perestStolb = 0;
        for (int k = 0; k < n; k++) {
            double del = res.get(k).get(k);
            //если элемент на главной диагонали равен нулю (с погрешностью)
            double max = del;
            int maxi = k;
            if (Math.abs(del) <= EPSILON) {
                for (int i = k + 1; i < n; i++) {
                    if (Math.abs(res.get(i).get(k)) > max) {
                        max = res.get(i).get(k);
                        maxi = i;
                    }
                }
                double tmp = 0.0;
                for (int i = 0; i <= n; i++) {
                    tmp = res.get(k).get(i);
                    res.get(k).set(i, res.get(maxi).get(i));
                    res.get(maxi).set(i, tmp);
                }
                perestStrok++;
            }
            //если в столбце все элементы оказались нулевыми, то переставляем столбцы
            if (Math.abs(max) <= EPSILON) {
                for (int i = k + 1; i < n; i++) {
                    if (Math.abs(res.get(k).get(i)) > max) {
                        max = res.get(k).get(i);
                        maxi = i;
                    }
                }
                double tmp = 0.0;
                for (int i = 0; i < n; i++) {
                    tmp = res.get(i).get(k);
                    res.get(i).set(k, res.get(i).get(maxi));
                    res.get(i).set(maxi, tmp);
                }
                perestStolb++;
                perestanovki.add(new Pair<>(k, maxi));
            }
            del = res.get(k).get(k);
            for (int i = 0; i < n + 1; i++) {
                res.get(k).set(i, res.get(k).get(i) / del);
            }
            for (int i = k + 1; i < n; i++) {
                double mnog = res.get(i).get(k);
                for (int j = 0; j < n + 1; j++) {
                    res.get(i).set(j, res.get(i).get(j) - mnog * res.get(k).get(j));
                }
            }
        }
        //System.out.println("Перестановок строк: " + perestStrok + ", перестановок столбцов: " + perestStolb);
        return new Pair<>(perestanovki, res);
    }


    public static ArrayList<Double> methodGauss(ArrayList<ArrayList<Double>> matrA, ArrayList<Double> b) {
        return methodGauss((getMatrAb(matrA, b)));
    }

    public static ArrayList<Double> methodGauss(ArrayList<ArrayList<Double>> matrAb) {
        int n = matrAb.size();
        ArrayList<ArrayList<Double>> matrGauss = pryamoiHod(matrAb);
        ArrayList<Double> xs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            xs.add(0.0);
        }

        xs.set(n - 1, matrGauss.get(n - 1).get(n));
        for (int i = n - 2; i >= 0; i--) {
            double tmp = matrGauss.get(i).get(n);
            for (int j = i + 1; j < n; j++) {
                tmp -= matrGauss.get(i).get(j) * xs.get(j);
            }
            xs.set(i, tmp);
        }
        return xs;
    }

    public static ArrayList<Double> methodGauss0(ArrayList<ArrayList<Double>> matrAb) {
        int n = matrAb.size();
        Pair<ArrayList<Pair<Integer, Integer>>, ArrayList<ArrayList<Double>>> pair = pryamoiHod0(matrAb);
        ArrayList<ArrayList<Double>> matrGauss = pair.getSecond();
        ArrayList<Double> xs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            xs.add(0.0);
        }
        xs.set(n - 1, matrGauss.get(n - 1).get(n));
        for (int i = n - 2; i >= 0; i--) {
            double tmp = matrGauss.get(i).get(n);
            for (int j = i + 1; j < n; j++) {
                tmp -= matrGauss.get(i).get(j) * xs.get(j);
            }
            xs.set(i, tmp);
        }
        ArrayList<Pair<Integer, Integer>> perestanovki = pair.getFirst();
        for (int i = perestanovki.size() - 1; i >= 0; i--) {
            int fisrt = perestanovki.get(i).getFirst();
            int second = perestanovki.get(i).getSecond();
            double tmp = xs.get(fisrt);
            xs.set(fisrt, xs.get(second));
            xs.set(second, tmp);
        }
        return xs;
    }


//[1.0, 2.5, 0.0, -0.5, 4.0, 4.0, 1.5, 0.5]
    public static void print(int n) {
        System.out.println("Матрица А:");
        ArrayList<ArrayList<Double>> A = getMatrAVariant(n);
        for (int i = 0; i < n; i++) {
            System.out.println(A.get(i));
        }
        System.out.println();
        System.out.println("Матрица d:");
        System.out.println(getStrVariant(n));
        System.out.println("Матрица b:");
        System.out.println(getMatrB(n));
        System.out.println("Матрица А|b:");
        A = getMatrAb(getMatrAVariant(n), getMatrB(n));
        for (int i = 0; i < n; i++) {
            System.out.println(A.get(i));
        }
        System.out.println();
        System.out.println("Матрица А|b после преобразований:");
        A = pryamoiHod(getMatrAb(getMatrAVariant(n), getMatrB(n)));
        for (int i = 0; i < n; i++) {
            System.out.println(A.get(i));
        }
        System.out.println();
        System.out.println("x:");
        ArrayList<Double> xs = methodGauss(getMatrAb(getMatrAVariant(n), getMatrB(n)));
        System.out.println(xs);
    }

    public static void showWork() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        ArrayList<Double> xs = methodGauss0(getMatrAb(getMatrAVariant(n), getMatrB(n)));
        System.out.println(xs);
    }
}
