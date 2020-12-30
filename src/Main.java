import Pair.Pair;
import part1.Lagrange;
import part1.Newton;
import part1.Splain;
import part2.Determinant;
import part2.Gauss;
import part2.MethodProgonki;
import part2.MethodSimpleIteration;
import part3.DifferenceMethod;
import part3.EulerMethods;
import part3.OdnorodnayaZadacha;
import part3.UndefinedCoefficientsMethod;
import part4.IntegralEquationDegenerateCore;
import part4.QudraturMethod;
import utilits.Utilits;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static Integer i = 3;

    public static void main(String[] args) {
        //ArrayList<Pair<Double, Double>> f = Utilits.readF();
        //int n = f.size() - 1;

        //Utilits.printF(f);

        //Lagrange.print(f);

        //Newton.print(f);

        //Splain.print(f);
        //Splain.showWork();

        Gauss.showWork();
        //Gauss.print(5);

        Determinant.showWork();

        MethodProgonki.showWork();

        MethodSimpleIteration.showWork();

        EulerMethods.showWork();

        DifferenceMethod.showWork();

        OdnorodnayaZadacha.showWork();

        UndefinedCoefficientsMethod.showWork();

        IntegralEquationDegenerateCore.showWork();

        QudraturMethod.showWork();
    }
}