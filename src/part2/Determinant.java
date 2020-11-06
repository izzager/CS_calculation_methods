package part2;

import Pair.Pair;
import utilits.Utilits;

import java.util.ArrayList;
import java.util.Scanner;

import static part2.Gauss.*;
import static utilits.Utilits.*;
import static utilits.Utilits.getTransponirMatr;
import static utilits.Utilits.multMatrColumn;

public class Determinant {

    public static double getDeterminant(ArrayList<ArrayList<Double>> matr) {
        int n = matr.size();
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> perestanovki = new ArrayList<>();
        double determ = 1.0;

        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                res.get(i).add(matr.get(i).get(j));
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
                double tmp;
                for (int i = 0; i < n; i++) {
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
                double tmp;
                for (int i = 0; i < n; i++) {
                    tmp = res.get(i).get(k);
                    res.get(i).set(k, res.get(i).get(maxi));
                    res.get(i).set(maxi, tmp);
                }
                perestStolb++;
                perestanovki.add(new Pair<>(k, maxi));
            }
            del = res.get(k).get(k);
            determ *= res.get(k).get(k);
            for (int i = 0; i < n; i++) {
                res.get(k).set(i, res.get(k).get(i) / del);
            }
            for (int i = k + 1; i < n; i++) {
                double mnog = res.get(i).get(k);
                for (int j = 0; j < res.get(0).size(); j++) {
                    res.get(i).set(j, res.get(i).get(j) - mnog * res.get(k).get(j));
                }
            }
        }

        if (perestStolb + perestStrok % 2 == 1) {
            return determ * (-1);
        } else {
            return determ;
        }
    }

    public static ArrayList<ArrayList<Double>> getWithoutIJMatr(ArrayList<ArrayList<Double>> matr, int i, int j) {
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        int n = matr.size();
        for (int k = 0; k < i; k++) {
            res.add(new ArrayList<>());
            for (int l = 0; l < j; l++) {
                res.get(k).add(matr.get(k).get(l));
            }
            for (int l = j + 1; l < n; l++) {
                res.get(k).add(matr.get(k).get(l));
            }
        }
        for (int k = i + 1; k < n; k++) {
            res.add(new ArrayList<>());
            for (int l = 0; l < j; l++) {
                res.get(k - 1).add(matr.get(k).get(l));
            }
            for (int l = j + 1; l < n; l++) {
                res.get(k - 1).add(matr.get(k).get(l));
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Double>> getAlgebrDopolnMatr(ArrayList<ArrayList<Double>> matr) {
        int n = matr.size();
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 1) {
                    res.get(i).add(-getDeterminant(getWithoutIJMatr(matr, i, j)));
                } else {
                    res.get(i).add(getDeterminant(getWithoutIJMatr(matr, i, j)));
                }
            }
        }
        return res;
    }

    public static ArrayList<ArrayList<Double>> getObratnMatr(ArrayList<ArrayList<Double>> matr) {
        double det = getDeterminant(matr);
        ArrayList<ArrayList<Double>> dopoln = getAlgebrDopolnMatr(matr);
        int n = matr.size();
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                res.get(i).add(dopoln.get(i).get(j) / det);
            }
        }
        return Utilits.getTransponirMatr(res);
    }

    public static ArrayList<Double> getX(ArrayList<ArrayList<Double>> matr, ArrayList<Double> str) {
        ArrayList<ArrayList<Double>> obratMatr = getObratnMatr(matr);
        return multMatrColumn(obratMatr, str);
    }

    public static ArrayList<Double> getColumnWith1inK(int k, int n) {
        ArrayList<Double> str = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            str.add(0.0);
        }
        str.add(1.0);
        for (int i = k + 1; i < n; i++) {
            str.add(0.0);
        }
        return str;
    }

    public static ArrayList<Double> getAkColumn(ArrayList<ArrayList<Double>> matr, int k) {
        ArrayList<Double> str = getColumnWith1inK(k, matr.size());
        return methodGauss0(getMatrAb(matr, str));
    }

    public static ArrayList<ArrayList<Double>> getObrMatrMulti(ArrayList<ArrayList<Double>> matr) {
        ArrayList<ArrayList<Double>> obr = new ArrayList<>();
        int n = matr.size();
        for (int i = 0; i < n; i++) {
            obr.add(getAkColumn(matr, i));
        }
        return getTransponirMatr(obr);
    }

    public static ArrayList<Double> getXWithoutDeterm(ArrayList<ArrayList<Double>> matr, ArrayList<Double> str) {
        ArrayList<ArrayList<Double>> obratMatr = getObrMatrMulti(matr);
        return multMatrColumn(obratMatr, str);
    }

    public static void showWork() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        ArrayList<ArrayList<Double>> matrA = getMatrAVariant(n);
        ArrayList<Double> strB = getMatrB(n);
        ArrayList<ArrayList<Double>> matrAb = getMatrAb(matrA, strB);
        ArrayList<Double> xs = methodGauss0(matrAb);
        System.out.println(xs);
        System.out.println(matrA);
        System.out.println(Determinant.getDeterminant(matrA));
        System.out.println(Determinant.getObratnMatr(matrA));
        System.out.println(getObrMatrMulti(matrA));
        System.out.println("!!!!!!!НАХОЖДЕНИЕ Х ЧЕРЕЗ УМНОЖЕНИЕ НА ОБРАТНУЮ МАТРИЦУ, ПОЛУЧЕННУЮ ИЗ МИНОРОВ!!!!!!");
        System.out.println(Determinant.getX(matrA, strB));
        System.out.println("!!!!!!!НАХОЖДЕНИЕ Х ЧЕРЕЗ УМНОЖЕНИЕ НА ОБРАТНУЮ МАТРИЦУ, ПОЛУЧЕННУЮ ПОСТОЛБЦОВО!!!!!!");
        System.out.println(Determinant.getXWithoutDeterm(matrA, strB));
        System.out.println("Проверка того, что постолбцовая матрицу удовлетворяет свойству A*A-1=E и наоборот");
        System.out.println(make0(multMatrMatr(matrA, getObrMatrMulti(matrA))));
        System.out.println(make0(multMatrMatr(getObrMatrMulti(matrA), matrA)));
    }
}
