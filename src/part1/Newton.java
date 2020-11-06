package part1;

import Pair.Pair;

import java.util.ArrayList;

import static utilits.Utilits.getNewX;


public class Newton {
    public static ArrayList<ArrayList<Double>> getRazdelRazn(ArrayList<Pair<Double, Double>> f) {
        ArrayList<Double> xk = Pair.getFirstsFromArrayList(f);

        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (Pair<Double, Double> fx : f) {
            res.get(0).add(fx.getSecond());
        }
        for (int i = 1; i < xk.size(); i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < xk.size() - i; j++) {
                double f2 = res.get(i - 1).get(j + 1);
                double f1 = res.get(i - 1).get(j);
                double x2 = xk.get(j + i);
                double x1 = xk.get(j);
                res.get(i).add((f2 - f1) / (x2 - x1));
            }
        }
        return res;
    }

    public static ArrayList<Pair<Double, Double>> getNewton(ArrayList<Pair<Double, Double>> f) {
        ArrayList<Double> newXs = getNewX(f); //создали массив, состоящих из узловых точек и точек между ними
        ArrayList<Double> oldXs = Pair.getFirstsFromArrayList(f); //без неузловых х
        ArrayList<ArrayList<Double>> RR = getRazdelRazn(f); //разделенные разности
        ArrayList<Pair<Double, Double>> N = new ArrayList<>(); //результаты
        for (double x : newXs) { //считаем для кажого х, в т.ч. неузлового
            double Nk = RR.get(0).get(0); //прибавляем первое слагаемое
            double proizv = 1; //произведения (х-х0)(х-х1)..(х-хн)
            for (int i = 1; i < oldXs.size(); i++) {
                proizv *= x - oldXs.get(i - 1);
                Nk += RR.get(i).get(0) * proizv;
            }
            N.add(new Pair<>(x, Nk));
        }
        return N;
    }

    public static void print(ArrayList<Pair<Double, Double>> f) {
        ArrayList<ArrayList<Double>> res = getRazdelRazn(f);
        System.out.println("Разделенные разности для многочлена в форме Ньютона:");
        for (ArrayList<Double> arr: res) {
            for (double a: arr) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        ArrayList<Pair<Double, Double>> N = getNewton(f); //результаты подсчетов для каждого х методов Лагранжа
        //выводим результаты подсчетов для каждого х
        System.out.println("Результат для метода Ньютона: ");
        int i = 0;
        for (Pair<Double, Double> pair : N) {
            System.out.println("x" + i + " = " + pair.getFirst() + " f(" + pair.getFirst() + ") = " + pair.getSecond());
            i++;
        }
    }
}
