package part1;

import Pair.Pair;

import java.util.ArrayList;

import static part2.Gauss.methodGauss0;
import static utilits.Utilits.readF;

public class Splain {
    //кубические сплайны
    public static ArrayList<ArrayList<Double>> getSLAYSplain(ArrayList<Pair<Double, Double>> f) {
        ArrayList<ArrayList<Double>> matr = new ArrayList<>();
        ArrayList<Double> free = new ArrayList<>();

        //ai = f(i-1)
        for (int i = 0; i < f.size() - 1; i++) {
            matr.add(new ArrayList<>());
            for (int j = 0; j < (f.size() - 1) * 4; j++) {
                if (j == i * 4) {
                    matr.get(i).add(1.0);
                } else {
                    matr.get(i).add(0.0);
                }
            }
            free.add(f.get(i).getSecond());
        }

        //ai+bi*hi+ci*hi^2+di*hi^3 = fi
        for (int i = 0; i < f.size() - 1; i++) {
            matr.add(new ArrayList<>());
            double hk = 1;
            double h = f.get(i + 1).getFirst() - f.get(i).getFirst();
            for (int j = 0; j < (f.size() - 1) * 4; j++) {
                if (j >= i * 4 && j <= i * 4 + 3) {
                    matr.get(i + f.size() - 1).add(hk);
                    hk *= h;
                } else {
                    matr.get(i + f.size() - 1).add(0.0);
                }
            }
            free.add(f.get(i + 1).getSecond());
        }

        //bi+2ci*hi+3di*hi^2 = bi+1
        for (int i = 0; i < f.size() - 2; i++) {
            matr.add(new ArrayList<>());
            double hk = 1;
            double h = f.get(i + 1).getFirst() - f.get(i).getFirst();
            for (int j = 0; j < (f.size() - 1) * 4; j++) {
                if (j >= i * 4 + 1 && j <= i * 4 + 3) {
                    matr.get(i + f.size() * 2 - 2).add(hk * (j - i * 4));
                    hk *= h;
                } else if (j == i * 4 + 5) {
                    matr.get(i + f.size() * 2 - 2).add(-1.0);
                } else {
                    matr.get(i + f.size() * 2 - 2).add(0.0);
                }
            }
            free.add(0.0);
        }

        //2ci+6di*hi = 2*ci+1
        for (int i = 0; i < f.size() - 2; i++) {
            matr.add(new ArrayList<>());
            double h = f.get(i + 1).getFirst() - f.get(i).getFirst();
            for (int j = 0; j < (f.size() - 1) * 4; j++) {
                if (j == i * 4 + 2) {
                    matr.get(i + f.size() * 3 - 4).add(2.0);
                } else if (j == i * 4 + 3) {
                    matr.get(i + f.size() * 3 - 4).add(6.0 * h);
                } else if (j == i * 4 + 6) {
                    matr.get(i + f.size() * 3 - 4).add(-2.0 - i);
                } else {
                    matr.get(i + f.size() * 3 - 4).add(0.0);
                }
            }
            free.add(0.0);
        }

        //c1 = 0
        matr.add(new ArrayList<>());
        matr.get((f.size() - 1) * 4 - 2).add(0.0);
        matr.get((f.size() - 1) * 4 - 2).add(0.0);
        matr.get((f.size() - 1) * 4 - 2).add(1.0);
        for (int j = 3; j < (f.size() - 1) * 4; j++) {
            matr.get((f.size() - 1) * 4 - 2).add(0.0);
        }
        free.add(0.0);

        //2cn+6dn*hn = 0
        matr.add(new ArrayList<>());
        for (int j = 0; j < (f.size() - 1) * 4 - 2; j++) {
            matr.get((f.size() - 1) * 4 - 1).add(0.0);
        }
        matr.get((f.size() - 1) * 4 - 1).add(2.0);
        matr.get((f.size() - 1) * 4 - 1).add(6.0 * (f.get(f.size() - 1).getFirst() -  f.get(f.size() - 2).getFirst()));
        free.add(0.0);

        int i = 0;
        for (double fr : free) {
            matr.get(i).add(fr);
            i++;
        }

        return matr;
    }

    public static void print(ArrayList<Pair<Double, Double>> f) {
        System.out.println("Матрица коэффициентов для сплайнов: ");
        ArrayList<ArrayList<Double>> arr = getSLAYSplain(f);
        for (ArrayList<Double> ar:arr) {
            for (double a:ar) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    //решение матрицы коэффициентов кубического сплайна методом Гаусса
    public static void showWork(ArrayList<Pair<Double, Double>> f) {
        ArrayList<Double> x = methodGauss0(Splain.getSLAYSplain(f));
        System.out.println(x);
    }

    public static void showWork() {
        ArrayList<Pair<Double, Double>> f = readF();
        showWork(f);
    }
}
