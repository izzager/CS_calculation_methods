package part1;

import Pair.Pair;

import java.util.ArrayList;

public class SLAY {
    public static ArrayList<Pair<Double, Double>> getSLAY(ArrayList<Pair<Double, Double>> f) {
        ArrayList<Pair<Double, Double>> res = new ArrayList<>();
        int n = f.size() - 1;
        double[][] matr = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                matr[i][j] = Math.pow(f.get(i).getFirst(), (n - j));
                //System.out.print(matr[i][j] + " ");
            }
            //System.out.println("");
        }

        return res;
    }

    public static void print() {

    }
}
