import part2.MethodSimpleIteration;

import java.util.ArrayList;

import static part3.EulerMethods.*;

public class Main {

    public static void main(String[] args) {
        //ArrayList<Pair<Double, Double>> f = Utilits.readF();
        //int n = f.size() - 1;

        //Utilits.printF(f);

        //Lagrange.print(f);

        //Newton.print(f);

        //Splain.print(f);
        //Splain.showWork();

        //Gauss.showWork();

        //Determinant.showWork();

        //MethodProgonki.showWork();

        //MethodSimpleIteration.showWork();

        ArrayList<Double> x = getX(1.0, 10);
        ArrayList<Double> yt = getYTochonoe(1.0, 10);
        ArrayList<Double> y1 = methodEuler(1.0, 4.0, 10);
        ArrayList<Double> y2 = upgradeMethodEuler(1.0, 4.0, 10);
        ArrayList<Double> y3 = methodPreditorCorrector(1.0, 4.0, 10);
        getTable(x, yt, y1);
        getTable(x, yt, y2);
        getTable(x, yt, y3);
    }
}