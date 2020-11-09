package part1;

import Pair.Pair;

import java.util.ArrayList;

import static part2.Gauss.methodGauss0;
import static utilits.Utilits.readF;

public class SLAY {
    //система линейных алгебраических уравнений
    public static ArrayList<Pair<Double, Double>> getSLAY(ArrayList<Pair<Double, Double>> f) {
        ArrayList<Pair<Double, Double>> res = new ArrayList<>();
        int n = f.size() - 1;
        double[][] matr = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                matr[i][j] = Math.pow(f.get(i).getFirst(), (n - j));
            }
        }

        return res;
    }

    public static void showWork(ArrayList<Pair<Double, Double>> f) {
        ArrayList<Pair<Double, Double>> slay = getSLAY(f);
        System.out.println(slay);
    }

    public static void showWork() {
        ArrayList<Pair<Double, Double>> f = readF();
        showWork(f);
    }
}
