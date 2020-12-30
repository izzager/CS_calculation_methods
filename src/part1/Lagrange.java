package part1;

import Pair.Pair;

import java.util.ArrayList;

import static utilits.Utilits.getNewX;

//решение интерполяционного многочлена в форме Лагранжа
public class Lagrange {

    public static ArrayList<Pair<Double, Double>> getLagrange(ArrayList<Pair<Double, Double>> f) {
        //создали массив, состоящих из узловых точек и точек между ними
        ArrayList<Double> newXs = getNewX(f);

        ArrayList<Double> oldXs = Pair.getFirstsFromArrayList(f); //без неузловых х
        ArrayList<Pair<Double, Double>> L = new ArrayList<>(); //результаты подсчетов для каждого х
        for (Double x : newXs) { //для каждого х строим многочлен Лагранжа
            double l = 0;
            int j = 0;
            for (Double xk : oldXs) { //считаем в этом цикле слагаемые для х, пробегая по всем узловым х
                int k = 0;
                double chisl = 1;
                double znam = 1;
                for (Double xkk : oldXs) {
                    if (j != k) { //пропускаем для x = xk
                        chisl *= x - xkk;
                        znam *= xk - xkk;
                    }
                    k++;
                }
                l += f.get(j).getSecond() * chisl / znam; //собираем слагаемое и прибавляем его к уже подсчитанным значениям
                j++;
            }
            L.add(new Pair<>(x, l));
        }
        return L;
    }

    public static void print(ArrayList<Pair<Double, Double>> f) {
        System.out.println("1.2 Интерполяционный многочлен в форме Лагранжа");
        ArrayList<Pair<Double, Double>> L = getLagrange(f); //результаты подсчетов для каждого х методов Лагранжа
        //выводим результаты подсчетов для каждого х
        System.out.println("Результат для метода Лагранжа: ");
        int i = 0;
        for (Pair<Double, Double> pair : L) {
            System.out.println("x" + i + " = " + pair.getFirst() + " f(" + pair.getFirst() + ") = " + pair.getSecond());
            i++;
        }
        System.out.println();
    }
}
